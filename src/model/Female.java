package model;

public class Female extends Person
{
    private int expectScore;
    
    public int getExpectScore()
    {
        return expectScore;
    }

    public void setExpectScore(int expectScore)
    {
        this.expectScore = expectScore;
    }

    public Female(int id, int wealth, int looks, int character, int health, int expectWealth, int expectLooks,
            int expectCharacter, int expectHealth,int expectScore)
    {
        super(id, wealth, looks, character, health, expectWealth, expectLooks, expectCharacter, expectHealth);
        this.expectScore = expectScore;
    }
    
}
