/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author sumana
 */
public class Constants 
{
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int xMax = screenSize.width+5;
    public static int yMax = screenSize.height-30;
    public static int x = xMax / 3;
    public static int y = (yMax -10 )/ 2;
    
    public static final int noOfPanels = 5;
    public static Dimension panelSize = new Dimension(x,y/noOfPanels);
    public static final int vGap = 5;
    public static final int hGap = 20;
    
    public static Dimension sliderLength = new Dimension ((2*x)/3, y/4);
    
    public static final String FONT = "Arial";
    public static final int FONT_SIZE = 12;
    public static final int FONT_STYLE = 1;
    
    public static final String START_BUTTON = "Start";
    
    public static final int SPEED_SLOW = 1000;
    public static final int SPEED_MEDIUM = 100;
    public static final int SPEED_FAST = 10;
    public static final String SPEED_SLOW_LABEL = "Slow";
    public static final String SPEED_MEDIUM_LABEL = "Normal";
    public static final String SPEED_FAST_LABEL = "Fast";
    
    public static final String DATASIZE_LABEL = "Dataset Size";
    public static final String SORT_LABEL = "Sortedness (%)";
    public static final String SPEED_LABEL = "Speed of Sort";
    public static final String REVERSESORT_LABEL = "Reverse Sort the rest?";
    
    public static final int DATASIZE_MINIMUM = 20;
    public static final int DATASIZE_MAXIMUM = 220;
    public static final int DATASIZE_PARTITIONS = 20;
    
    public static final int SORT_MINIMUM = 0;
    public static final int SORT_MAXIMUM = 100;
    public static final int SORT_PARTITIONS = 10;
    
    public static final int SPEED_MINIMUM = 1;
    public static final int SPEED_MAXIMUM = 3;
    public static final int SPEED_PARTITIONS = 1;
    
    public static final String BUBBLE_SORT = "Bubble Sort";
    public static final String SELECTION_SORT = "Selection Sort";
    public static final String INSERTION_SORT = "Insertion Sort";
    public static final String MERGE_SORT = "Merge Sort";
    public static final String QUICK_SORT = "Quick Sort";
    
    public static int MAX_VALUE_DATASET = 1000;
    public static int MIN_VALUE_DATASET = 200;
    
}
