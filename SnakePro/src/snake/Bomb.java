package snake;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;

public class Bomb extends Piece{
	public static int pW = SnakePro.pieceWidth;
	public static int pH = SnakePro.pieceHeight;
	
	public Rectangle radius;
	public int remainingTime;
	public int startTime;
	public Player Owner;

	public Bomb(int x, int y, int rad, int fullTime, Player Owner) {
		super(x, y);
		remainingTime = fullTime;
		startTime = fullTime;
		this.Owner = Owner;
		radius = new Rectangle(x-rad, y-rad, rad*2+1, rad*2+1);
	}
	
	public Rectangle getRadius() {
		return new Rectangle(radius.getX()*pW, radius.getY()*pH, radius.getWidth()*pW, radius.getHeight()*pH);
	}

	public void explode(List<Player> pl, int score) {
		SnakePro.soundsBmb[SnakePro.random.nextInt(SnakePro.soundsBmb.length)].play();
		int x0 = (int) radius.getX();
		int y0 = (int) radius.getY();
		int x1 = (int) (radius.getWidth()+x0);
		int y1 = (int) (radius.getHeight()+y0);
		for(Player p : pl) {
			if(p.alive) {
				if(p.getHead().inBorders(x0, y0, x1-1, y1-1)) {
					p.setDead(score, Dead.Bomb, Owner);
				}else {
					int hitPc = -1;
					for(Piece pc: p.getPieces()) {
						int newHitPc = p.pieces.indexOf(pc);
						if(newHitPc > hitPc && pc.inBorders(x0, y0, x1-1, y1-1)) {
							hitPc = newHitPc;
						}
					}
					if(hitPc >= 0) {
						if(hitPc < p.pieces.size()-1) {
							for(int i=0; i<hitPc; i++) {
								p.pieces.remove(0);
							}
						}
					}
				}
			}
		}
	}
	
}
