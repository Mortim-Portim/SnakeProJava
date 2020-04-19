package snake;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImgObject {
	public Image img;
	public float rotation;
	public int x;
	public int y;

	public ImgObject(Image image, int x, int y) throws SlickException {
		this.img = image.copy();
		this.x = x;
		this.y = y;
		this.rotation = 0;
	}
	
	public boolean collidesWith(ImgObject img) {
		if(this.x == img.x && this.y == img.y) {
			return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	public int getPixelX() {
		return x*SnakePro.pieceWidth;
	}
	public int getPixelY() {
		return y*SnakePro.pieceHeight;
	}
	public int getY() {
		return y;
	}
	public Image getImg() {
		return img;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setImg(String file) throws SlickException {
		this.img = new Image(file);
	}
	
	public boolean inImList(List<ImgObject> l) {
		for(ImgObject i:l) {
			if(i.x == x && i.y == y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean inItList(List<Item> l) {
		for(Item i:l) {
			if(i.x == x && i.y == y) {
				return true;
			}
		}
		return false;
	}
	
	public void rescale(int width, int height) {
		this.img = img.getScaledCopy(width, height);
	}
	
	public void rotate(float angle) {
		this.img.rotate(-rotation);
		this.rotation = angle;
		this.img.rotate(angle);
	}
}
