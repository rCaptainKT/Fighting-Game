/*
 * Water.java
 * 
 * Creates water fighter
 */

package fightingGame;

public class Water extends Fighter {
	
	//Constructor
	public Water(String name, int hp, int atk, int def, int spd, boolean custom) {
		super(name, hp, atk, def, spd, Type.Water, custom);
	}

	//Calculates damage taken
	public void damage(int dmg, Type move) {
		int fdmg;

		switch(move) {
		case Fire:
			fdmg = dmg * 2 / 3 - this.getDEF();			
			if (fdmg < dmg/4) {
				fdmg = dmg/4;
			}
			this.loseHP(fdmg);
			break;
		case Water:
			fdmg = dmg * 1 - this.getDEF();
			this.loseHP(fdmg);
			break;
		case Plant:
			fdmg = dmg * 3 / 2 - this.getDEF();
			this.loseHP(fdmg);
			break;
		}
	}
	
	//Heal fighter
	public void heal() {
		healHP(50);
	}
}
