package snake;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Snake {
	public static int pW = SnakePro.pieceWidth;
	public static int pH = SnakePro.pieceHeight;
	public static int headScale = 2;
	
	
	public List<Piece> pieces = new ArrayList<Piece>();
	public ImgObject head;
	public Direction dir;
	public Direction nextDir;
	
	public Piece startPos;
	public Image originalHead;
	public Direction startDir;
	public int startLenght;

	public Snake(int x, int y, int lenght, Direction direction, Image Head) throws SlickException {
		startPos = new Piece(x, y);
		startDir = direction;
		startLenght = lenght;
		originalHead = Head;
		reset();
	}
	
	public Snake() {}
	
	public void reset() throws SlickException {
		pieces.clear();
		dir = startDir;
		nextDir = dir;
		head = new ImgObject(originalHead, startPos.xPos, startPos.yPos);
		head.rescale(pW*headScale, pH*headScale);
		if(startDir == Direction.up) {
			head.setY(startPos.yPos-startLenght+1);
			head.rotate(90);
			for(int i=0; i<startLenght; i++) {
				pieces.add(new Piece(startPos.xPos, startPos.yPos-i));
			}
		}else if(startDir == Direction.down) {
			head.setY(startPos.yPos+startLenght-1);
			head.rotate(-90);
			for(int i=0; i<startLenght; i++) {
				pieces.add(new Piece(startPos.xPos, startPos.yPos+i));
			}
		}else if(startDir == Direction.left) {
			head.setX(startPos.xPos-startLenght+1);
			for(int i=0; i<startLenght; i++) {
				pieces.add(new Piece(startPos.xPos-i, startPos.yPos));
			}
		}else if(startDir == Direction.right) {
			head.setX(startPos.xPos+startLenght-1);
			head.rotate(180);
			for(int i=0; i<startLenght; i++) {
				pieces.add(new Piece(startPos.xPos+i, startPos.yPos));
			}
		}
	}
	
	public void changeDirection() {
		if( ((nextDir == Direction.up) && (dir == Direction.down)) ||
			((nextDir == Direction.down) && (dir == Direction.up)) ||
			((nextDir == Direction.left) && (dir == Direction.right)) ||
			((nextDir == Direction.right) && (dir == Direction.left)))
		{
			nextDir = dir;
		}else {
			dir = nextDir;
		}
	}
	
	public void update() {
		if(pieces.size()>0) {
			Piece nextPc = pieces.get(pieces.size()-1).copy();
			if(dir == Direction.up) {
				nextPc.yPos -= 1;
				head.rotate(90);
			}else if(dir == Direction.down) {
				nextPc.yPos += 1;
				head.rotate(-90);
			}else if(dir == Direction.left) {
				nextPc.xPos -= 1;
				head.rotate(0);
			}else if(dir == Direction.right) {
				nextPc.xPos += 1;
				head.rotate(180);
			}
			pieces.add(nextPc);
			head.x = nextPc.xPos;
			head.y = nextPc.yPos;
			pieces.remove(0);
		}
	}
	
	public void grow(int rate) {
		Piece p = pieces.get(0);
		for(int i=0; i<rate; i++) {
			pieces.add(0, p.copy());;
		}
	}
	
	public Piece getHead() {
		return pieces.get(pieces.size()-1);
	}
	
	public boolean collidesWithSelf() {
		if(getHead().inList(pieces) > 1) {
			return true;
		}
		return false;
	}
	
	public void setDead() {
		this.pieces.clear();
		this.dir = startDir;
		this.nextDir = startDir;
	}

	public List<ImgObject> getImgs() {
		List<ImgObject> l = new ArrayList<ImgObject>();
		l.add(head);
		return l;
	}
	
	public List<Piece> getPieces() {
		List<Piece> l = new ArrayList<Piece>();
		for(int i=0; i < pieces.size()-1; i++) {
			l.add(pieces.get(i));
		}
		return l;
	}
}
