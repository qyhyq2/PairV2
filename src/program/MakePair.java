package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.Female;
import model.Male;
import model.Person;

/**
 * @author qyh
 *
 */
public class MakePair {
	private static final String MALE_FILE_PATH = "attachments\\e_ztework_mywork_male.txt";
	private static final String FEMALE_FILE_PATH = "attachments\\e_ztework_mywork_female.txt";
	private static final int MAX_ATTRIBUTION_VALUE = 100;
	private static final int MAX_EXPECTAION_VALUE = 10000;
	private static ArrayList<Male> male = new ArrayList<Male>();
	private static ArrayList<Female> female = new ArrayList<Female>();
	private static int maleNum,femaleNum;
	
	/**
	 * Set data using specific file data
	 * the number of male is MaleNum,the number of female is FemaleNum 
	 * @param 
	 */
	public static void usingFileData(int MaleNum,int FemaleNum) {
		setMaleNum(MaleNum);
        setFemaleNum(FemaleNum);
		readFileToMale(MALE_FILE_PATH);
		readFileToFemale(FEMALE_FILE_PATH);
	}
	
	/**
	 * Set data using random data
	 * the number of male is MaleNum,the number of female is FemaleNum 
	 * @param MaleNum
	 * @param FemaleNum
	 */
	public static void usingRandomData(int MaleNum,int FemaleNum){
	    setMaleNum(MaleNum);
        setFemaleNum(FemaleNum);
		setRandomData(maleNum,femaleNum);
	}
	
	/**
	 * Get all the result of the matching
	 * @return ArrayList<Person[]> 
	 *         all the pair
	 */
	public static ArrayList<Person[]> getAllResult(){
		Person[] onePair;
		ArrayList<Person[]> result = new ArrayList<Person[]>();
		while(male.size()>0 && female.size()>0){
			onePair = getOnePair();
			if(onePair!=null){
    			result.add(onePair);
    			male.remove(onePair[0]);
    			female.remove(onePair[1]);
			}
			else
			    break;
		}
		
		return result;
	}
	
	
	/**
	 * Read male from file of the path,and set the data to ArrayList<Male> male
	 * @param path
	 * @param p
	 */
	private static void readFileToMale(String malePath) {
		FileReader fr = null ;
		BufferedReader br = null;
		try {
			fr = new FileReader(new File(malePath));
			br = new BufferedReader(fr);
			String line ;
			for(int i=0;i<maleNum;i++){
				line = br.readLine();
				if(line!=null){
				    male.add(createMale(line));
				}
				else{
				    return;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	/**
     * Read female from file which is in the path,and set the data to ArrayList<Female> female
     * @param path
     * @param p
     */
    private static void readFileToFemale(String femalePath) {
        FileReader fr = null ;
        BufferedReader br = null;
        try {
            fr = new FileReader(new File(femalePath));
            br = new BufferedReader(fr);
            String line ;
            for(int i=0;i<femaleNum;i++){
                line = br.readLine();
                if(line!=null){
                    female.add(createFemale(line));
                }
                else{
                    return;
                }
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        
    }
	
	/**
	 * Create a Male by analyzing a String of a line
	 * @param str
	 * @return Person who set data from str
	 */
	private static Male createMale(String str){
		String[] val = str.split(",");
		return (new Male(Integer.parseInt(val[0]),Integer.parseInt(val[1]),Integer.parseInt(val[2]),
		        Integer.parseInt(val[3]),Integer.parseInt(val[4]),Integer.parseInt(val[5]),
				Integer.parseInt(val[6]),Integer.parseInt(val[7]),Integer.parseInt(val[8])));
	}
	
    /**
     * Create a female by analyzing a String of a line
     * @param str
     * @return Person who set data from str
     */
    private static Female createFemale(String str){
        String[] val = str.split(",");
        return (new Female(Integer.parseInt(val[0]),Integer.parseInt(val[1]),Integer.parseInt(val[2]),
                Integer.parseInt(val[3]),Integer.parseInt(val[4]),Integer.parseInt(val[5]),
                Integer.parseInt(val[6]),Integer.parseInt(val[7]),Integer.parseInt(val[8]),Integer.parseInt(val[9])));
    }
	
	
	
	
	/**
	 * Calculate the the score of targetPerson to sourcePerson
	 * @param targetPerson
	 * @param sourcePerson
	 * @return the score of targetPerson to sourcePerson
	 */
	private static int calculateScore(Person targetPerson,Person sourcePerson){
		return (sourcePerson.getExpectLooks()*targetPerson.getLooks() 
				+ sourcePerson.getExpectCharacter()*targetPerson.getCharacter()
				+ sourcePerson.getExpectWealth()*targetPerson.getWealth()
				+ sourcePerson.getExpectHealth()*targetPerson.getHealth());
	}
	
	/**
	 * Compare the priority of two persons when they have the same score
	 * the rule is that the one who has higher summary of attributions and smaller id 
	 * is prior
	 * @param p1
	 * @param p2
	 * @return 1,-1
	 *  1 means p1 is prior to p2
	 *  -1 means p2 is prior to p1
	 */
	private static int comparePriority(Person p1,Person p2){
		if(p1.getLooks()+p1.getCharacter()+p1.getWealth()+p1.getHealth()>
		p2.getLooks()+p2.getCharacter()+p2.getWealth()+p2.getHealth()){
			return 1;
		}
		else if(p1.getLooks()+p1.getCharacter()+p1.getWealth()+p1.getHealth()<
		p2.getLooks()+p2.getCharacter()+p2.getWealth()+p2.getHealth()){
			return -1;
		}
		else if(p1.getId()<p2.getId()){
			return 1;
		}
		else
			return -1;
	}
	
	/**
	 * Get one matching pair according to the specific rule
	 * @return Person[]
	 *  index 0 is male,index 1 is female.
	 *  if return null, that means no pair is generated
	 */
	public static Person[] getOnePair(){
		int score;
		Female highScoreFemale;
		ArrayList<Female> NoSelectFemale = new ArrayList<Female>();
		initializeVote();
		
		//males vote one who has the highest score among existing females
        //meanwhile female can record the male who is the better matcher among voters
		for(Male voter:male){
		    
		    //check if the male's target is still remaining.If she's remaining,there's no need to find again  
			if(voter.getTarget()== null || (!female.contains(voter.getTarget()))){
				setHighestScoreFemale(voter,female);
				
				highScoreFemale = voter.getTarget();
	            score = calculateScore(voter, highScoreFemale);
	            if(score>=highScoreFemale.getExpectScore()*1.5){
	                if(highScoreFemale.getTarget()==null || male.indexOf(voter)>male.indexOf(highScoreFemale.getTarget())){
    	                highScoreFemale.setTarget(voter);
    	                highScoreFemale.setTargetScore(score);
	                }
	            }
	            else if(score<highScoreFemale.getExpectScore()){
	                continue;
	            }
	            else{//ExpectScore<=score<ExpectScore*1.5
	                if(score>highScoreFemale.getTargetScore()){
	                    highScoreFemale.setTarget(voter);
	                    highScoreFemale.setTargetScore(score);
	                }
	                else if(score==highScoreFemale.getTargetScore()){
	                    if(comparePriority(voter, highScoreFemale.getTarget())>0){
	                        highScoreFemale.setTarget(voter);
	                        highScoreFemale.setTargetScore(score);
	                    }
	                }
	            }
			}
			
			voter.getTarget().addVote();
		}
		
		//find the female and get the pair
		while(female.size()>0){//there're still some females
		    int index = 0;
    		for(int i=1;i<female.size();i++){//find the most/next popular female 
    		    if(compareVote(i,index,female)>0){
    		        index = i;
    		    }
    		}
    		
    		if(female.get(index).getVoteBox()==0){//the next popular one has no vote.
    		    return null;
    		}
    		else if(female.get(index).getTarget()!=null){//the female has a target,return the pair
    		    female.addAll(NoSelectFemale);
    		    return new Person[]{female.get(index).getTarget(),female.get(index)};
    		}
    		else{//the female hasn't a target, she will give up
    		    NoSelectFemale.add(female.get(index));
                female.remove(index);
    		}
		}
		
		//no female left,return null
		female.addAll(NoSelectFemale);
		return null;

	}
	
	/**
	 * Compare two female's voteBox to find the winner according to the rule that 
	 * the one who has higher summary of attributions and smaller id will win when 
	 * having the same votes
	 * @param index1 
	 * @param index2
	 * @param voteBox
	 * @return
	 */
	public static int compareVote(int index1,int index2,ArrayList<Female> female){
		Female f1 = female.get(index1);
		Female f2 = female.get(index2);
		if(f1.getVoteBox()>f2.getVoteBox()){
			return 1;
		}
		else if(f1.getVoteBox()<f2.getVoteBox()){
			return -1;
		}
		else if(f1.getLooks()+f1.getCharacter()+f1.getWealth()
				>f2.getLooks()+f2.getCharacter()+f2.getWealth()){
			return 1;
		}
		else if(f1.getLooks()+f1.getCharacter()+f1.getWealth()
				<f2.getLooks()+f2.getCharacter()+f2.getWealth()){
			return -1;
		}
		else if(f1.getId()<f2.getId()){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * For specific male,set a female who is mostly fitted his expectation to his target
	 * in remaining females 
	 * @param man
	 */
	public static void setHighestScoreFemale(Male man,ArrayList<Female> female){
		int score;
		man.setTarget(null);
		man.setTargetScore(0);
		for(int i=0;i<female.size();i++){
			score = calculateScore(female.get(i),man);
			if(score>man.getTargetScore()){
				man.setTargetScore(score);
				man.setTarget(female.get(i));
			}
			else if(man.getTarget()!=null && score==man.getTargetScore()){
			    if(comparePriority(female.get(i), man.getTarget())>0){
			        man.setTargetScore(score);
	                man.setTarget(female.get(i));
			    }
			}
		}
	}
	
	/**
	 * Reset all the VoteBox of each female
	 */
	private static void initializeVote(){
		for(Female f:female){
			f.resetVoteBox();
		}
	}
	
	/**
	 * Set random generated data to male and female
	 */
	private static void setRandomData(int MaleNum,int FemaleNum){
		male = generateRandomMale(MaleNum);
		female = generateRandomFemale(FemaleNum);
	}
	
	/**
	 * Generate Num with random male whose value between [1,97]
	 * @param Num the number of person.
	 * @return ArrayList<Male>
	 */
	private static ArrayList<Male> generateRandomMale(int Num){
	    int expectLooks,expectChar,expectWealth,expectHealth;
		ArrayList<Male> m = new ArrayList<Male>();
		Random ran = new Random();
		for(int i=0;i<Num;i++){
		    expectLooks = ran.nextInt(97)+1;
		    expectChar = ran.nextInt(98-expectLooks)+1;
		    expectWealth = ran.nextInt(99-expectLooks-expectChar)+1;
		    expectHealth = 100-expectLooks-expectChar-expectWealth;
			m.add(new Male(i,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,
					ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,expectLooks,expectChar,expectWealth,expectHealth));
		}
		return m;
	}
	
	/**
     * Generate Num with random female whose expectation between [1,97]
     * @param Num the number of person.
     * @return ArrayList<Female>
     */
    private static ArrayList<Female> generateRandomFemale(int Num){
        int expectLooks,expectChar,expectWealth,expectHealth;
        ArrayList<Female> fm = new ArrayList<Female>();
        Random ran = new Random();
        for(int i=0;i<Num;i++){
            expectLooks = ran.nextInt(97)+1;//[1,97]
            expectChar = ran.nextInt(98-expectLooks)+1;//[1,97]
            expectWealth = ran.nextInt(99-expectLooks-expectChar)+1;
            expectHealth = 100-expectLooks-expectChar-expectWealth;
            fm.add(new Female(i,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,
                    ran.nextInt(MAX_ATTRIBUTION_VALUE)+1,expectLooks,expectChar,expectWealth,expectHealth,ran.nextInt(MAX_EXPECTAION_VALUE)+1));
        }
        return fm;
    }
	
	/**
	 * Clear male and female
	 */
	public static void clearData(){
		female.clear();
		male.clear();
	}

    public static void setMaleNum(int maleNum){
        MakePair.maleNum = maleNum;
    }

    public static void setFemaleNum(int femaleNum){
        MakePair.femaleNum = femaleNum;
    }

	public static ArrayList<Male> getMale() {
		return male;
	}

	public static ArrayList<Female> getFemale() {
		return female;
	}

    public static void setMale(ArrayList<Male> male){
        MakePair.male = male;
    }

    public static void setFemale(ArrayList<Female> female){
        MakePair.female = female;
    }
	
	
}
