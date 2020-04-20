package snake;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.PathFindingOnSquaredGrid;

public class Boss extends Snake{
	public static Direction[] dirs = {Direction.up, Direction.down, Direction.left, Direction.right};
	
	public Boss(Image Head, int startLenght, int x, int y) throws SlickException {
		super();
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

	public void calcDir(boolean [][] mat, Piece target) {
		nextDir = dirs[SnakePro.random.nextInt(4)];
		if(target != null) {
			Piece bhd = getHead();
			Piece phd = target;
	    	int[] Ai = {bhd.xPos, bhd.yPos};
	    	int[] Bi = {phd.xPos, phd.yPos};
	    	mat[Ai[0]][Ai[1]] = true;
	    	mat[Bi[0]][Bi[1]] = true;
	    	
			ArrayList<Node> path = PathFindingOnSquaredGrid.getShortestNodes(mat, Ai, Bi, 3);
	    	String[] symbols = {"  ", "||", "AA", "BB", "->"};
			//System.out.println(PathFindingOnSquaredGrid.matrixToString(
			//		PathFindingOnSquaredGrid.getShortestPath(mat, symbols, Ai, Bi, 3)));
			
			if(path.size() > 1) {
				int xdir = path.get(0).x-path.get(1).x;
				int ydir = path.get(0).y-path.get(1).y;
				if(xdir > 0) {
					nextDir = Direction.left;
				}else if(xdir < 0) {
					nextDir = Direction.right;
				}else if(ydir > 0) {
					nextDir = Direction.up;
				}else if(ydir < 0) {
					nextDir = Direction.down;
				}
			}
		}
		changeDirection();
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
