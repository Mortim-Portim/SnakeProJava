package snake;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

public class Laser {
	public static int pW = SnakePro.pieceWidth;
	public static int pH = SnakePro.pieceHeight;
	public static float thickness = 0.5f;
	
	public List<Piece> pieces = new ArrayList<Piece>();
	public int remainingTime;
	private int startingTime;

	public Laser(Piece start, int time, Direction dir) {
		for(int i=0; i<time; i++) {
			switch (dir) {
			case up:
				pieces.add(new Piece(start.xPos, start.yPos-i-2));
				break;
			case down:
				pieces.add(new Piece(start.xPos, start.yPos+i+2));
				break;
			case left:
				pieces.add(new Piece(start.xPos-i-2, start.yPos));
				break;
			case right:
				pieces.add(new Piece(start.xPos+i+2, start.yPos));
				break;
			}
		}
		this.remainingTime = time;
		this.startingTime = time;
	}
	
	public int hits(Player pl) {
		int hitpc = -1;
		for(Piece p : pieces) {
			for(Piece p2 :pl.pieces) {
				if(p.xPos == p2.xPos && p.yPos == p2.yPos && pl.pieces.indexOf(p2)>=hitpc) {
					hitpc = pl.pieces.indexOf(p2);
				}
			}
		}
		return hitpc;
	}
	
	public void draw(Graphics g) {
		g.setColor(SnakePro.laserCol);
		for(Piece p: pieces) {
			g.fill(p.getScaledRect((float)remainingTime/(float)startingTime*thickness));
		}
	}

}
