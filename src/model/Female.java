package model;

public class Female extends Person
{

	private Male target;
	private int targetScore;
    private int expectScore;
    private int voteBox;//to record the number of vote in one round
    

    public Female(int id, int wealth, int looks, int character, int health, int expectWealth, int expectLooks,
            int expectCharacter, int expectHealth,int expectScore)
    {
        super(id, wealth, looks, character, health, expectWealth, expectLooks, expectCharacter, expectHealth);
        this.target = null;
        this.targetScore = 0;
        this.expectScore = expectScore;
        resetVoteBox();
    }
    
    public void resetVoteBox(){
    	this.voteBox=0;
    }
    
    public void addVote(){
    	this.voteBox++;
    }

    public int getVoteBox() {
		return voteBox;
	}

	public int getExpectScore()
    {
        return expectScore;
    }

    public void setExpectScore(int expectScore)
    {
        this.expectScore = expectScore;
    }

	public Male getTarget() {
		return target;
	}

	public void setTarget(Male target) {
		this.target = target;
	}

	public int getTargetScore() {
		return targetScore;
	}

	public void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}
}
