package snake;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
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
import org.newdawn.slick.util.BufferedImageUtil;

public class SnakePro extends BasicGame{
	public static int screenX = 1920;
	public static int screenY = 1080;
	public static int frameRate = 60;
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
	
	public static int[] nameChoosePos = {(int) (screenX*(280.0/1920.0)),(int) (screenY*(250.0/1080.0))};
	public static int[] winImgPos = {(int)(screenX*(1168.0/1920.0)),(int)(screenY*(118.0/1080.0))};
	public static int[] winImgScale = {(int) (screenX*(400.0/1920.0)), (int) (screenY*(400.0/1080.0))};
	public static String FoodFil = "/Food.png";
	public static Image foodImg;
	public static float FoodScale = 1.5f;
	public static Color laserCol = new Color(0, 180, 0);
	public static String BotFil = "/Kopf5.png";
	public static String BossFil = "/Boss.png";
	public static int BombCol = 180;
	public static String Back1Fil = "/WinBack.png";
	public static String HallFil = "/HallOfFame.png";
	public static String IntroFil = "/Intro.png";
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
	
	public static Image Back;
	public static Image HallOfFame;
	public static String ParaFil = "Parameter.txt";
	public static String StatFil = "stats/Statistics.txt";

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
	public Color bossCol = new Color(255, 255, 255);
	public Font TextFont;
	public TrueTypeFont TextTTFont;
	public int bossAnimBlink = 10;
	
	public int player;
	public int speed = 5;
	public int startLenght = 10;
	public int foodCount = 10;
	public int growthRate = 3;
	public int itemCount = 10;
	public int spawnItemFrequency = 6*10;
	public int speedLenght = 6;
	public int LaserTime = 6;
	public int LaserLenght = 10;
	public double botSpeed = 1.1;
	public int bombRadius = 2;
	public int bombTime = 60*4;
	public int bombDis = 5;
	public int lastPlTime = 60*3;
	public int bossSpawnFrequency = 1000;
	public int bossSpawnTime = 60;
	public int bossLenght = 10;
	public double bossSpeed = 1.5;
	public int bossTime = 300;
	public boolean showFps = true;
	public boolean setFullscreen = true;
	public String configFile = "cfg/config.txt";
	
	public List<Player> snakes = new ArrayList<Player>();
	public List<ImgObject> food = new ArrayList<ImgObject>();
	public List<Item> items = new ArrayList<Item>();
	public List<Laser> lasers = new ArrayList<Laser>();
	public List<Bot> bots = new ArrayList<Bot>();
	public List<Bomb> bombs = new ArrayList<Bomb>();
	public List<Boss> bosses = new ArrayList<Boss>();
	public List<Piece> bossSpawns = new ArrayList<Piece>();
	public List<Integer> bossSpawnTimer = new ArrayList<Integer>();
	public int drawConName = -1;
	
	public Input lastIn;
	public int frameCounter = 0;
	public int lastPlCounter = 0;
	public boolean playing = false;
	public String[] choosenNames;
	public String[] controller;
	public List<Integer> scores = new ArrayList<Integer>();
	public List<TextField> nameFields = new ArrayList<TextField>();
	public boolean displayHallOfFame;
	public boolean displayStats = false;
	public Image statsImg;

	public SnakePro(String Settings) throws SlickException, FileNotFoundException {
		super("SnakePro");
		Statistics.loadStats();
		List<String> l = readFile(ParaFil, "=");
		this.player = 		Integer.parseInt(l.get(0));
		speed = 	  		20-Integer.parseInt(l.get(1));
		startLenght = 		Integer.parseInt(l.get(2));
		foodCount =   		Integer.parseInt(l.get(3));
		growthRate =  		Integer.parseInt(l.get(4));
		itemCount =   		Integer.parseInt(l.get(5));
		spawnItemFrequency =(int) (Double.parseDouble(l.get(6))*frameRate);
		speedLenght = 		Integer.parseInt(l.get(7));
		LaserTime = 		(int) (Double.parseDouble(l.get(8))*frameRate);
		LaserLenght = 		Integer.parseInt(l.get(9));
		bombRadius = 		Integer.parseInt(l.get(10));
		bombTime = 			(int) (Double.parseDouble(l.get(11))*frameRate);
		bombDis = 			Integer.parseInt(l.get(12));
		lastPlTime =		(int) (Double.parseDouble(l.get(13))*frameRate);
		botSpeed = 			Double.parseDouble(l.get(14));
		bossSpawnFrequency =(int) (Double.parseDouble(l.get(15))*frameRate);
		bossSpawnTime =     (int) (Double.parseDouble(l.get(16))*frameRate);
		bossLenght =        Integer.parseInt(l.get(17));
		bossSpeed =         Double.parseDouble(l.get(18));
		bossTime = 			(int) (Double.parseDouble(l.get(19))*frameRate);
		ImageFil =          l.get(20);
		showFps = 			Boolean.parseBoolean(l.get(21));
		setFullscreen = 	Boolean.parseBoolean(l.get(22));
		configFile = "cfg/"+ImageFil+"config.txt";
		List<String> config = readFile(configFile, "=");
		nameChoosePos[0] = (int) (screenX*(Double.parseDouble(config.get(0))/1920.0));
		nameChoosePos[1] = (int) (screenY*(Double.parseDouble(config.get(1))/1080.0));
		winImgPos[0] = (int) (screenX*(    Double.parseDouble(config.get(2))/1920.0));
		winImgPos[1] = (int) (screenY*(    Double.parseDouble(config.get(3))/1080.0));
		winImgScale[0] = (int) (screenX*(  Double.parseDouble(config.get(4))/1920.0));
		winImgScale[1] = (int) (screenY*(  Double.parseDouble(config.get(5))/1080.0));
	}
	
	public List<String> readFile(String filename, String Splitter) throws FileNotFoundException {
		FileInputStream fis=new FileInputStream(filename);
		Scanner sc=new Scanner(fis);
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			String l = sc.nextLine().replace(" ", "");
			String [] ls = l.split(Splitter);
			if(ls.length>1) {
				l = ls[ls.length-1];
				lines.add(l);
			}
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
			String con;
			if(i > 0 && i < gc.getInput().getControllerCount()+1 && Controllers.getController(i-1).getAxisCount()>2) {
				con = Controllers.getController(i-1).getName().replace(" ", "");
			}else {
				con = "keyboard";
			}
			if(Arrays.asList(controller).contains(con)) {
				con += Integer.toString(i+1);
			}
			for(String[] s:Statistics.loadedFil) {
				if(s[s.length-1].equals(con)) {
					choosenNames[i] = s[0];
					break;
				}else {
					choosenNames[i] = Integer.toString(i+1);
				}
			}
			/**JOptionPane pane = new JOptionPane("Who is using "+con+" ?");
			pane.setWantsInput(true);
			pane.setInitialSelectionValue(choosenNames[i]);
			JDialog dialog = pane.createDialog("SnakePro");
			dialog.setVisible(true);
			String play = (String) pane.getInputValue();
			choosenNames[i] = play;**/
			controller[i] = con;
		}
		/**Window[] w = Window.getWindows();
		gc.setFullscreen(false);
		w[0].toFront();
		gc.setFullscreen(setFullscreen);**/
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
		
		Back = new Image(ImageFil+Back1Fil).getScaledCopy(screenX, screenY);
		HallOfFame = new Image(ImageFil+HallFil).getScaledCopy(screenX, screenY);
		
		int scl = (int)(screenX*(PklScale/1920.0));
		for(int i=0; i<pokaleFil.length; i++) {
			Pokale[i] = new Image(ImageFil+pokaleFil[i]).getScaledCopy(scl,scl);
		}
		
		gc.setIcon(ImageFil+iconFile);
		TextFont = new Font("Comic Sans MS", Font.BOLD, (int) (screenX*(35.0/1920.0)));
		TextTTFont= new TrueTypeFont(TextFont, false);
		gc.setShowFPS(showFps);
		gc.setFullscreen(setFullscreen);
		initSounds(gc);
		initNames(gc);
		reset(gc);
		playing = false;
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
		nameFields.clear();
		for(int i=0; i<choosenNames.length; i++) {
			int scale = (int) (screenY*(50.0/1080.0));
			TextField tf = new TextField(gc, TextTTFont, nameChoosePos[0], nameChoosePos[1]+i*scale, scale*5, scale);
			tf.setText(choosenNames[i]);
			tf.setBackgroundColor(null);
			tf.setBorderColor(null);
			nameFields.add(tf);
		}
		lasers.clear();
		bots.clear();
		bombs.clear();
		bosses.clear();
		bossSpawns.clear();
		bossSpawnTimer.clear();
		frameCounter = 0;
		lastPlCounter = 0;
		playing = true;
		displayHallOfFame = false;
		displayStats = false;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(gc.hasFocus()) {
		lastIn = gc.getInput();
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
		if(lastIn.isKeyPressed(Input.KEY_S) && !playing) {
			displayStats = !displayStats;
			for(Player p:snakes) {
				p.stats.writeStats();
			}
			JFreeChart jf = Statistics.showStats();
			BufferedImage objBufferedImage = jf.createBufferedImage(screenX,screenY);
			try {
				statsImg = new Image(BufferedImageUtil.getTexture("stats/Stats.png", objBufferedImage));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			try {
				ImageIO.write(objBufferedImage, "png", bas);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] byteArray=bas.toByteArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage image;
			try {
				image = ImageIO.read(in);
				File outputfile = new File("stats/Stats.png");
				ImageIO.write(image, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			gc.setMouseGrabbed(true);
			List<Player> itemUser = new ArrayList<Player>();
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
			List<Boss> removeBoss = new ArrayList<Boss>();
			for(Boss b : bosses) {
				if(b.time <= 0) {
					removeBoss.add(b);
				}
			}
			for(Boss b:removeBoss) {
				bosses.remove(b);
			}
			if(frameCounter%bossSpawnFrequency == 0 && frameCounter != 0) {
				bossSpawns.add(new Piece(random.nextInt(xPcs), random.nextInt(yPcs)));
				bossSpawnTimer.add(bossSpawnTime);
			}
			for(int i=0; i<bossSpawnTimer.size(); i++) {
				bossSpawnTimer.set(i, bossSpawnTimer.get(i)-1);
			}
			List<Integer> removeTimers = new ArrayList<Integer>();
			for(int i=0; i<bossSpawnTimer.size(); i++) {
				if(bossSpawnTimer.get(i) == 0) {
					int xpos = bossSpawns.get(bossSpawns.size()-1).xPos;
					int ypos = bossSpawns.get(bossSpawns.size()-1).yPos;
					bosses.add(new Boss(new Image(ImageFil+BossFil), bossLenght, xpos, ypos, bossTime));
					bossSpawns.remove(i);
					removeTimers.add(i);
				}
			}
			for(Integer i : removeTimers) {
				bossSpawnTimer.remove(removeTimers.get(i));
			}
			
			if(frameCounter%speed == 0) {
				List<Player> setDeadPlayer = new ArrayList<Player>();
				List<Boss> setDeadBosses = new ArrayList<Boss>();
				for(Player p:snakes) {
					if(p.alive) {
						for(Boss b:bosses) {
							if(p.getHead().inList(b.pieces) > 0 && !setDeadPlayer.contains(p)) {
								setDeadPlayer.add(p);
							}
							if(b.getHead().inList(p.pieces) > 0 && !setDeadBosses.contains(b)) {
								setDeadBosses.add(b);
								p.stats.addKill(Dead.Boss);
							}
						}
					}
				}
				for(Boss b : bosses) {
					if(!b.getHead().inBorders(0, 0, xPcs-1, yPcs-1) && !setDeadBosses.contains(b)) {
						setDeadBosses.add(b);
					}
				}
				for(Boss b : setDeadBosses) {
					bosses.remove(b);
				}
				for(Player p : setDeadPlayer) {
					p.setDead(frameCounter, Dead.Boss, null);
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
			if(frameCounter%(int)(speed/bossSpeed) == 0) {
				boolean[][] mat = new boolean[xPcs][yPcs];
				for(int x=0; x<xPcs; x++) {
					for(int y=0; y<yPcs; y++) {
						mat[x][y] = true;
					}
				}
				for(Player p:snakes) {
					for(Piece pc:p.pieces) {
						if(pc.inBorders(0, 0, xPcs, yPcs)) {
							mat[pc.xPos][pc.yPos] = false;
						}
					}
				}
				List<Direction> deadDirs = new ArrayList<Direction>();
				for(Player p : snakes) {
					if(!p.alive) {
						deadDirs.add(p.nextDir);
					}
				}
				for(Boss b : bosses) {
					if(deadDirs.size() == 0) {
						int minDis = xPcs+yPcs;
						Piece target = snakes.get(0).getHead();
						for(Player p: snakes) {
							if(p.alive) {
								int dis = (int) p.getHead().getDis(b.getHead());
								if(dis<minDis) {
									minDis = dis;
									target = p.getHead();
								}
							}
						}
						if(target != null) {
							b.calcDirSlow(mat, target);
						}
					}else {
						b.nextDir = deadDirs.get(0);
						b.changeDirection();
						deadDirs.remove(0);
					}
					b.update();
				}
			}
			for(Boss b : bosses) {
				b.time --;
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
				boolean[][] mat = new boolean[xPcs][yPcs];
				for(int x=0; x<xPcs; x++) {
					for(int y=0; y<yPcs; y++) {
						mat[x][y] = true;
					}
				}
				for(Player p:snakes) {
					if(p.alive) {
						for(Piece pc:p.pieces) {
							if(pc.inBorders(0, 0, xPcs, yPcs)) {
								mat[pc.xPos][pc.yPos] = false;
							}
						}
					}
				}
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
			for(Laser l : lasers) {
				l.remainingTime --;
				if(l.remainingTime <= 0) {
					removeLaser.add(l);
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
			gc.setMouseGrabbed(false);
			drawConName = -1;
			int x = lastIn.getMouseX();
			int y = lastIn.getMouseY();
			boolean backFocus = true;
			for(TextField tf : nameFields) {
				if(x>tf.getX() && x<tf.getX()+tf.getWidth()
				&& y>tf.getY() && y<tf.getY()+tf.getHeight()) {
					drawConName = nameFields.indexOf(tf);
					if(lastIn.isMouseButtonDown(0)) {
						tf.setFocus(true);
					}
				}else {
					if(lastIn.isMouseButtonDown(0)) {
						tf.setFocus(false);
					}
				}
				if(tf.hasFocus()) {
					snakes.get(nameFields.indexOf(tf)).stats.name = tf.getText();
					backFocus = false;
				}
			}
			if(lastIn.isKeyPressed(Input.KEY_P) && backFocus) {
				for(TextField tf : nameFields) {
					choosenNames[nameFields.indexOf(tf)] = tf.getText();
				}
				for(Player p:snakes) {
					p.stats.writeStats();
				}
				reset(gc);
			}
		}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(playing) {
			g.setBackground(back);
			
			for(Piece pc: bossSpawns) {
				int c = 255-255*bossSpawnTimer.get(bossSpawns.indexOf(pc))/bossSpawnTime;
				g.setColor(new Color(c,c,c));
				g.fill(pc.getScaledRect(4.0f));
			}
			
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
			for(Boss bs:bosses) {
				g.setColor(bossCol);
				for(Piece pcs:bs.getPieces()) {
					g.fill(pcs.getPixlRect());
				}
				for(ImgObject img:bs.getImgs()) {
					int x = img.getPixelX()-pieceWidth*(Snake.headScale-1)/2;
					int y = img.getPixelY()-pieceHeight*(Snake.headScale-1)/2;
					g.drawImage(img.img, x, y);
				}
			}
		}else {
			if(displayStats && statsImg != null) {
				g.drawImage(statsImg, 0, 0);
			}else if(!displayHallOfFame) {
				g.drawImage(Back,0,0);
				for(TextField tf:nameFields) {
					g.setColor(snakes.get(nameFields.indexOf(tf)).color);
					tf.render(gc, g);
				}
				if(scores.size() == player) {
					Image winImg = new Image(ImageFil+HeadSvg.get(scores.indexOf(Collections.max(scores)))).getScaledCopy(winImgScale[0], winImgScale[1]);
					
					winImg.rotate(90);
					g.drawImage(winImg,winImgPos[0], winImgPos[1]);
					for(int i=0; i<snakes.size(); i++) {
						Player p = snakes.get(i);
						TextTTFont.drawString(
								nameChoosePos[0]+(int)(screenY*(250.0/1080.0)),
								nameChoosePos[1]+(int)(i*screenY*(50.0/1080.0)),
								": "+p.score, p.color);
					}
				}else {
					Image winImg = new Image(ImageFil+IntroFil).getScaledCopy(winImgScale[0], winImgScale[1]);
					g.drawImage(winImg,winImgPos[0], winImgPos[1]);
				}
				if(drawConName >= 0) {
					TextTTFont.drawString(
							lastIn.getMouseX(),
							lastIn.getMouseY(),
							controller[drawConName]);
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
						newImage = new Image(ImageFil+Heads.get(sttLis.indexOf(Collections.max(sttLis)))).getScaledCopy(HdScale, HdScale);
					}else {
						newImage = new Image(ImageFil+Heads.get(sttLis.indexOf(Collections.max(sttLis)))).getScaledCopy(0, 0);
					}
					newImage.rotate(90);
					bestImgs.add(newImage);
				}
				
				for(int i=0; i<4; i++) {
					g.drawImage(bestImgs.get(i), PklXDis+(PklScale+PklSpac)*i+PklScale/2-HdScale/2, PklYDis-HdScale+30);
					TextTTFont.drawString(PklXDis+(PklScale+PklSpac)*i+PklScale, PklYDis, Integer.toString(Collections.max(l.get(i))), new Color(0,0,0));
					g.drawImage(Pokale[i], PklXDis+(PklScale+PklSpac)*i, PklYDis);
				}
				for(int i=4; i<8; i++) {
					g.drawImage(bestImgs.get(i), PklXDis+(PklScale+PklSpac)*(i-4)+PklScale/2-HdScale/2, PklYDis2-HdScale+30);
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
			lasers.add(new Laser(hd, LaserLenght, p.dir, LaserTime));
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
			/**List<Boss> setDeadBosses = new ArrayList<Boss>();
			for(Boss b:bosses) {
				int hitPc = lasers.get(lasers.size()-1).hits(b);
				if(hitPc >= 0) {
					if(hitPc < b.pieces.size()-1) {
						for(int i=0; i<hitPc; i++) {
							b.pieces.remove(0);
						}
					}else {
						setDeadBosses.add(b);
					}
				}
			}
			for(Boss b : setDeadBosses) {
				bosses.remove(b);
			}**/
		}else if(itm == 4) {
			SnakePro.soundsCln[SnakePro.random.nextInt(SnakePro.soundsCln.length)].play();
			Bot b = new Bot(new Image(ImageFil+BotFil), snakes.indexOf(p), p, startLenght, snakes);
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
		//SnakeProGame1.setFullscreen(true);
		SnakeProGame1.setTargetFrameRate(frameRate);
		SnakeProGame1.setVSync(false);
		SnakeProGame1.start();
		
	}
}
