package myPackage;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
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

import model.Person;

public class Interface extends JFrame implements ActionListener{
        private static final int MaleNum=150,FemaleNum=100;
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
			setLocation(400, 300);
			setTitle("Automated Matching");
			
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
			filePanel.setLayout(new FlowLayout());
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
			listModel.addElement("[Format:]MaleId,attributions-----FemaleId,attributions");
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
//				String attribution = inputTextArear.getText();
//				if(attribution!=null && !attribution.trim().equals("") && verifyInput(attribution)){
//					MakePair.usingRandomData(MaleNum, FemaleNum);
//					Person matcher = MakePair.getMatcher();
//					if(matcher!=null){
//						rlt.setText("<html>[Format:]id,looks,character,wealth,exceptLooks," +
//							"expectCharacter,expectWealth<br>"+"The player's matcher is "+
//							matcher+"</html>");
//					}
//					else{
//						rlt.setText("<html>[Format:]id,looks,character,wealth,exceptLooks," +
//								"expectCharacter,expectWealth<br>"+"The player is so pool," +
//								"he has no macther</html>");
//					}
//					MakePair.clearData();
//					return;
//				}
//				rlt.setText("the input is invalid");
				
			}
			else if(e.getActionCommand()=="Get the result using data from file"){
				MakePair.usingFileData(MaleNum,FemaleNum);
				result = MakePair.showAllResult();
				if(result!=null){
				    DefaultListModel listModel = new DefaultListModel();
		            listModel.addElement("[Format:]MaleId,attributions-----FemaleId,attributions");
				    for(Person[] p :result){
				        listModel.addElement(p[0]+"-----"+p[1]);
				    }
				    rlt.setModel(listModel);
				}
				else{
				    DefaultListModel listModel = new DefaultListModel();
                    listModel.addElement("<html>[Format:]MaleId,attributions-----FemaleId,attributions<br>" +
							"there is no pair generated</html>");
                    rlt.setModel(listModel);
				}
				MakePair.clearData();
				return;
			}
				
			
		}
		
		/**
		 * Verify the user's input is valid or not with specific rule
		 * @param input
		 * @return
		 */
		private boolean verifyInput(String input){
			String regex = "[0-1],\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2}";
			String value[] = new String[7];
			boolean isValid = false;
			if(input.matches(regex)){
				value = input.split(",");
				for(int i=1;i<4;i++){//the attribution should be in [1-97]
					int vi = Integer.parseInt(value[i]);
					if(vi<=97&&vi>=1){
						isValid = true;
					}
					else{
						isValid = false;
						return isValid;
					}
				}
				if(Integer.parseInt(value[4])+Integer.parseInt(value[5])
						+Integer.parseInt(value[6])==100){//the summary exception should be 100
					isValid = true;
				}
				else{
					isValid = false;
					return isValid;
				}
			}
			
			return isValid;
		}
	
}
