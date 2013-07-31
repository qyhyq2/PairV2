package myPackage;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Female;
import model.Male;
import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test of MakePair using data from specific file
 * @author qyh
 *
 */
public class MakePairTest {
	final int MaleNum=150,FemaleNum=100;
	
	
	/**
	 * Test the method data setup
	 *  Make sure the female and male has been setup
	 */
	@Test
	public void testReadFileToFemale(){
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
	 * Test the method getOnePair():
	 *  Get one matching pair according to the specific rule
	 *  the method return Person[], index 0 is male,index 1 is female
	 */
	@Test
	public void testGetOnePair(){
	    //m choose f0 but m doesn't reach the low expectation of f0,so return null
	    Female f0 = new Female(0,97,97,97,97,5,20,70,5,3000);
        Female f1 = new Female(1,50,50,30,30,5,20,70,5,1000);
        Male m = new Male(0,20,20,20,20,20,20,30,30);
        ArrayList<Female> female = new ArrayList<Female>();
        ArrayList<Male> male = new ArrayList<Male>();
        female.add(f0);
        female.add(f1);
        male.add(m);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()==null);
        
        //m2 choose f2 and m2 does reach the low expectation of f2,so return the matcher f2---m2
        Female f2 = new Female(2,97,97,97,97,5,20,70,5,2000);
        Female f3 = new Female(3,50,50,30,30,5,20,70,5,1000);
        Male m2 = new Male(1,20,20,20,20,20,20,30,30);
        female.clear();
        male.clear();
        female.add(f2);
        female.add(f3);
        male.add(m2);
        MakePair.setFemale(female);
        MakePair.setMale(male);
        assertTrue(MakePair.getOnePair()[0].getId()==1);
        assertTrue(MakePair.getOnePair()[1].getId()==2);
		
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
	    MakePair.showAllResult();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MakePair.clearData();
	}

}
