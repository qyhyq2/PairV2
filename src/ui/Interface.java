package ui;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import program.MakePair;

import model.Female;
import model.Person;

public class Interface extends JFrame implements ActionListener{
        private static int MaleNum;
        private static int FemaleNum;
        private ArrayList<Person[]> result = new ArrayList<Person[]>();
		private JPanel getDataWayPane,randomPanel,filePanel,inputPanelr,cards;
		private Button randomButton,fileButton,randomGetRltButton,fileGetRltButton;
		private JTextArea inputTextAreaM,inputTextAreaF;
		private JLabel inputLabelM,inputLabelF;
		private JList rlt;
		private CardLayout cl;
		
		public Interface()
		{
			setSize(460,333);
			setLocation(500, 400);
			setTitle("Automated Matching V2");
			
			//getDataWayPanel: choose the way to get data
			randomButton = new Button("Using random data");
			randomButton.addActionListener(this);
			
			fileButton = new Button("Using data from specific file");
			fileButton.addActionListener(this);
			
			getDataWayPane = new JPanel();
			getDataWayPane.setLayout(new FlowLayout());
			getDataWayPane.add(randomButton);
			getDataWayPane.add(fileButton);
			//getDataWayPanel
			
			//filePanel:using data from file
			fileGetRltButton = new Button("Get the result using data from file");
			fileGetRltButton.addActionListener(this);
			
			filePanel = new JPanel();
			FlowLayout fl = new FlowLayout();
			fl.setVgap(30);
			filePanel.setLayout(fl);
			filePanel.add(new JPanel().add(fileGetRltButton));
			//filePanel
			
			//randomPanel:using random data
			
				//inputPanelr:to get the player data
				inputTextAreaM = new JTextArea();
				inputTextAreaF = new JTextArea();
				inputLabelM = new JLabel("Please enter the number of Male");
				inputLabelF = new JLabel("Please enter the number of Female");
				
				inputPanelr = new JPanel();
				GridLayout gl = new GridLayout(2,2);
				gl.setHgap(20);
				gl.setVgap(10);
				inputPanelr.setLayout(gl);
				inputPanelr.add(inputLabelM);
                inputPanelr.add(inputLabelF);
				inputPanelr.add(inputTextAreaM);
                inputPanelr.add(inputTextAreaF);
				//inputPanel
		
		
			randomGetRltButton = new Button("Get the result using random data");
			randomGetRltButton.addActionListener(this);
			
			randomPanel = new JPanel();
			randomPanel.setLayout(new FlowLayout());
			randomPanel.add(inputPanelr);
			randomPanel.add(randomGetRltButton);
			//randomPanel
			
			
			add("North", getDataWayPane);
			
			
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("[Format:]MaleId,attributions-----FemaleId,attributions,lowExpection");
			rlt = new JList(listModel);
			JScrollPane scroll = new JScrollPane(rlt);
            scroll.setAutoscrolls(true);
			add("South",scroll);
			
			cl = new CardLayout();
			cards = new JPanel(cl);	
			cards.add("random data",randomPanel);
			cards.add("data from file",filePanel);
		    add("Center",cards);
		    
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand()=="Using random data"){
				cl.first(cards);
			}
			
			else if(e.getActionCommand()=="Using data from specific file"){
				cl.last(cards);				
			}
			else if(e.getActionCommand()=="Get the result using random data"){
			    if(inputTextAreaM.getText().trim().matches("\\d+") && inputTextAreaF.getText().trim().matches("\\d+")){
			        MaleNum = Integer.parseInt(inputTextAreaM.getText().trim());
			        FemaleNum = Integer.parseInt(inputTextAreaF.getText().trim());
			        MakePair.usingRandomData(MaleNum,FemaleNum);
			        result = MakePair.getAllResult();
	                outputResult();
	                MakePair.clearData();
			    }
			    else{
			        DefaultListModel listModel = new DefaultListModel();
	                listModel.addElement("Invalid input");
	                rlt.setModel(listModel);
			    }
				
			}
			else if(e.getActionCommand()=="Get the result using data from file"){
			    MaleNum = 150;
			    FemaleNum = 100;
				MakePair.usingFileData(MaleNum,FemaleNum);
				result = MakePair.getAllResult();
				outputResult();
				MakePair.clearData();
			}
				
			
		}
		
		/**
		 * Output the result
		 */
		private void outputResult(){
		    if(result!=null || result.size()==0){
                DefaultListModel listModel = new DefaultListModel();
                listModel.addElement("[Format:]MaleId,attributions-----FemaleId,attributions,lowExpection");
                for(Person[] p :result){
                    listModel.addElement(p[0]+"-----"+(Female)p[1]);
                }
                listModel.addElement("Total: "+result.size()+"Pairs");
                rlt.setModel(listModel);
            }
            else{
                DefaultListModel listModel = new DefaultListModel();
                listModel.addElement("[Format:]MaleId,attributions-----FemaleId,attributions,lowExpection");
                listModel.addElement("There is no pair generated");
                rlt.setModel(listModel);
            }
		}
	
}
