package snake;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.PathFindingOnSquaredGrid;

public class Bot extends Snake{
	public static Direction[] dirs = {Direction.up, Direction.down, Direction.left, Direction.right};
	
	public int Owner;

	public Bot(Image Head, int Owner, Player own, int startLenght, List<Player> pls) throws SlickException {
		super();
		this.Owner = Owner;
		startPos = own.getHead();
		startDir = own.dir; dir = startDir;nextDir = dir;
		this.startLenght = startLenght;
		originalHead = Head;
		head = new ImgObject(originalHead, startPos.xPos, startPos.yPos);
		head.rescale(pW*headScale, pH*headScale);
		pieces.clear();
		for(int i=startLenght; i>0; i--) {
			pieces.add(own.pieces.get(own.pieces.size()-i));
		}
	}
	
	public void calcDir(List<Player> pls) {
		double distance = 100000000.0;
		for(Player p : pls) {
			if(p.alive && pls.indexOf(p) != Owner) {
				Piece pH = p.getHead();
				Piece bH = getHead();
				double newDis = pH.getDis(bH);
				if(newDis < distance) {
					distance = newDis;
					if(Math.abs(pH.xPos - bH.xPos) > Math.abs(pH.yPos - bH.yPos)) {
						if(pH.xPos<bH.xPos) {
							nextDir = Direction.left;
						}else if(pH.xPos>bH.xPos) {
							nextDir = Direction.right;
						}
					}else {
						if(pH.yPos<bH.yPos) {
							nextDir = Direction.up;
						}else if(pH.yPos>bH.yPos) {
							nextDir = Direction.down;
						}
					}
				}
			}
		}
		changeDirection();
	}
	
	@Override
	public void changeDirection() {
		if( ((nextDir == Direction.up) && (dir == Direction.down)) ||
			((nextDir == Direction.down) && (dir == Direction.up)) ||
			((nextDir == Direction.left) && (dir == Direction.right)) ||
			((nextDir == Direction.right) && (dir == Direction.left)))
		{
			nextDir = dirs[SnakePro.random.nextInt(4)];
			changeDirection();
		}else {
			dir = nextDir;
		}
	}

}
