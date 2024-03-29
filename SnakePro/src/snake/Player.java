package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Snake{
	//Player attributes ------------------------------------------------------------
	public static int[] Pos1 = {0,0};
	public static int[] Pos2 = {SnakePro.xPcs-1,0};
	public static int[] Pos3 = {0,SnakePro.yPcs-1};
	public static int[] Pos4 = {SnakePro.xPcs-1,SnakePro.yPcs-1};
	public static int[] Pos5 = {SnakePro.xPcs/2,0};
	public static int[] Pos6 = {SnakePro.xPcs-1,SnakePro.yPcs/2};
	public static int[] Pos7 = {SnakePro.xPcs/2,SnakePro.yPcs-1};
	public static int[] Pos8 = {0,SnakePro.yPcs/2};
	
	public static int[] Rev = {SnakePro.xPcs/2,SnakePro.yPcs/2};
	/**
	public static int[] Rev1 = {0,0};
	public static int[] Rev2 = {SnakePro.xPcs-1,0};
	public static int[] Rev3 = {0,SnakePro.yPcs-1};
	public static int[] Rev4 = {SnakePro.xPcs-1,SnakePro.yPcs-1};
	public static int[] Rev5 = {SnakePro.xPcs/2,0};
	public static int[] Rev6 = {SnakePro.xPcs-1,SnakePro.yPcs/2};
	public static int[] Rev7 = {SnakePro.xPcs/2,SnakePro.yPcs-1};
	public static int[] Rev8 = {0,SnakePro.yPcs/2};
	public static int[][] Revs = {Rev1, Rev2, Rev3, Rev4, Rev5, Rev6, Rev7, Rev8};
	**/
	public static Color Col1 = new Color(0, 0, 255);
	public static Color Col2 = new Color(255,  0, 60);
	public static Color Col3 = new Color(255,255,  0);
	public static Color Col4 = new Color(239, 49,165);
	public static Color Col5 = new Color(0, 0, 120);
	public static Color Col6 = new Color(120,  0, 30);
	public static Color Col7 = new Color(120,120,  0);
	public static Color Col8 = new Color(80, 20,35);
	
	public Color color;
	public boolean alive = true;
	public int Item = 0;
	public int Playernum;
	public int score = 0;
	public Statistics stats;

	public Player(int PlayerNum, Image img, int lenght, String name, String controller) throws SlickException {
		super();
		startLenght = lenght;
		originalHead = img;
		Playernum = PlayerNum;
		score = 0;
		stats = new Statistics(name, controller);
		switch (PlayerNum) {
		case 1:
			startPos = new Piece(Pos1[0], Pos1[1]);
			startDir = Direction.right;
			color = Col1;
			break;
		case 2:
			startPos = new Piece(Pos2[0], Pos2[1]);
			startDir = Direction.left;
			color = Col2;
			break;
		case 3:
			startPos = new Piece(Pos3[0], Pos3[1]);
			startDir = Direction.right;
			color = Col3;
			break;
		case 4:
			startPos = new Piece(Pos4[0], Pos4[1]);
			startDir = Direction.left;
			color = Col4;
			break;
		case 5:
			startPos = new Piece(Pos5[0], Pos5[1]);
			startDir = Direction.down;
			color = Col5;
			break;
		case 6:
			startPos = new Piece(Pos6[0], Pos6[1]);
			startDir = Direction.left;
			color = Col6;
			break;
		case 7:
			startPos = new Piece(Pos7[0], Pos7[1]);
			startDir = Direction.up;
			color = Col7;
			break;
		case 8:
			startPos = new Piece(Pos8[0], Pos8[1]);
			startDir = Direction.right;
			color = Col8;
			break;
		}
		reset();
		pieces.clear();
		for(int i=0; i<startLenght; i++) {
			pieces.add(new Piece(-170+startPos.xPos, -170+startPos.yPos));
		}
		pieces.add(startPos.copy());
	}
	
	@Override
	public void grow(int rate) {
		if(alive) {
			SnakePro.soundsEat[SnakePro.random.nextInt(SnakePro.soundsEat.length)].play();
			super.grow(rate);
		}
	}
	
	public void setDead(int score, Dead cause, Player killer) {
		if(alive) {
			if(this != killer && cause != Dead.Boss) {
				killer.stats.addKill(cause);
			}
			if(Item != 1) {
				SnakePro.soundsDie[SnakePro.random.nextInt(SnakePro.soundsDie.length)].play();
				this.stats.addDeath(cause);
				this.alive = false;
				super.setDead();
				this.score = score;
			}else {
				this.revive();
				this.score = score;
			}
			Item = 0;
		}
	}
	
	public void revive() {
		int[] revivePos = Rev;
		SnakePro.soundsRev[SnakePro.random.nextInt(SnakePro.soundsRev.length)].play();
		pieces.clear();
		for(int i=0; i<startLenght; i++) {
			pieces.add(new Piece(startPos.xPos-100,startPos.yPos-100));
		}
		//pieces.add(new Piece(Revs[Playernum-1][0], Revs[Playernum-1][1]));
		pieces.add(new Piece(revivePos[0], revivePos[1]));
	}
	
	@Override
	public boolean collidesWithSelf() {
		if(alive) {
			return super.collidesWithSelf();
		}
		return false;
	}
	
	@Override
	public Piece getHead() {
		if(alive) {
			return super.getHead();
		}
		return null;
	}
	
	@Override
	public void changeDirection() {
		if(alive) {
			super.changeDirection();
		}
	}
	
	@Override
	public void reset() throws SlickException {
		super.reset();
		alive = true;
		Item = 0;
		score = 0;
	}
	
	@Override
	public void update() {
		if(alive) {
			super.update();
		}
	}

}
