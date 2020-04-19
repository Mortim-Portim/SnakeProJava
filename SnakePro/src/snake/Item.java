package snake;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item extends ImgObject{
	
	public int type;
	
	public Item(Image image, int x, int y, int type) throws SlickException {
		super(image, x, y);
		this.type = type;
	}
}
