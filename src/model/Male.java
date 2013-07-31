package model;

public class Male extends Person
{
	private Female target;
	private int targetScore;
	
    public Male(int id, int wealth, int looks, int character, int health, int expectWealth, int expectLooks,
            int expectCharacter, int expectHealth)
    {
        super(id, wealth, looks, character, health, expectWealth, expectLooks, expectCharacter, expectHealth);
        this.target = null;
        this.targetScore = 0;
    }

	public Female getTarget() {
		return target;
	}

	public void setTarget(Female target) {
		this.target = target;
	}

	public int getTargetScore() {
		return targetScore;
	}

	public void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}

}
