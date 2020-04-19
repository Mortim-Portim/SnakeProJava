package test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

public class Test1 extends BasicGame {
	public static final int WIDTH= 400;
	public static final int HEIGHT= 300;
	public static String soundFil = "Sounds/explosion.wav";
	
	public Test1() {
		super("Pong");
	}
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getInput().initControllers();
	}
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// Abfangen der Eingabegaräte
		Input input= gc.getInput();
		
		System.out.println(gc.isSoundOn());
		Sound s = new Sound(soundFil);
		s.play();
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Rectangle r = new Rectangle(0f, 0f, 12f, 12f);
		Image i = new Image("src/Images/Food.png");
		g.fill(r);
		g.drawImage(i.getScaledCopy(1000, 1000), 100, 100);
	}
	
	public static void main(String[] args) throws SlickException {
		System.out.println(Integer.parseInt("1"));
		AppGameContainer pong = new AppGameContainer(new Test1());
		//pong.setDisplayMode(WIDTH, HEIGHT, false);
		pong.setDisplayMode(640, 480, true);
		pong.setFullscreen(false);
		pong.setTargetFrameRate(30);
		pong.setVSync(false);
		pong.setShowFPS(true);
		pong.start();
	}
}