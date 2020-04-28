package snake;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.PathFinding;

public class Boss extends Snake{
	public static Direction[] dirs = {Direction.up, Direction.down, Direction.left, Direction.right};
	public static int maxTries = 20;
	public int pieceTry = 0;
	public int time;
	public boolean[][] matrix;
	
	public Boss(Image Head, int startLenght, int x, int y, int BossTime) throws SlickException {
		super();
		time = BossTime;
		startPos = new Piece(x, y);
		startDir = dirs[SnakePro.random.nextInt(4)];
		this.startLenght = startLenght;
		originalHead = Head;
		pieces.clear();
		dir = startDir;
		nextDir = dir;
		head = new ImgObject(originalHead, startPos.xPos, startPos.yPos);
		head.rescale(pW*headScale, pH*headScale);
		if(startDir == Direction.up) {
			head.rotate(90);
		}else if(startDir == Direction.down) {
			head.rotate(-90);
		}else if(startDir == Direction.right) {
			head.rotate(180);
		}
		for(int i=0; i<startLenght; i++) {
			pieces.add(new Piece(startPos.xPos-200, startPos.yPos-200));
		}
		pieces.add(startPos);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public Direction getDirToPc(Piece pc) {
		Piece hd =getHead();
		int xdis = hd.xPos-pc.xPos;
		int ydis = hd.yPos-pc.yPos;
		Direction next = dirs[SnakePro.random.nextInt(4)];
		if(Math.abs(xdis) > Math.abs(ydis)) {
			if(xdis > 0) {
				next = Direction.left;
			}else if(xdis < 0) {
				next = Direction.right;
			}
		}else {
			if(ydis > 0) {
				next = Direction.up;
			}else if(ydis < 0) {
				next = Direction.down;
			}
		}
		return next;
	}
	
	public void goToPiece(Piece pc, boolean[][] mat, Piece target) {
		if(pieceTry < maxTries && pc.inBorders(0, 0, SnakePro.xPcs, SnakePro.yPcs)) {
			if(mat[pc.xPos][pc.yPos]) {
				nextDir = getDirToPc(pc);
			}else {
				nextDir = calcDir(mat, target);
			}
		}
	}
	
	public void calcDirFast(boolean [][] mat, Piece target) {
		matrix = mat;
		Piece hd = getHead();
		int xdis = hd.xPos-target.xPos;
		int ydis = hd.yPos-target.yPos;
		nextDir = dirs[SnakePro.random.nextInt(4)];
		pieceTry = 0;
		if(Math.abs(xdis) > Math.abs(ydis)) {
			if(xdis > 0) {
				goToPiece(new Piece(hd.xPos-1, hd.yPos), mat, target);
			}else if(xdis < 0) {
				goToPiece(new Piece(hd.xPos+1, hd.yPos), mat, target);
			}
		}else {
			if(ydis > 0) {
				goToPiece(new Piece(hd.xPos, hd.yPos-1), mat, target);
			}else if(ydis < 0) {
				goToPiece(new Piece(hd.xPos, hd.yPos+1), mat, target);
			}
		}
		changeDirection();
	}
	
	public void calcDirSlow(boolean [][] mat, Piece target) {
		matrix = mat;
		nextDir = calcDir(mat, target);
		changeDirection();
	}

	public Direction calcDir(boolean [][] mat, Piece target) {
		Direction next = dirs[SnakePro.random.nextInt(4)];
		if(target != null) {
			Piece bhd = getHead();
			Piece phd = target;
	    	int[] Ai = {bhd.xPos, bhd.yPos};
	    	int[] Bi = {phd.xPos, phd.yPos};
	    	mat[Ai[0]][Ai[1]] = true;
	    	mat[Bi[0]][Bi[1]] = true;
	    	
			ArrayList<Node> path = PathFinding.getShortestNodes(mat, Ai, Bi, 3);
	    	String[] symbols = {"  ", "||", "AA", "BB", "->"};
			//System.out.println(PathFinding.matrixToString(
			//		PathFinding.getShortestPath(mat, symbols, Ai, Bi, 3)));
			
			if(path.size() > 1) {
				int xdir = path.get(0).x-path.get(1).x;
				int ydir = path.get(0).y-path.get(1).y;
				if(xdir > 0) {
					next = Direction.left;
				}else if(xdir < 0) {
					next = Direction.right;
				}else if(ydir > 0) {
					next = Direction.up;
				}else if(ydir < 0) {
					next = Direction.down;
				}
			}
		}
		return next;
	}
	
	@Override
	public void changeDirection() {
		/**if( ((nextDir == Direction.up) && (dir == Direction.down)) ||
			((nextDir == Direction.down) && (dir == Direction.up)) ||
			((nextDir == Direction.left) && (dir == Direction.right)) ||
			((nextDir == Direction.right) && (dir == Direction.left)))
		{
			nextDir = dirs[SnakePro.random.nextInt(4)];
			changeDirection();
		}else {
			dir = nextDir;
		}**/
		dir = nextDir;
	}
}
