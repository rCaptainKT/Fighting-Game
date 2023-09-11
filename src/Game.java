/*
 * Fighting Game
 * Created by Kai Tan
 * 
 * Requires:
 * Fighter.java
 * FighterClass.java
 * Type.java
 * Water.java
 * Fire.java
 * Plant.java
 */

package fightingGame;

import java.util.Scanner;
import java.util.Random;

public class Game {

	//Input scanner
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Select fighter
		Fighter player = selectFighter();
		
		//Select opponent
		Fighter computer = selectDifficulty(player);
		
		//Start game
		startGame(player, computer);
	}

	//Runs main game loop
	public static void startGame(Fighter player, Fighter computer) {
		while (true) {
			//Creates divider
			String divider = "";
			for (int i = 0; i < 50; i++) {
				divider = divider + "=";
			}
			System.out.println(divider);
			System.out.println();
			
			//States player's and computer's HP
			System.out.println("[YOU] " + player.getName() + ": " + player.getHP() + "HP");
			System.out.println("[COM] " + computer.getName() + ": " + computer.getHP() + "HP");
			System.out.println();
			
			//List player's moveset
			System.out.println("Select Move:");
			System.out.println("1 - " + player.getMove(1));
			System.out.println("2 - " + player.getMove(2));
			System.out.println("3 - " + player.getMove(3));
			System.out.println("4 - " + player.getMove(4));
			
			int move;
			int comMove;
			
			//
			if (player.getChrg() == 5) {
				System.out.println("5 - [ULTIMATE]" + player.getMove(5));
				move = selection(1, 5);
			}else {
				System.out.println("CHARGING(" + player.getChrg() + "/5) - [ULTIMATE]" + player.getMove(5));
				while (true) {
					move = selection(1, 5);
					if (move == 5) {
						System.out.println("ULTIMATE is still charging.");
					}else {
						break;
					}
				}
			}
			System.out.println();
			
			if (computer.getChrg() == 5) {
				comMove = new Random().nextInt(5) + 1;
			}else {
				comMove = new Random().nextInt(4) + 1;
			}
			
			if (player.getSPD() > computer.getSPD()) {
				move(player, computer, move, true);
				checkWin(player, computer);
				move(computer, player, comMove, false);
				checkWin(player, computer);
			}else if (player.getSPD() < computer.getSPD()) {
				move(computer, player, comMove, false);
				checkWin(player, computer);
				move(player, computer, move, true);
				checkWin(player, computer);
			}else {
				if (new Random().nextInt(2) == 0) {
					move(player, computer, move, true);
					checkWin(player, computer);
					move(computer, player, comMove, false);
					checkWin(player, computer);
				}else {
					move(computer, player, comMove, false);
					checkWin(player, computer);
					move(player, computer, move, true);
					checkWin(player, computer);
				}
			}
			System.out.println();
		}
			
	}
	
	public static void move(Fighter fighter, Fighter opponent, int move, boolean isPlayer) {
		String turn = null;
		if (isPlayer) {
			turn = "[YOU] ";
		}else {
			turn = "[COM] ";
		}
		
		int atk = fighter.getATK();
		
		if (move == 1) {
			System.out.println(turn + fighter.getName() + " used " + fighter.getMove(1) + ".");
			delay();
			
			atk = atk * 1;
			
			Random r = new Random();
			int rand = r.nextInt(10);
			if (rand == 9 || rand == 8) {
				System.out.println(turn + fighter.getName() + " missed their attack.");
				delay();
			}else {
				if (opponent.getType() == Type.Water) {
					((Water)opponent).damage(atk, fighter.getType());
				}else if (opponent.getType() == Type.Fire) {
					((Fire)opponent).damage(atk, fighter.getType());
				}else if (opponent.getType() == Type.Plant) {
					((Plant)opponent).damage(atk, fighter.getType());
				}
			}
			
			fighter.chrgUp();
		}else if (move == 2) {
			System.out.println(turn + fighter.getName() + " used " + fighter.getMove(2) + ".");
			delay();
			
			atk = atk * 7 / 10;
			
			if (opponent.getType() == Type.Water) {
				((Water)opponent).damage(atk, fighter.getType());
			}else if (opponent.getType() == Type.Fire) {
				((Fire)opponent).damage(atk, fighter.getType());
			}else if (opponent.getType() == Type.Plant) {
				((Plant)opponent).damage(atk, fighter.getType());
			}
			
			fighter.chrgUp();
		}else if (move == 3) {
			System.out.println(turn + fighter.getName() + " used " + fighter.getMove(3) + ".");
			delay();
			
			if (fighter.isMaxATK()) {
				System.out.println(turn + fighter.getName() + "'s attack could not increase any further.");
				delay();
			}else {
				fighter.incATK();
				System.out.println(turn + fighter.getName() + "'s attack increased.");
				delay();
			}
			
			Random r = new Random();
			int rand = r.nextInt(10);
			if (rand == 0 || rand == 1 || rand == 2) {
				if (fighter.isMaxSPD()) {
					System.out.println(turn + fighter.getName() + "'s speed could not increase any further.");
					delay();
				}else {
					fighter.incSPD();
					System.out.println(turn + fighter.getName() + "'s speed increased.");
					delay();
				}
			}else {
				System.out.println(turn + fighter.getName() + "'s speed did not change.");
				delay();
			}
		}else if (move == 4) {
			System.out.println(turn + fighter.getName() + " used " + fighter.getMove(4) + ".");
			delay();
			
			if (fighter.isMaxDEF()) {
				System.out.println(turn + fighter.getName() + "'s defence could not increase any further.");
				delay();
			}else {
				fighter.incDEF();
				System.out.println(turn + fighter.getName() + "'s defence increased.");
				delay();
			}
			
			Random r = new Random();
			int rand = r.nextInt(10);
			if (rand == 0 || rand == 1 || rand == 2) {
				int temp = fighter.getHP();
				
				if (fighter.getType() == Type.Water) {
					((Water)fighter).heal();
				}else if (fighter.getType() == Type.Fire) {
					((Fire)fighter).heal();
				}else if (fighter.getType() == Type.Plant) {
					((Plant)fighter).heal();
				}
				
				if (fighter.getHP() == temp) {
					System.out.println(turn + fighter.getName() + " is already at max HP.");
					delay();
				}else {
					System.out.println(turn + fighter.getName() + " healed " + (fighter.getHP() - temp) + "HP.");
					delay();
				}
			}else {
				if (fighter.getHP() == fighter.getMaxHP()) {
					System.out.println(turn + fighter.getName() + " is already at max HP.");
					delay();
				}else {
					System.out.println(turn + fighter.getName() + " did not heal any HP.");
					delay();
				}
			}
		}else if (move == 5) {
			System.out.println(turn + fighter.getName() + " used [ULTIMATE]" + fighter.getMove(5) + ".");
			delay();
			
			atk = atk + (atk/2);
			
			if (opponent.getType() == Type.Water) {
				((Water)opponent).damage(atk, fighter.getType());
			}else if (opponent.getType() == Type.Fire) {
				((Fire)opponent).damage(atk, fighter.getType());
			}else if (opponent.getType() == Type.Plant) {
				((Plant)opponent).damage(atk, fighter.getType());
			}
			
			fighter.chrgReset();
		}
	}
	
	public static void checkWin(Fighter player, Fighter computer) {
		if (player.getHP() == 0) {
			System.out.println("[YOU] " + player.getName() + " has been defeated.");
			delay();
			System.out.println("YOU LOSE");
			System.exit(0);
		}else if (computer.getHP() == 0) {
			System.out.println("[COM] " + computer.getName() + " has been defeated.");
			delay();
			System.out.println("YOU WIN");
			System.exit(0);
		}
	}

	public static void delay() {
		try {
			Thread.sleep(2900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static Fighter selectFighter() {
		System.out.println("Select Fighter Type:");
		System.out.println("1 - Fire");
		System.out.println("2 - Water");
		System.out.println("3 - Plant");
		int type = selection(1, 3);
		System.out.println();
		
		System.out.println("Select Fighter Class:");
		System.out.println("1 - Standard");
		System.out.println("2 - Cannon");
		System.out.println("3 - Wall");
		int fclass = selection(1, 3);
		System.out.println();
		
		Fighter player = null;
		
		if (type == 1) {
			player = new Fire("Fire Fighter", 300, 80, 30, 6, false);
		}else if (type == 2) {
			player = new Water("Water Fighter", 320, 70, 35, 7, false);
		}else if (type == 3) {
			player = new Plant("Plant Fighter", 340, 60, 40, 5, false);
		} 
		
		if (fclass == 1) {
			player.setFClass(FighterClass.standard);
		}else if (fclass == 2) {
			player.setFClass(FighterClass.cannon);
		}else if (fclass == 3) {
			player.setFClass(FighterClass.wall);
		}
		
		setMoveset(player);
		
		return player;
	}
	
	public static Fighter selectDifficulty(Fighter player) {
		System.out.println("Select Difficulty:");
		System.out.println("1 - Easy");
		System.out.println("2 - Medium");
		System.out.println("3 - Hard");
		int diff = selection(1, 3);
		System.out.println();
		
		Fighter computer = null;
		
		if (diff == 1) {
			if (player.getType() == Type.Fire) {
				computer = new Plant("Plant Fighter", 340, 60, 40, 5, false);
			}else if (player.getType() == Type.Water) {
				computer = new Fire("Fire Fighter", 300, 80, 30, 6, false);
			}else if (player.getType() == Type.Plant) {
				computer = new Water("Water Fighter", 320, 70, 35, 7, false);
			}
			
			computer.setFClass(FighterClass.standard);
		}else if (diff == 2) {
			Random r = new Random();
			
			int type = r.nextInt(3);
			if (type == 0) {
				computer = new Fire("Fire Fighter", 300, 80, 30, 6, false);
			}else if (type == 1) {
				computer = new Water("Water Fighter", 320, 70, 35, 7, false);
			}else if (type == 2) {
				computer = new Plant("Plant Fighter", 340, 60, 40, 5, false);
			}
			
			int fclass = r.nextInt(3);			
			if (fclass == 0) {
				computer.setFClass(FighterClass.standard);
			}else if (fclass == 1) {
				computer.setFClass(FighterClass.cannon);
			}else if (fclass == 2) {
				computer.setFClass(FighterClass.wall);
			}
		}else if (diff == 3) {
			if (player.getType() == Type.Fire) {
				computer = new Water("Water Fighter", 320, 70, 35, 7, false);
			}else if (player.getType() == Type.Water) {
				computer = new Plant("Plant Fighter", 340, 60, 40, 5, false);
			}else if (player.getType() == Type.Plant) {
				computer = new Fire("Fire Fighter", 300, 80, 30, 6, false);
			}
			
			if (player.getFClass() == FighterClass.cannon) {
				computer.setFClass(FighterClass.wall);
			}else if (player.getFClass() == FighterClass.wall) {
				computer.setFClass(FighterClass.cannon);
			}else if (player.getFClass() == FighterClass.standard) {
				Random r = new Random();
				
				int fclass = r.nextInt(2);
				if (fclass == 0) {
					computer.setFClass(FighterClass.cannon);
				}else if (fclass == 1) {
					computer.setFClass(FighterClass.wall);
				}
			}
		}
		
		setMoveset(computer);
		
		return computer;
	}
	
	public static int selection(int lower, int upper) {
		while (true) {
			String input = sc.next();
			try {
				int input2 = Integer.parseInt(input);
				if (input2 <= upper && input2 >= lower) {
					return input2;
				}else {
					throw new Exception();
				}	
			}catch (Exception e) {
				System.out.println("Invalid Input");
			}
		}
		
	}
	
	public static void setMoveset(Fighter fighter) {
		Type type = fighter.getType();
		FighterClass fclass = fighter.getFClass();
		
		if (type == Type.Fire) {
			if (fclass == FighterClass.standard) {
				fighter.setMoveset("Fire Ball", "Fire Punch", "Heat It Up", "Unwavering Flame", "Flash Fire");
			}else if (fclass == FighterClass.cannon) {
				fighter.setMoveset("Flamethrower", "Fire Punch", "Heat It Up", "Unwavering Flame", "Blazing Inferno");
			}else if (fclass == FighterClass.wall) {
				fighter.setMoveset("Fire Ball", "Blazing Slash", "Heat It Up", "Unwavering Flame", "Incineration");
			}
		}else if (type == Type.Water) {
			if (fclass == FighterClass.standard) {
				fighter.setMoveset("Douse", "Water Punch", "Scalding Water", "Hard Water", "Whirlpool");
			}else if (fclass == FighterClass.cannon) {
				fighter.setMoveset("Geyser", "Water Punch", "Scalding Water", "Hard Water", "Water Bombardment");
			}else if (fclass == FighterClass.wall) {
				fighter.setMoveset("Douse", "Hydro Slash", "Scalding Water", "Hard Water", "Tsunami");
			}
		}else if (type == Type.Plant) {
			if (fclass == FighterClass.standard) {
				fighter.setMoveset("Leaf Barrage", "Vine Whip", "Nature's Fury", "Cellulose Synthesis", "Nature's Revenge");
			}else if (fclass == FighterClass.cannon) {
				fighter.setMoveset("Leaf Tornado", "Vine Whip", "Nature's Fury", "Cellulose Synthesis", "Mother Nature's Wrath");
			}else if (fclass == FighterClass.wall) {
				fighter.setMoveset("Leaf Barrage", "Thistle Slash", "Nature's Fury", "Cellulose Synthesis", "Overgrowth");
			}
		}
	}
}
