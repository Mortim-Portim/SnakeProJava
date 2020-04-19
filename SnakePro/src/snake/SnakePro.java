package snake;

import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.lwjgl.input.Controllers;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

public class SnakePro extends BasicGame{
	public static int screenX = 1920;
	public static int screenY = 1080;
	public static int xPcs = 64;
	public static int yPcs = 36;
	public static int pieceWidth = screenX/xPcs;
	public static int pieceHeight = screenY/yPcs;
	public static String ImageFil;
	
	public static String iconFile = "/Icon.png";
	public static String HeadFil1 = "/Kopf1.png";
	public static String HeadFil2 = "/Kopf2.png";
	public static String HeadFil3 = "/Kopf3.png";
	public static String HeadFil4 = "/Kopf4.png";
	public static ArrayList<String> Heads = new ArrayList<String>();
	static {Heads.add(HeadFil1);Heads.add(HeadFil2);Heads.add(HeadFil3);Heads.add(HeadFil4);}
	public static String HeadSvg1 = "/Kopf1Win.png";
	public static String HeadSvg2 = "/Kopf2Win.png";
	public static String HeadSvg3 = "/Kopf3Win.png";
	public static String HeadSvg4 = "/Kopf4Win.png";
	public static ArrayList<String> HeadSvg = new ArrayList<String>();
	static {HeadSvg.add(HeadSvg1);HeadSvg.add(HeadSvg2);HeadSvg.add(HeadSvg3);HeadSvg.add(HeadSvg4);}
	
	public static float ItemScale = 2.0f;
	public static String ItemFil1 = "/Item1.png";
	public static String ItemFil2 = "/Item2.png";
	public static String ItemFil3 = "/Item3.png";
	public static String ItemFil4 = "/Item4.png";
	public static String ItemFil5 = "/Item5.png";
	public static ArrayList<Image> Items = new ArrayList<Image>();
	
	public static float PlayerItemsScale = 1.5f;
	public static int[] Itm1 = {0,0};
	public static int[] Itm2 = {(int) (screenX-pieceWidth*PlayerItemsScale*ItemScale),0};
	public static int[] Itm3 = {0, (int) (screenY-pieceHeight*PlayerItemsScale*ItemScale)};
	public static int[] Itm4 = {(int) (screenX-pieceWidth*PlayerItemsScale*ItemScale),(int) (screenY-pieceHeight*PlayerItemsScale*ItemScale)};
	public static int[][] Itms = {Itm1, Itm2, Itm3, Itm4};
	
	public static String FoodFil = "/Food.png";
	public static Image foodImg;
	public static float FoodScale = 1.5f;
	public static Color laserCol = new Color(0, 180, 0);
	public static String BotFil = "/Kopf5.png";
	public static int BombCol = 180;
	public static String Back1Fil = "/WinBack1.png";
	public static String Back2Fil = "/WinBack2.png";
	public static String HallFil = "/HallOfFame.png";
	public static int PklScale = 250;
	public static int PklXDis = 280;
	public static int PklYDis = 330;
	public static int PklSpac = (screenX-4*PklScale-2*PklXDis)/4;
	public static int PklYDis2 = 680;
	public static double hallHeadScale = 0.4;
	public static String[] pokaleFil = {
			"/HallPokal1.png",
			"/HallPokal2.png",
			"/HallPokal3.png",
			"/HallPokal4.png",
			"/HallPokal5.png",
			"/HallPokal6.png",
			"/HallPokal7.png",
			"/HallPokal8.png"};
	public static Image[] Pokale = new Image[pokaleFil.length];
	
	public static Image Back1;
	public static Image Back2;
	public static Image HallOfFame;
	public static String ParaFil = "Parameter.txt";
	public static String StatFil = "Statistics.txt";

	public static String[] Eating = {
			"Sounds/fressen1.wav",
			"Sounds/fressen2.wav",
            "Sounds/fressen3.wav",
            "Sounds/fressen4.wav",
            "Sounds/fressen5.wav"};
	public static String[] Dying = {
			"Sounds/tot1.wav",
	        "Sounds/tot2.wav",
	        "Sounds/Tod.wav"};
	public static String[] Winning = {
	        "Sounds/Win.wav",
	        "Sounds/Sieg.wav"};
	public static String[] Iteming = {
			"Sounds/Item.wav",
	        "Sounds/Item2.wav",
	        "Sounds/Item3.wav"};
	public static String[] Cloning = {"Sounds/Klonen.wav"};
	public static String[] Shooting = {
			"Sounds/Schießen.wav",
	        "Sounds/Schießen2.wav",
	        "Sounds/Schießen3.wav"};
	public static String[] Speeding = {
			"Sounds/Speed.wav",
	        "Sounds/Speed2.wav"};
	public static String[] Reviving = {
			"Sounds/Wiederbeleben.wav",
	        "Sounds/Wiederbeleben2.wav"};
	public static String[] Bombing = {"Sounds/explosion.wav"};
	public static String[] Ticking = {"Sounds/Piepen.wav"};
	
	public static Sound[] soundsEat = new Sound[Eating.length];
	public static Sound[] soundsDie = new Sound[Dying.length];
	public static Sound[] soundsWin = new Sound[Winning.length];
	public static Sound[] soundsItm = new Sound[Iteming.length];
	public static Sound[] soundsCln = new Sound[Cloning.length];
	public static Sound[] soundsSht = new Sound[Shooting.length];
	public static Sound[] soundsSpd = new Sound[Speeding.length];
	public static Sound[] soundsRev = new Sound[Reviving.length];
	public static Sound[] soundsBmb = new Sound[Bombing.length];
	public static Sound[] soundsTic = new Sound[Ticking.length];
	
	public static Random random = new Random();
	
	public Color back = new Color(60, 60, 60);
	public Color BotCol = new Color(0, 0, 0);
	public Font TextFont;
	public TrueTypeFont TextTTFont;
	
	public int player;
	public int speed = 5;
	public int startLenght = 10;
	public int foodCount = 10;
	public int growthRate = 3;
	public int itemCount = 10;
	public int spawnItemFrequency = 6*10;
	public int speedLenght = 6;
	public int LaserTime = 3;
	public int LaserLenght = 10;
	public double botSpeed = 1.1;
	public int bombRadius = 2;
	public int bombTime = 60*4;
	public int bombDis = 5;
	public int lastPlTime = 60*3;
	public List<Player> snakes = new ArrayList<Player>();
	public List<ImgObject> food = new ArrayList<ImgObject>();
	public List<Item> items = new ArrayList<Item>();
	public List<Laser> lasers = new ArrayList<Laser>();
	public List<Bot> bots = new ArrayList<Bot>();
	public List<Bomb> bombs = new ArrayList<Bomb>();
	
	public Input lastIn;
	public int frameCounter = 0;
	public int lastPlCounter = 0;
	public boolean playing = false;
	public String[] choosenNames;
	public String[] controller;
	public List<Integer> scores = new ArrayList<Integer>();
	public boolean displayHallOfFame;

	public SnakePro(String Settings) throws SlickException, FileNotFoundException {
		super("SnakePro");
		Statistics.loadStats();
		List<String> l = readFile(ParaFil, "=");
		this.player = 		Integer.parseInt(l.get(0));
		speed = 	  		Integer.parseInt(l.get(1));
		startLenght = 		Integer.parseInt(l.get(2));
		foodCount =   		Integer.parseInt(l.get(3));
		growthRate =  		Integer.parseInt(l.get(4));
		itemCount =   		Integer.parseInt(l.get(5));
		spawnItemFrequency =Integer.parseInt(l.get(6));
		speedLenght = 		Integer.parseInt(l.get(7));
		LaserTime = 		Integer.parseInt(l.get(8));
		LaserLenght = 		Integer.parseInt(l.get(9));
		bombRadius = 		Integer.parseInt(l.get(10));
		bombTime = 			Integer.parseInt(l.get(11));
		bombDis = 			Integer.parseInt(l.get(12));
		lastPlTime =		Integer.parseInt(l.get(13));
		botSpeed = 			Double.parseDouble(l.get(14));
		ImageFil =          l.get(15);
	}
	
	public List<String> readFile(String filename, String Splitter) throws FileNotFoundException {
		FileInputStream fis=new FileInputStream(filename);
		Scanner sc=new Scanner(fis);
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			String l = sc.nextLine().replace(" ", "");
			String [] ls = l.split(Splitter);
			l = ls[ls.length-1];
			lines.add(l);
		}
		sc.close();
		return lines;
	}
	
	public void initSounds(GameContainer gc) throws SlickException {
		for(int i=0; i<Eating.length; i++) {
			soundsEat[i] = new Sound(Eating[i]);
		}
		for(int i=0; i<Dying.length; i++) {
			soundsDie[i] = new Sound(Dying[i]);
		}
		for(int i=0; i<Winning.length; i++) {
			soundsWin[i] = new Sound(Winning[i]);
		}
		for(int i=0; i<Iteming.length; i++) {
			soundsItm[i] = new Sound(Iteming[i]);
		}
		for(int i=0; i<Cloning.length; i++) {
			soundsCln[i] = new Sound(Cloning[i]);
		}
		for(int i=0; i<Shooting.length; i++) {
			soundsSht[i] = new Sound(Shooting[i]);
		}
		for(int i=0; i<Speeding.length; i++) {
			soundsSpd[i] = new Sound(Speeding[i]);
		}
		for(int i=0; i<Reviving.length; i++) {
			soundsRev[i] = new Sound(Reviving[i]);
		}
		for(int i=0; i<Bombing.length; i++) {
			soundsBmb[i] = new Sound(Bombing[i]);
		}
		for(int i=0; i<Ticking.length; i++) {
			soundsTic[i] = new Sound(Ticking[i]);
		}
	}
	
	public void initNames(GameContainer gc) throws SlickException {
		choosenNames = new String[player];
		controller = new String[player];
		for(int i=0; i<player; i++) {
			boolean found = false;
			String con;
			if(i > 0 && i < gc.getInput().getControllerCount()+1) {
				con = Controllers.getController(i-1).getName().replace(" ", "");
			}else {
				con = "keyboard";
			}
			for(String[] s:Statistics.loadedFil) {
				if(s[s.length-1].equals(con)) {
					choosenNames[i] = s[0];
					found = true;
					break;
				}else {
					choosenNames[i] = Integer.toString(i+1);
				}
			}
			JOptionPane pane = new JOptionPane("Who is using "+con+" ?");
			pane.setWantsInput(true);
			pane.setInitialSelectionValue(choosenNames[i]);
			JDialog dialog = pane.createDialog("SnakePro");
			dialog.setVisible(true);
			con = (String) pane.getInputValue();
			controller[i] = con;
		}
		Window[] w = Window.getWindows();
		gc.setFullscreen(false);
		w[0].toFront();
		gc.setFullscreen(true);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		Items.clear();
		Items.add(new Image(ImageFil+ItemFil1).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(ItemScale));
		Items.add(new Image(ImageFil+ItemFil2).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(ItemScale));
		Items.add(new Image(ImageFil+ItemFil3).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(ItemScale));
		Items.add(new Image(ImageFil+ItemFil4).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(ItemScale));
		Items.add(new Image(ImageFil+ItemFil5).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(ItemScale));
		
		foodImg = new Image(ImageFil+FoodFil).getScaledCopy(pieceWidth, pieceHeight).getScaledCopy(FoodScale);
		
		Back1 = new Image(ImageFil+Back1Fil).getScaledCopy(screenX, screenY);
		Back2 = new Image(ImageFil+Back2Fil).getScaledCopy(screenX, screenY);
		HallOfFame = new Image(ImageFil+HallFil).getScaledCopy(screenX, screenY);
		
		int scl = (int)(screenX*(PklScale/1920.0));
		for(int i=0; i<pokaleFil.length; i++) {
			Pokale[i] = new Image(ImageFil+pokaleFil[i]).getScaledCopy(scl,scl);
		}
		
		gc.setMouseGrabbed(true);
		gc.setIcon(ImageFil+iconFile);
		TextFont = new Font("Verdana", Font.BOLD, (int) (screenX*(35.0/1920.0)));
		TextTTFont= new TrueTypeFont(TextFont, false);
		frameCounter = 0;
		initSounds(gc);
		initNames(gc);
		reset(gc);
	}
	
	@Override
	public boolean closeRequested() {
		JPanel j = new JPanel();
		if(JOptionPane.showConfirmDialog(j, "Quit?") == 0) {
			for(Player p:snakes) {
				p.stats.writeStats();
			}
			return true;
		}else {
			return false;
		}
	}
	
	public void reset(GameContainer gc) throws SlickException {
		snakes.clear();
		for(int i=0; i<player; i++) {
			snakes.add(new Player(i+1, new Image(ImageFil+Heads.get(i)), startLenght, choosenNames[i], controller[i]));
		}
		food.clear();
		for(int i=0; i<foodCount; i++) {
			food.add(new ImgObject(foodImg, random.nextInt(xPcs), random.nextInt(yPcs)));
		}
		items.clear();
		for(int i=0; i<itemCount; i++) {
			int type = random.nextInt(Items.size())+1;
			items.add(new Item(Items.get(type-1), random.nextInt(xPcs), random.nextInt(yPcs), type));
		}
		lasers.clear();
		bots.clear();
		bombs.clear();
		frameCounter = 0;
		lastPlCounter = 0;
		playing = true;
		displayHallOfFame = false;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(gc.hasFocus()) {
		lastIn = gc.getInput();
		List<Player> itemUser = new ArrayList<Player>();
		if(lastIn.isKeyDown(Input.KEY_UP)) {
			snakes.get(0).nextDir = Direction.up;
		}else if(lastIn.isKeyDown(Input.KEY_DOWN)) {
			snakes.get(0).nextDir = Direction.down;
		}else if(lastIn.isKeyDown(Input.KEY_LEFT)) {
			snakes.get(0).nextDir = Direction.left;
		}else if(lastIn.isKeyDown(Input.KEY_RIGHT)) {
			snakes.get(0).nextDir = Direction.right;
		}
		if(lastIn.isKeyPressed(Input.KEY_TAB) && !playing) {
			displayHallOfFame = !displayHallOfFame;
		}
		if(lastIn.isKeyPressed(Input.KEY_ADD) && !playing) {
			if(Statistics.isShowingStats()) {
				Statistics.chart.dispose();
			}
			Statistics.showStats();
		}
		if(player > 1) {
			if(controllerUp[0]) {
				snakes.get(1).nextDir = Direction.up;
			}else if(controllerDown[0]) {
				snakes.get(1).nextDir = Direction.down;
			}else if(controllerLeft[0]) {
				snakes.get(1).nextDir = Direction.left;
			}else if(controllerRight[0]) {
				snakes.get(1).nextDir = Direction.right;
			}
		}
		if(player > 2) {
			if(controllerUp[1]) {
				snakes.get(2).nextDir = Direction.up;
			}else if(controllerDown[1]) {
				snakes.get(2).nextDir = Direction.down;
			}else if(controllerLeft[1]) {
				snakes.get(2).nextDir = Direction.left;
			}else if(controllerRight[1]) {
				snakes.get(2).nextDir = Direction.right;
			}
		}
		if(player > 3) {
			if(controllerUp[2]) {
				snakes.get(3).nextDir = Direction.up;
			}else if(controllerDown[2]) {
				snakes.get(3).nextDir = Direction.down;
			}else if(controllerLeft[2]) {
				snakes.get(3).nextDir = Direction.left;
			}else if(controllerRight[2]) {
				snakes.get(3).nextDir = Direction.right;
			}
		}
		if(playing) {
			if(frameCounter%speed == 0) {
				if(lastIn.isKeyPressed(Input.KEY_SPACE)) {
					itemUser.add(snakes.get(0));
				}
				if(lastIn.isButton1Pressed(0) || lastIn.isButton2Pressed(0) || lastIn.isButton3Pressed(0)) {
					itemUser.add(snakes.get(1));
				}
				if(lastIn.isButton1Pressed(1) || lastIn.isButton2Pressed(1) || lastIn.isButton3Pressed(1)) {
					itemUser.add(snakes.get(2));
				}
				if(lastIn.isButton1Pressed(2) || lastIn.isButton2Pressed(2) || lastIn.isButton3Pressed(2)) {
					itemUser.add(snakes.get(3));
				}
				for(Player p:itemUser) {
					if(p.alive) {
						useItem(p.Item, p);
					}
				}
				
				for(Player p:snakes) {
					p.changeDirection();
					p.update();
				}
				
				int addFood = 0;
				List<ImgObject> removeFoods = new ArrayList<ImgObject>();
				for(ImgObject f:food) {
					for(Player p:snakes) {
						if(f.collidesWith(p.head)) {
							p.grow(growthRate);
							removeFoods.add(f);
							addFood ++;
						}
					}
				}
				for(ImgObject f:removeFoods) {
					food.remove(f);
				}
				for(int i=0; i<addFood; i++) {
					ImgObject newFood = new ImgObject(foodImg, random.nextInt(xPcs), random.nextInt(yPcs));
					while(newFood.inImList(food) || newFood.inItList(items)) {
						newFood = new ImgObject(foodImg, random.nextInt(xPcs), random.nextInt(yPcs));
					}
					food.add(newFood);
				}
				
				
				List<Item> removeItems = new ArrayList<Item>();
				for(Item i:items) {
					for(Player p:snakes) {
						if(i.collidesWith(p.head)) {
							SnakePro.soundsItm[SnakePro.random.nextInt(SnakePro.soundsItm.length)].play();
							removeItems.add(i);
							p.Item = i.type;
						}
					}
				}
				for(Item i:removeItems) {
					items.remove(i);
				}
				
				List<Player> setDeadPl = new ArrayList<Player>();
				List<Dead> causes = new ArrayList<Dead>();
				List<Player> killer = new ArrayList<Player>();
				for(Player p:snakes) {
					if(!setDeadPl.contains(p) && p.alive) {
						if(p.collidesWithSelf()) {
							setDeadPl.add(p);
							causes.add(Dead.Stupid);
							killer.add(p);
						}else if(!p.getHead().inBorders(0, 0, xPcs-1, yPcs-1)) {
							setDeadPl.add(p);
							causes.add(Dead.Wall);
							killer.add(p);
						}else {
							for(Player p2 : snakes) {
								if(p2 != p && p.getHead().inList(p2.pieces) > 0 && !setDeadPl.contains(p)) {
									setDeadPl.add(p);
									causes.add(Dead.Player);
									killer.add(p2);
								}
							}
						}
					}
				}
				int alivePl = 0;
				for(Player p : snakes) {
					if(p.alive) {
						alivePl ++;
					}
				}
				for(int i=0; i<setDeadPl.size(); i++) {
					if(alivePl > 1) {
						setDeadPl.get(i).setDead(frameCounter, causes.get(i), killer.get(i));
					}else {
						setDeadPl.get(i).setDead(frameCounter, Dead.Winner, setDeadPl.get(i));
					}
				}
			}
			if(frameCounter%spawnItemFrequency == 0) {
				int type = random.nextInt(Items.size())+1;
				Item newItem = new Item(Items.get(type-1), random.nextInt(xPcs), random.nextInt(yPcs), type);
				while(newItem.inImList(food) || newItem.inItList(items)) {
					newItem = new Item(Items.get(type-1), random.nextInt(xPcs), random.nextInt(yPcs), type);
				}
				items.add(newItem);
			}
			if(frameCounter%(int)(speed/botSpeed) == 0) {
				for(Bot b:bots) {
					b.calcDir(snakes);
					b.update();
				}
				List<Bot> setDeadBots = new ArrayList<Bot>();
				for(Bot b:bots) {
					if(!setDeadBots.contains(b)) {
						if(b.collidesWithSelf()) {
							setDeadBots.add(b);
						}else if(!b.getHead().inBorders(0, 0, xPcs-1, yPcs-1)) {
							setDeadBots.add(b);
						}else {
							for(Player p2 : snakes) {
								if(b.getHead().inList(p2.pieces) > 0 && !setDeadBots.contains(b) && snakes.indexOf(p2) != b.Owner) {
									setDeadBots.add(b);
								}
							}
						}
					}
				}
				for(Player p : snakes) {
					for(Bot b : bots) {
						if(p.alive) {
							if(p.getHead().inList(b.pieces) > 0) {
								if(snakes.indexOf(p) != b.Owner) {
									p.setDead(frameCounter, Dead.Bot, snakes.get(b.Owner));
								}
							}
						}
					}
				}
				for(Bot b : setDeadBots) {
					bots.remove(b);
				} 
			}
			List<Laser> removeLaser = new ArrayList<Laser>();
			if(frameCounter%LaserTime == 0) {
				for(Laser l : lasers) {
					l.remainingTime --;
					if(l.remainingTime <= 0) {
						removeLaser.add(l);
					}
				}
			}
			for(Laser l : removeLaser) {
				lasers.remove(l);
			}
			List<Bomb> removeBomb = new ArrayList<Bomb>();
			for(Bomb b : bombs) {
				b.remainingTime --;
				if(b.remainingTime <= 0) {
					removeBomb.add(b);
				}
			}
			for(Bomb b : removeBomb) {
				b.explode(snakes, frameCounter);
				bombs.remove(b);
			}
			int alivePl = 0;
			for(Player p : snakes) {
				if(p.alive) {
					alivePl ++;
				}
			}
			if(alivePl <= 1) {
				lastPlCounter ++;
			}
			if(lastPlCounter >= lastPlTime) {
				SnakePro.soundsWin[SnakePro.random.nextInt(SnakePro.soundsWin.length)].play();
				for(Player p : snakes) {
					p.setDead(frameCounter, Dead.Winner, p);
				}
				scores.clear();
				for(Player p:snakes) {
					scores.add(p.score);
				}
				playing = false;
				lastPlCounter = 0;
			}
			frameCounter ++;
		}else {
			if(lastIn.isKeyDown(Input.KEY_SPACE)) {
				reset(gc);
			}
		}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(playing) {
			g.setBackground(back);
			
			for(Bomb b: bombs) {
				int col = (int) (BombCol-BombCol*((double)(b.remainingTime)/(double)b.startTime));
				g.setColor(new Color(col/2+back.getRed(), back.getGreen(), back.getBlue()));
				g.fill(b.getRadius());
				g.setColor(new Color(col-BombCol+255, 0, 0));
				Rectangle r = b.getScaledRect(0.8f);
				g.fill(r);
				g.setColor(new Color(0, 0, 0));
				g.draw(r);
			}
			
			for(ImgObject f:food) {
				g.drawImage(f.img, f.getPixelX()-pieceWidth*(FoodScale-1)/2, f.getPixelY()-pieceHeight*(FoodScale-1)/2);
			}
			for(ImgObject i:items) {
				g.drawImage(i.img, i.getPixelX()-pieceWidth*(ItemScale-1)/2, i.getPixelY()-pieceHeight*(ItemScale-1)/2);
			}
			for(Player snk:snakes) {
				if(snk.alive) {
					if(snk.Item > 0) {
						Image PlayItmImg = Items.get(snk.Item-1).getScaledCopy(PlayerItemsScale).copy();
						PlayItmImg.setAlpha(0.5f);
						g.drawImage(PlayItmImg, Itms[snk.Playernum-1][0], Itms[snk.Playernum-1][1]);
					}
				}
			}
			
			for(Laser l:lasers) {
				l.draw(g);
			}
			
			for(Bot b: bots) {
				g.setColor(BotCol);
				for(Piece pcs:b.getPieces()) {
					g.fill(pcs.getPixlRect());
				}
				for(ImgObject img:b.getImgs()) {
					int x = img.getPixelX()-pieceWidth*(Snake.headScale-1)/2;
					int y = img.getPixelY()-pieceHeight*(Snake.headScale-1)/2;
					g.drawImage(img.img, x, y);
				}
			}
			
			for(Player snk:snakes) {
				if(snk.alive) {
					g.setColor(snk.color);
					for(Piece pcs:snk.getPieces()) {
						g.fill(pcs.getPixlRect());
					}
					for(ImgObject img:snk.getImgs()) {
						int x = img.getPixelX()-pieceWidth*(Snake.headScale-1)/2;
						int y = img.getPixelY()-pieceHeight*(Snake.headScale-1)/2;
						g.drawImage(img.img, x, y);
					}
				}
			}
		}else {
			if(!displayHallOfFame) {
				g.drawImage(Back1,0,0);
				
				Image winImg = new Image(ImageFil+HeadSvg.get(scores.indexOf(Collections.max(scores)))).getScaledCopy((int) (screenX*(400.0/1920.0)), (int) (screenY*(400.0/1080.0)));
				
				winImg.rotate(90);
				g.drawImage(winImg,(int)(screenX*(1168.0/1920.0)),(int)(screenY*(118.0/1080.0)));
				g.drawImage(Back2, 0, 0);
				for(int i=0; i<snakes.size(); i++) {
					Player p = snakes.get(i);
					TextTTFont.drawString((int)(screenX*(285.0/1920.0)),(int) (screenY*(250.0/1080.0)+i*screenX*(50.0/1920.0)),
							p.stats.name+": "+p.score, p.color);
				}
				
				for(Player p: snakes) {
					p.stats.writeStats();
				}
			}else {
				g.drawImage(HallOfFame,0,0);
				List<List<Integer>> l = new ArrayList<List<Integer>>();
				for(int i=0; i<8; i++) {
					List<Integer> statLis = new ArrayList<Integer>();
					for(Player p : snakes) {
						statLis.add(p.stats.getValue(i));
					}
					l.add(statLis);
				}
				int HdScale = (int) (PklScale*hallHeadScale);
				List<Image> bestImgs = new ArrayList<Image>();
				for(List<Integer> sttLis:l) {
					int timesInList = 0;
					for(Integer i : sttLis) {
						if(i == Collections.max(sttLis)) {
							timesInList ++;
						}
					}
					Image newImage;
					if(timesInList <= 1) {
						newImage = new Image(ImageFil+HeadSvg.get(sttLis.indexOf(Collections.max(sttLis)))).getScaledCopy(HdScale, HdScale);
					}else {
						newImage = new Image(ImageFil+HeadSvg.get(sttLis.indexOf(Collections.max(sttLis)))).getScaledCopy(0, 0);
					}
					newImage.rotate(90);
					bestImgs.add(newImage);
				}
				
				for(int i=0; i<4; i++) {
					g.drawImage(bestImgs.get(i), PklXDis+(PklScale+PklSpac)*i+PklScale/2-HdScale/2, PklYDis-HdScale+20);
					TextTTFont.drawString(PklXDis+(PklScale+PklSpac)*i+PklScale, PklYDis, Integer.toString(Collections.max(l.get(i))), new Color(0,0,0));
					g.drawImage(Pokale[i], PklXDis+(PklScale+PklSpac)*i, PklYDis);
				}
				for(int i=4; i<8; i++) {
					g.drawImage(bestImgs.get(i), PklXDis+(PklScale+PklSpac)*(i-4)+PklScale/2-HdScale/2, PklYDis2-HdScale+20);
					TextTTFont.drawString(PklXDis+(PklScale+PklSpac)*(i-4)+PklScale, PklYDis2, Integer.toString(Collections.max(l.get(i))), new Color(0,0,0));
					g.drawImage(Pokale[i], PklXDis+(PklScale+PklSpac)*(i-4), PklYDis2);
				}
				
			}
		}
	}
	
	public static List<Piece> getNextPieces(int lenght, Piece hd, int xdif, int ydif) {
		List<Piece> p = new ArrayList<Piece>();
		for(int i=0; i<lenght; i++) {
			int x = hd.xPos+xdif*i;
			int y = hd.yPos+ydif*i;
			if(x < 0) {
				x = xPcs-1;
			}
			if(y < 0) {
				y = yPcs-1;
			}
			if(x >= xPcs) {
				x = 0;
			}
			if(y >= yPcs) {
				y = 0;
			}
			p.add(new Piece(x, y));
		}
		return p;
	}
	
	public void useItem(int itm, Player p) throws SlickException {
		if(itm == 1) {
			p.revive();
		}else if(itm == 2) {
			SnakePro.soundsSpd[SnakePro.random.nextInt(SnakePro.soundsSpd.length)].play();
			Piece hd = p.getHead();
			List<Piece> nextPieces = new ArrayList<Piece>();
			switch (p.nextDir) {
			case up:
				nextPieces = getNextPieces(speedLenght, hd, 0, -1);
				break;
			case down:
				nextPieces = getNextPieces(speedLenght, hd, 0, 1);
				break;
			case left:
				nextPieces = getNextPieces(speedLenght, hd, -1, 0);
				break;
			case right:
				nextPieces = getNextPieces(speedLenght, hd, 1, 0);
				break;
			}
			for(Piece pcs : nextPieces) {
				p.pieces.add(pcs);
			}
			for(int i=0; i<speedLenght; i++) {
				p.pieces.remove(0);
			}
		}else if(itm == 3) {
			SnakePro.soundsSht[SnakePro.random.nextInt(SnakePro.soundsSht.length)].play();
			Piece hd = p.getHead();
			lasers.add(new Laser(hd, LaserLenght, p.dir));
			for(Player p2:snakes) {
				if(p2.alive) {
					int hitPc = lasers.get(lasers.size()-1).hits(p2);
					if(hitPc >= 0) {
						if(hitPc < p2.pieces.size()-1) {
							for(int i=0; i<hitPc; i++) {
								p2.pieces.remove(0);
							}
						}else {
							p2.setDead(frameCounter, Dead.Laser, p);
						}
					}
				}
			}
		}else if(itm == 4) {
			SnakePro.soundsCln[SnakePro.random.nextInt(SnakePro.soundsCln.length)].play();
			Bot b = new Bot(new Image(ImageFil+BotFil), snakes.indexOf(p), p, startLenght);
			bots.add(b);
		}else if(itm == 5) {
			SnakePro.soundsTic[SnakePro.random.nextInt(SnakePro.soundsTic.length)].play();
			Piece hd = p.getHead();
			int x=hd.xPos; int y=hd.yPos;
			switch (p.nextDir) {
			case up:
				y -= bombDis;
				break;
			case down:
				y += bombDis;
				break;
			case left:
				x -= bombDis;
				break;
			case right:
				x += bombDis;
				break;
			}
			
			bombs.add(new Bomb(x, y, bombRadius, bombTime, p));
		}
		p.Item = 0;
	}
	
	public static void main(String[] args) throws SlickException, FileNotFoundException {
		//System.out.println(System.getProperty("java.library.path"));
		SnakePro snkProGm = new SnakePro("settings.txt");
		AppGameContainer SnakeProGame1 = new AppGameContainer(snkProGm);
		SnakeProGame1.setDisplayMode(screenX, screenY, true);
		SnakeProGame1.setFullscreen(true);
		SnakeProGame1.setTargetFrameRate(60);
		SnakeProGame1.setVSync(false);
		SnakeProGame1.setShowFPS(false);
		SnakeProGame1.start();
		
	}
}
