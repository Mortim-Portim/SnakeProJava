package snake;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;

public class Piece {
	public static int pW = SnakePro.pieceWidth;
	public static int pH = SnakePro.pieceHeight;
	
	public int xPos;
	public int yPos;
	public int width;
	public int height;
	
	public Piece(int x, int y, int w, int h) {
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
	}
	
	public Piece(int x, int y) {
		this(x, y, 1, 1);
	}
	
	public int inList(List<Piece> l) {
		int count = 0;
		for(Piece i:l) {
			if(i.xPos == xPos && i.yPos == yPos) {
				count ++;
			}
		}
		return count;
	}
	
	public boolean inBorders(int x0, int y0, int x1, int y1) {
		if(xPos < x0 || yPos < y0 || xPos > x1 || yPos > y1) {
			return false;
		}
		return true;
	}
	
	public double getDis(Piece p) {
		return Math.hypot(xPos-p.xPos, yPos-p.yPos);
	}

	public Piece copy() {
		Piece p = new Piece(xPos, yPos, width, height);
		return p;
	}
	
	public Rectangle getPixlRect() {
		return new Rectangle(xPos*pW, yPos*pH, width*pW, height*pH);
	}
	
	public Rectangle getScaledRect(float scale) {
		return new Rectangle((xPos+0.5f-scale/2.0f)*pW, (yPos+0.5f-scale/2.0f)*pH, width*pW*scale, height*pH*scale);
	}
}
