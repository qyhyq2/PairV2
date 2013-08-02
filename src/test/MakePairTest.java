package test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import model.Female;
import model.Male;
import model.Person;

import org.junit.After;
import org.junit.Test;

import program.MakePair;


/**
 * Unit test of MakePair using different data
 * @author qyh
 *
 */
public class MakePairTest {
	final int MaleNum=150,FemaleNum=100;
	
	
	/**
	 * Test the method usingFileData()
	 *  Make sure the female and male has been setup
	 */
	@Test
	public void testUsingFileData(){
	    MakePair.usingFileData(MaleNum,FemaleNum);
		assertTrue(MakePair.getMale().size()==MaleNum);
		assertTrue(MakePair.getFemale().size()==FemaleNum);
		
	}
	
	
	
    /**
     * Test the method setHighestScoreFemale(Male man):
     * For specific male,set a female who is mostly fitted his expectation to his target
     * in remaining females
     */
	@Test
	public void testSetHighestScoreFemale(){
	    Male m = new Male(1,80,80,80,80,20,20,30,30);
	    Female f0 = new Female(1,97,97,97,97,5,20,70,5,1000);
	    Female f1 = new Female(2,50,50,30,30,5,20,70,5,1000);
	    Female f2 = new Female(3,20,20,50,50,5,20,70,5,1000);
	    Female f3 = new Female(4,20,20,50,50,5,20,70,5,1000);
	    Female f4 = new Female(5,10,10,10,10,5,20,70,5,1000);
        ArrayList<Female> female = new ArrayList<Female>();
        female.add(f0);
        female.add(f1);
        female.add(f2);
        female.add(f3);
        female.add(f4);
        MakePair.setHighestScoreFemale(m,female);
        assertTrue(m.getTarget().getId()==1);
        female.remove(f0);
        MakePair.setHighestScoreFemale(m,female);
        assertTrue(m.getTarget().getId()==2);
        female.remove(f1);
        MakePair.setHighestScoreFemale(m,female);
        assertTrue(m.getTarget().getId()==3);
	}
	
	/**
	 * Test the method getOnePair() using different data: 
	 *  Get one matching pair according to the specific rule
	 *  the method return Person[], index 0 is male,index 1 is female
	 *  
	 *  TestCase:
	 *  m chooses f0 but m doesn't reach the low expectation of f0
     *  f1 gets no vote, so return null
     *  
     *  Purpose:
     *  it verifies low expectation can work  
	 */
	@Test
	public void testGetOnePair1(){
	    Female f0 = new Female(0,97,97,97,97,5,20,70,5,3000);
        Female f1 = new Female(1,50,50,30,30,5,20,70,5,1000);
        Male m0 = new Male(0,20,20,20,20,20,20,30,30);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        female.add(f1);
        male.add(m0);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()==null);
	}
      
	/**
	 * Test the method getOnePair() using different data: 
	 *  Get one matching pair according to the specific rule
	 *  the method return Person[], index 0 is male,index 1 is female
	 *  
	 *  TestCase:
	 *  m1 chooses f2 and m1 does reach the low expectation of f2
     *  f3 gets no vote, so return the matcher m1---f2
     *  
     *  Purpose:
     *  it verifies low expectation can work
	 */
	@Test
	public void testGetOnePair2(){
        Female f2 = new Female(2,97,97,97,97,5,20,70,5,2000);
        Female f3 = new Female(3,50,50,30,30,5,20,70,5,1000);
        Male m1 = new Male(1,20,20,20,20,20,20,30,30);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f2);
        female.add(f3);
        male.add(m1);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==1);
        assertTrue(MakePair.getOnePair()[1].getId()==2);
	}
	
	/**
	 * Test the method getOnePair() using different data: 
	 *  Get one matching pair according to the specific rule
	 *  the method return Person[], index 0 is male,index 1 is female
	 *  
	 *  TestCase:
	 *  m2,m3 choose f4 but do not reach the low expectation of f2
     *  m4 chooses f5 but do not reach the low expectation of f5,so return null
     *  
     *  Purpose:
     *  it verifies when all the females give up, no pair can be generated
	 */
	@Test
	public void testGetOnePair3(){
        Female f4 = new Female(4,90,90,90,10,5,20,70,5,3000);
        Female f5 = new Female(5,30,30,30,90,5,20,70,5,3000);
        Male m2 = new Male(2,20,20,20,20,30,30,30,10);
        Male m3 = new Male(3,20,20,20,20,30,30,30,10);
        Male m4 = new Male(4,20,20,20,20,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f4);
        female.add(f5);
        male.add(m2);
        male.add(m3);
        male.add(m4);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()==null);
	}
	
	/**
	 * Test the method getOnePair() using different data: 
	 *  Get one matching pair according to the specific rule
	 *  the method return Person[], index 0 is male,index 1 is female
	 *  
	 *  TestCase:
	 *  m5,m6 choose f6 but they do not reach the low expectation of f6
     *  m7 chooses f7 and m7 can reach the low expectation of f7,so return m7---f7
     *  
     *  Purpose:
     *  it verifies when a female gives up, the next one can get matcher.
	 */
	@Test
	public void testGetOnePair4(){
        Female f6 = new Female(6,90,90,90,10,5,20,70,5,3000);
        Female f7 = new Female(7,30,30,30,90,5,20,70,5,2000);
        Male m5 = new Male(5,20,20,20,20,30,30,30,10);
        Male m6 = new Male(6,20,20,20,20,30,30,30,10);
        Male m7 = new Male(7,20,20,20,20,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f6);
        female.add(f7);
        male.add(m5);
        male.add(m6);
        male.add(m7);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==7);
        assertTrue(MakePair.getOnePair()[1].getId()==7);
	}
	
	/**
     * Test the method getOnePair() using different data: 
     *  Get one matching pair according to the specific rule
     *  the method return Person[], index 0 is male,index 1 is female
     *  
     *  TestCase:
     *  m0,m1,m2,m3 choose f0;  no one reach low expectation*1.5;
     *  m0,m1,m2 reach the low expectation;  m3 doesn't reach low expectation
     *  m0 has highest prior(greater sum of attributions and less id),so return m0---f0
     *  
     *  Purpose:
     *  it verifies when no male reaches low expectation*1.5 but there's
     *  some males reach expectation, the female will chooses a male who has greater prior
     */
    @Test
    public void testGetOnePair5(){
        Female f0 = new Female(0,90,90,90,10,10,10,10,70,4000);
        Male m0 = new Male(0,20,20,20,60,30,30,30,10);
        Male m1 = new Male(1,20,20,20,60,30,30,30,10);
        Male m2 = new Male(2,10,10,5,65,1,1,1,97);
        Male m3 = new Male(3,20,20,20,20,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        male.add(m0);
        male.add(m1);
        male.add(m2);
        male.add(m3);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==0);
        assertTrue(MakePair.getOnePair()[1].getId()==0);
    }
	
    /**
     * Test the method getOnePair() using different data: 
     *  Get one matching pair according to the specific rule
     *  the method return Person[], index 0 is male,index 1 is female
     *  
     *  TestCase:
     *  m0,m1,m2,m3 choose f0;  m0 reach low expectation*1.5;
     *  m1,m2 reach the low expectation;  m3 doesn't reach low expectation
     *  m0 reach low expectation*1.5,so return m0---f0
     *  
     *  Purpose:
     *  it verifies when a male reaches low expectation*1.5 and has greater index(means he will invite first) 
     *  will be prior 
     */
    @Test
    public void testGetOnePair6(){
        Female f0 = new Female(0,90,90,90,10,10,10,10,70,4000);
        Male m0 = new Male(0,20,20,20,90,30,30,30,10);
        Male m1 = new Male(1,10,10,10,60,30,30,30,10);
        Male m2 = new Male(2,10,10,20,60,1,1,1,97);
        Male m3 = new Male(3,20,20,20,20,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        male.add(m0);
        male.add(m1);
        male.add(m2);
        male.add(m3);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==0);
        assertTrue(MakePair.getOnePair()[1].getId()==0);
    }
    
    /**
     * Test the method getOnePair() using different data: 
     *  Get one matching pair according to the specific rule
     *  the method return Person[], index 0 is male,index 1 is female
     *  
     *  TestCase:
     *  m0,m1,m2,m3 choose f0;  m0,m1 reach low expectation*1.5;
     *  m2 reach the low expectation;  m3 doesn't reach low expectation
     *  m0,m1 reach low expectation*1.5, m1 has greater index ,so return m1---f0
     *  
     *  Purpose:
     *  it verifies when a male reaches low expectation*1.5 and has greater index(means he will invite first) 
     *  will be prior
     */
    @Test
    public void testGetOnePair7(){
        Female f0 = new Female(0,90,90,90,10,10,10,10,70,4000);
        Male m0 = new Male(0,20,20,20,90,30,30,30,10);
        Male m1 = new Male(1,10,10,20,80,30,30,30,10);
        Male m2 = new Male(2,10,10,20,60,1,1,1,97);
        Male m3 = new Male(3,20,20,20,20,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        male.add(m0);
        male.add(m1);
        male.add(m2);
        male.add(m3);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==1);
        assertTrue(MakePair.getOnePair()[1].getId()==0);
    }
    
    /**
     * Test the method getAllResult(): 
     * Get all the result of the matching which will return all the pair
     * 
     * TestCase:
     *  m0,m1,m2 choose f0 but do not reach the low expectation of f6, so f6 give up
     *  m3,m4 choose f1 and f1 choose m4,so return first pair m4---f1
     *  and next round m3 also chooses f0 and can reach the low expectation of f6
     *  so return second pair m3---f0
     *  
     *  Purpose:
     *  it can prove that when a female give up in a round,she also has the possibility 
     *  to get a matcher in some round next.
     */
    @Test
    public void testGetAllResult(){
        Female f0 = new Female(0,90,90,90,10,5,20,70,5,3000);
        Female f1 = new Female(1,30,30,30,90,5,20,70,5,2000);
        Male m0 = new Male(0,20,20,20,20,30,30,30,10);
        Male m1 = new Male(1,20,20,20,20,30,30,30,10);
        Male m2 = new Male(2,20,20,20,20,30,30,30,10);
        Male m3 = new Male(3,30,30,30,30,1,1,1,97);
        Male m4 = new Male(4,40,40,40,40,1,1,1,97);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        female.add(f1);
        male.add(m0);
        male.add(m1);
        male.add(m2);
        male.add(m3);
        male.add(m4);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        ArrayList<Person[]> result = new ArrayList<Person[]>();
        result = MakePair.getAllResult();
        assertTrue(result.get(0)[0].getId()==4);
        assertTrue(result.get(0)[1].getId()==1);
        assertTrue(result.get(1)[0].getId()==3);
        assertTrue(result.get(1)[1].getId()==0);
    }
    
	/**
	 * Test the method compareVote():
	 * Compare two female's voteBox to find the winner according to the rule that 
	 * the one who has higher summary of attributions and smaller id will win when 
	 * having the same votes
	 */
	@Test
	public void testCompVote(){
		Female f0 = new Female(1,97,97,97,97,10,10,70,10,1000);
		Female f1 = new Female(2,12,12,12,12,10,10,70,10,1000);
		Female f2 = new Female(3,97,90,90,90,10,10,70,10,1000);
		Female f3 = new Female(4,97,97,97,97,10,10,70,10,1000);
		ArrayList<Female> female = new ArrayList<Female>(); 
		female.add(f0);
		female.add(f1);
		female.add(f2);
		female.add(f3);
		f0.setVoteBox(10);
		f1.setVoteBox(12);
		f2.setVoteBox(10);
		f3.setVoteBox(10);
		assertTrue(MakePair.compareVote(0, 1, female)==-1);
		assertTrue(MakePair.compareVote(0, 2, female)==1);
		assertTrue(MakePair.compareVote(0, 3, female)==1);		
	}
	
	/**
	 * Test the final result
	 */
	@Test
	public void testShowAllResult(){
		MakePair.usingFileData(MaleNum,FemaleNum);
		for(Person[] p : MakePair.getAllResult()){
           System.out.println(p[0]+"-----"+(Female)p[1]);
        }
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MakePair.clearData();
	}

}
