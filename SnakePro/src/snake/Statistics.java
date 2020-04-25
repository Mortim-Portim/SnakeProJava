
package snake;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.WindowConstants;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class Statistics {
	public static String StatFil = SnakePro.StatFil;
	public static String splitter = ";";
	public static List<String[]> loadedFil;
	
	public String name;
	public int lineIndex;
	//Bomb, Wall, Laser, Stupid, Player, Bot, Winner
	public int wins;
	public int deaths;
	public int kills;
	
	public int bombD;
	public int wallD;
	public int laserD;
	public int stupidD;
	public int playerD;
	public int botD;
	public int bossD;
	
	public int bombK;
	public int laserK;
	public int skillK; //player ohne alles gekillt
	public int botK;
	public int bossK;
	
	public String controllerName;
		
	public Statistics(String name, String controller) {
		this.name = name;
		this.controllerName = controller.replace(" ", "");
		this.getStats();
	}
	
	public void addDeath(Dead d) {
		//Bomb, Wall, Laser, Stupid, Player, Bot, Winner
		switch (d) {
		case Bomb:
			bombD ++;
			break;
		case Wall:
			wallD ++;
			break;
		case Laser:
			laserD ++;
			break;
		case Stupid:
			stupidD ++;
			break;
		case Player:
			playerD ++;
			break;
		case Bot:
			botD ++;
			break;
		case Boss:
			bossD ++;
			break;
		case Winner:
			wins ++;
			break;
		}
		if(d != Dead.Winner) {
			deaths ++;
		}
	}
	
	public void addKill(Dead d) {
		switch (d) {
		case Bomb:
			bombK ++;
			break;
		case Laser:
			laserK ++;
			break;
		case Player:
			skillK ++;
			break;
		case Bot:
			botK ++;
			break;
		case Boss:
			bossK ++;
			break;
		}
		kills ++;
	}
	
	public void getStats() {
		for(int i=0; i<loadedFil.size(); i++) {
			if(this.name.equals(loadedFil.get(i)[0])) {
				lineIndex = i;
				this.wins = Integer.parseInt(loadedFil.get(i)[1]);
				this.deaths = Integer.parseInt(loadedFil.get(i)[2]);
				this.kills = Integer.parseInt(loadedFil.get(i)[3]);
				this.bombD = Integer.parseInt(loadedFil.get(i)[4]);
				this.wallD = Integer.parseInt(loadedFil.get(i)[5]);
				this.laserD = Integer.parseInt(loadedFil.get(i)[6]);
				this.stupidD = Integer.parseInt(loadedFil.get(i)[7]);
				this.playerD = Integer.parseInt(loadedFil.get(i)[8]);
				this.botD = Integer.parseInt(loadedFil.get(i)[9]);
				this.bossD = Integer.parseInt(loadedFil.get(i)[10]);
				
				this.bombK = Integer.parseInt(loadedFil.get(i)[11]);
				this.laserK = Integer.parseInt(loadedFil.get(i)[12]);
				this.skillK = Integer.parseInt(loadedFil.get(i)[13]);
				this.botK = Integer.parseInt(loadedFil.get(i)[14]);
				this.bossK = Integer.parseInt(loadedFil.get(i)[15]);
				break;
			}
		}
	}
	
	public void writeStats() {
		int indx=-1;
		for(int i=0; i<loadedFil.size(); i++) {
			if(this.name.equals(loadedFil.get(i)[0])) {
				indx = i;
				break;
			}
		}
		String[] selfStats = {
				name,
				Integer.toString(wins),
				Integer.toString(deaths),
				Integer.toString(kills),
				Integer.toString(bombD),
				Integer.toString(wallD),
				Integer.toString(laserD),
				Integer.toString(stupidD),
				Integer.toString(playerD),
				Integer.toString(botD),
				Integer.toString(bossD),
				Integer.toString(bombK),
				Integer.toString(laserK),
				Integer.toString(skillK),
				Integer.toString(botK),
				Integer.toString(bossK),
				controllerName
				};
		if(indx != -1) {
			loadedFil.set(indx, selfStats);
		}else {
			loadedFil.add(selfStats);
		}
		
		try {
			PrintWriter writer = new PrintWriter(StatFil);
			for(String[] line:loadedFil) {
				for(int a=0; a<line.length; a++) {
					writer.write(line[a]+";");
				}
				writer.write("\n");
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadStats() {
		try {
			FileInputStream fis = new FileInputStream(StatFil);
			Scanner sc = new Scanner(fis);
			List<String[]> lines = new ArrayList<String[]>();
			while(sc.hasNextLine()) {
				String l = sc.nextLine().replace(" ", "");
				String [] ls = l.split(splitter);
				lines.add(ls);
			}
			sc.close();
			loadedFil = lines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static BarChart chart;
	
	public static JFreeChart showStats() {
		chart = new BarChart("Player Stats", "Player Stats");
		for(int i=1; i<loadedFil.size(); i++) {
			for(int a=1; a<loadedFil.get(i).length-1; a++) {
				int value = Integer.parseInt(loadedFil.get(i)[a]);
				String player = loadedFil.get(i)[0];
				String stat = loadedFil.get(0)[a];
				chart.dataset.addValue(value , player , stat);
			}
		}
		return chart.createChart();
	}
		
	public static boolean isShowingStats() {
		if(chart != null) {
			return true;
		}
		return false;
	}
	
	public int getValue(int index) {
		switch (index) {
		case 0:
			return wins;
		case 1:
			return kills;
		case 2:
			return bombK;
		case 3:
			return laserK;
		case 4:
			return stupidD;
		case 5:
			return botK;
		case 6:
			return skillK;
		case 7:
			return deaths;
		}
		return 0;
	}
	
	public static void closeStats() {
		chart.setVisible(false);
	}

}
