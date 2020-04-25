package snake;

import java.awt.Event;
import java.awt.Image;
import java.awt.Window;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.RefineryUtilities;
import org.newdawn.slick.Input; 

public class BarChart extends JFrame {
	
	public JFreeChart barChart;
	public DefaultCategoryDataset dataset;
	public String chartTitle;
	   
	   public BarChart( String applicationTitle , String chartTitle) {
	      super( applicationTitle );
	      this.chartTitle = chartTitle;
	      this.dataset = new DefaultCategoryDataset();
	   }
	   
	   public JFreeChart createChart() {
		   barChart = ChartFactory.createBarChart(
			         chartTitle,           
			         "Category",            
			         "Score",            
			         dataset,          
			         PlotOrientation.VERTICAL,           
			         true, true, false);
		   /**ChartPanel chartPanel = new ChartPanel(barChart);
		   chartPanel.setPreferredSize(new java.awt.Dimension(SnakePro.screenX,SnakePro.screenY) );   
		   setContentPane( chartPanel );
		   this.pack( );**/
		   return barChart;
	   }
	   
	   public void showChart() {
		   createChart();   
		   RefineryUtilities.centerFrameOnScreen( this ); 
		   this.setVisible( true ); 
	   }
	}
