package model;
/**
 * @class Person
 * @description Each person has his id,looks,character,wealth,exceptLooks,
 * 				expectCharacter,expectWealth.etc
 * @author qyh
 *
*/
public class Person {
	private int id;
	private int looks;
	private int character;
	private int wealth;
	private int health;
	private int expectLooks;
	private int expectCharacter;
	private int expectWealth;
	private int expectHealth;
	
	/**
	 * Constructor
	 * @param id
	 * @param looks
	 * @param character
	 * @param wealth
	 * @param expectLooks
	 * @param expectCharacter
	 * @param expectWealth
	 */
	public Person(int id,int wealth,int looks,int character,int health,
	        int expectWealth,int expectLooks,int expectCharacter,int expectHealth){
		this.id = id;
        this.wealth = wealth;
		this.looks = looks;
        this.character = character;
		this.health = health;
        this.expectWealth = expectWealth;
		this.expectLooks = expectLooks;
		this.expectCharacter = expectCharacter;
		this.expectHealth = expectHealth;
	}

	public int getId() {
		return id;
	}

	public int getLooks() {
		return looks;
	}

	public int getWealth() {
		return wealth;
	}

	public int getCharacter() {
		return character;
	}

	public int getExpectLooks() {
		return expectLooks;
	}

	public int getExpectWealth() {
		return expectWealth;
	}

	public int getExpectCharacter() {
		return expectCharacter;
	}

	public int getHealth()
    {
        return health;
    }

    public int getExpectHealth()
    {
        return expectHealth;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id +","+this.looks+","+this.character+","
				+this.wealth+","+this.health+","+this.expectLooks+","
				+this.expectCharacter+","+this.expectWealth+this.expectHealth+",";
	}
	
	
	
	
}
