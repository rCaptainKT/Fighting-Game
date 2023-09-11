/*
 * Fighter.java
 * 
 * Creates basic fighter
 */

package fightingGame;

public abstract class Fighter {

	//Properties
	private String name; //Fighter's name
	private int maxhp; //Fighter's maximum HP
	private int hp; //Fighter's current HP
	private int Batk; //Fighter's base attack
	private int atk; //Fighter's current attack
	private int Bdef; //Fighter's base defence
	private int def; //Fighter's current defence
	private int spd; //Fighter's current speed
	private Type type; //Fighter's type
	private FighterClass fclass; //Fighter's class
	private String moveset[] = new String[5]; //Name's of fightter's moveset
	private int ultChrg; //Ultimate's current charge
	private boolean maxatk; //States if fighter has reached max attack
	private boolean maxdef; //States if fighter has reached max defence
	private boolean maxspd; //States if fighter has reached max speed
	private boolean custom; //States if fighter is custom made
	
	//Constructor
	public Fighter(String name, int maxhp, int Batk, int Bdef, int spd, Type type, boolean custom) {
		this.name = name;
		this.maxhp = maxhp;
		this.hp = this.maxhp;
		this.Batk = Batk;
		this.atk = this.Batk;
		this.Bdef = Bdef;
		this.def = this.Bdef;
		this.spd = spd;
		this.type = type;
		this.ultChrg = 0;
		this.maxatk = false;
		this.maxdef = false;
		this.maxspd = false;
		this.custom = custom;
	}
	
	//Get fighter's name
	public String getName() {
		return this.name;
	}
	
	//Get fighter's HP
	public int getHP() {
		return this.hp;
	}
	
	//heal fighter and restore HP
	public void healHP(int HP) {
		if (this.hp + HP > this.maxhp) {
			this.hp = this.maxhp;
		}else {
			this.hp += HP;
		}
	}
	
	//Remove fighter's HP
	public void loseHP(int dmg) {
		if (this.hp - dmg < 0) {
			this.hp = 0;
		}else {
			this.hp = this.hp - dmg;
		}
	}
	
	//Get fighter's max HP
	public int getMaxHP() {
		return this.maxhp;	
	}
	
	//Get fighter's attack
	public int getATK() {
		return this.atk;
	}
	
	//Increase fighter's attack
	public void incATK() {
		if (this.atk != this.Batk + 30) {
			this.atk += 5;
			if (this.atk == this.Batk + 30) {
				this.maxatk = true;
			}
		}
	}
	
	//Check if fighter has max attack
	public boolean isMaxATK() {
		return this.maxatk;
	}
	
	//Get fighter's defence
	public int getDEF() {
		return this.def;
	}
	
	//Increase fighter's defence
	public void incDEF() {
		if (this.def != this.Bdef + 30) {
			this.def += 5;
			if (this.def == this.Bdef + 30) {
				this.maxdef = true;
			}
		}
	}
	
	//Check if fighter has max defence
	public boolean isMaxDEF() {
		return this.maxdef;
	}
	
	//Get fighter's speed
	public int getSPD() {
		return this.spd;
	}
	
	//Increase fighter's speed
	public void incSPD() {
		if (this.spd != 10) {
			this.spd++;
			if (this.spd == 10) {
				this.maxspd = true;
			}
		}
		
	}
	
	//Check if fighter has max speed
	public boolean isMaxSPD() {
		return this.maxspd;
	}
	
	//Get fighter's ULTIMATE charge
	public int getChrg() {
		return this.ultChrg;
	}
	
	//Increase fighter's ULTIMATE charge
	public void chrgUp() {
		if (this.ultChrg < 5) {
			this.ultChrg++;	
		}
	}
	
	//Reset fighter's ULTIMATE charge
	public void chrgReset() {
		this.ultChrg = 0;
	}
	
	//Get fighter's type
	public Type getType() {
		return this.type;
	}
	
	//Get fighter's fighting class
	public FighterClass getFClass() {
		return this.fclass;
	}
	
	//Set fighter's fighting class
	public void setFClass(FighterClass fclass) {
		this.fclass = fclass;
		
		//Adjust stats and names based on fclass
		if (fclass == FighterClass.cannon) {
			this.Bdef -= 20;
			this.def -= 20;
			this.Batk += 20;
			this.atk += 20;
			this.name = name + " (Cannon)";
		}else if (fclass == FighterClass.wall) {
			this.Bdef += 20;
			this.def += 20;
			this.Batk -= 20;
			this.atk -= 20;
			this.name = name + " (Wall)";
		}else {
			this.name = name + " (Standard)";
		}
	}
	
	//Set fighter's moveset
	public void setMoveset(String move1, String move2, String move3, String move4, String move5) {
		this.moveset[0] = move1;
		this.moveset[1] = move2;
		this.moveset[2] = move3;
		this.moveset[3] = move4;
		this.moveset[4] = move5;
	}
	
	//Get fighter's moveset
	public String[] getMoveset() {
		return this.moveset;
	}
	
	//Get name of one of fighter's moves
	public String getMove(int slot) {
		return this.moveset[slot - 1];
	}
	
	//Check if fighter is custom
	public boolean isCustom() {
		return this.custom;
	}
}
