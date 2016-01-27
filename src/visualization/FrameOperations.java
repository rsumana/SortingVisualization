/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

/**
 *
 * @author sumana
 */

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;
import javax.swing.Box;
import javax.swing.DefaultDesktopManager;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class FrameOperations extends JFrame 
{
    public static JDesktopPane jdp;
    Dimension screenSize;
    GridLayout userOptionsLayout;
    public InternalFrame fUser, fBubble, fSelection, fInsertion, fMerge, fQuick;
    Thread tUser, tBubble, tSelection, tInsertion, tMerge, tQuick;
    JPanel dataSizeSliderPanel, sortednessSliderPanel, speedSliderPanel, buttonPanel, reverseSortPanel;
    public static int dataSetSize, sortedness, speed;
    public static boolean isReverseSort;
    private JCheckBox reverseCheckBox;
    public FrameOperations() 
    {
        super("Visualizing Sorting Techniques");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdp = new JDesktopPane();
        jdp.setDesktopManager(new ImmovableDesktopManager());
        setBounds(0, 0, Constants.xMax,Constants.yMax);
        setContentPane(jdp);
        createFrame();
    }
    private void createFrame() 
    {
        userOptionsLayout = new GridLayout(Constants.noOfPanels,1);
        createUserFrame("Sorting Options", 1);
    }
    private void createUserFrame(String title, int window)
    {
        createAndAddUserFrame(title,window);
        createDataSizeSlider();
        createSortSlider();
        createReverseSortCheckBox();
        createSpeedSlider();
        addStartButton();
        fUser.setVisible(true);
    }
    private void createAndAddUserFrame(String title, int window)
    {
        jdp.setVisible(true);  
        fUser = new InternalFrame(title,window);
        fUser.setLayout(userOptionsLayout);
        jdp.add(fUser);
    }
    private void addStartButton()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Constants.hGap, Constants.vGap));
        buttonPanel.setPreferredSize(Constants.panelSize);
        JButton button = new JButton(Constants.START_BUTTON);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                //System.out.println("Clicked");  
                System.out.println(dataSetSize);
                new DatasetOperations(dataSetSize,sortedness);
                doSort();
            }
        }); 
        buttonPanel.add(button);
        fUser.add(buttonPanel);
    }
    private void doSort()
    {
        String sortingTechnique = "-";
        Runnable sort;
        for(int i = 2; i<7; i++)
        {
            switch(i)
            {
                case 2: sortingTechnique = Constants.BUBBLE_SORT;
                        fBubble = new InternalFrame(sortingTechnique,i);
                        sort = new RunnableSort(i,sortingTechnique,fBubble);
                        tBubble = new Thread(sort);
                        break;
                case 3: sortingTechnique = Constants.SELECTION_SORT; 
                        fSelection = new InternalFrame(sortingTechnique,i);
                        sort = new RunnableSort(i,sortingTechnique,fSelection);
                        tSelection = new Thread(sort);
                        break;
                case 4: sortingTechnique = Constants.INSERTION_SORT; 
                        fInsertion = new InternalFrame(sortingTechnique,i);
                        sort = new RunnableSort(i,sortingTechnique,fInsertion);
                        tInsertion = new Thread(sort);
                        break;
                case 5: sortingTechnique = Constants.MERGE_SORT; 
                        fMerge = new InternalFrame(sortingTechnique,i);
                        sort = new RunnableSort(i,sortingTechnique,fMerge);
                        tMerge = new Thread(sort);
                        break;
                case 6: sortingTechnique = Constants.QUICK_SORT; 
                        fQuick = new InternalFrame(sortingTechnique,i);
                        sort = new RunnableSort(i,sortingTechnique,fQuick);
                        tQuick = new Thread(sort);
                        break;
            }
        }
        startThreads();
    }
    public static int getSleepTime()
    {
        System.out.println(speed);
        if(speed == 1)
        {
            return Constants.SPEED_SLOW;
        }
        else if(speed == 2)
        {
            return Constants.SPEED_MEDIUM;
        }
        return Constants.SPEED_FAST;
        //return 500;
    }
    private void createDataSizeSlider()
    {
        dataSizeSliderPanel = new JPanel();
        dataSizeSliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, Constants.hGap+7, Constants.vGap));
        dataSizeSliderPanel.setPreferredSize(Constants.panelSize);
        JLabel jlabel = new JLabel(Constants.DATASIZE_LABEL);
        jlabel.setFont(new Font(Constants.FONT,Constants.FONT_STYLE,Constants.FONT_SIZE));
        dataSizeSliderPanel.add(jlabel);
        JSlider slider= new JSlider(JSlider.HORIZONTAL,Constants.DATASIZE_MINIMUM,Constants.DATASIZE_MAXIMUM,Constants.DATASIZE_PARTITIONS);
        slider.setMajorTickSpacing(Constants.DATASIZE_PARTITIONS);
        slider.setMinorTickSpacing(Constants.DATASIZE_PARTITIONS);
        slider.setPaintTicks(true);
        slider.setPreferredSize(Constants.sliderLength);
        slider.setValue(Constants.DATASIZE_PARTITIONS);
        dataSetSize = Constants.DATASIZE_PARTITIONS;
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(Constants.DATASIZE_PARTITIONS));
        slider.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                dataSetSize = ((JSlider)e.getSource()).getValue();
            }
        });
        dataSizeSliderPanel.add(slider);
        fUser.setBorder(null);
        fUser.add(dataSizeSliderPanel);
    }

    private void createReverseSortCheckBox()
    {
        reverseSortPanel = new JPanel();
        reverseSortPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Constants.hGap+7, Constants.vGap));
        reverseSortPanel.setPreferredSize(Constants.panelSize);
        reverseCheckBox = new JCheckBox(Constants.REVERSESORT_LABEL);
        reverseCheckBox.setSelected(false);
        reverseCheckBox.setVisible(true);
        isReverseSort = false;
        reverseCheckBox.addItemListener(new ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                isReverseSort = (e.getStateChange()==1)?true:false;
            }
        });
        reverseSortPanel.add(Box.createVerticalGlue());
        reverseSortPanel.add(Box.createVerticalStrut(Constants.y/10 - 15));
        reverseSortPanel.add(Box.createHorizontalGlue());
        reverseSortPanel.add(Box.createHorizontalStrut(Constants.x));
        reverseSortPanel.add(reverseCheckBox);
        fUser.setBorder(null);
        fUser.add(reverseSortPanel);
    }
    private void createSortSlider()
    {
        sortednessSliderPanel = new JPanel();
        sortednessSliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, Constants.hGap, Constants.vGap));
        sortednessSliderPanel.setPreferredSize(Constants.panelSize);
        JLabel jlabel = new JLabel(Constants.SORT_LABEL);
        jlabel.setFont(new Font(Constants.FONT,Constants.FONT_STYLE,Constants.FONT_SIZE));
        sortednessSliderPanel.add(jlabel);
        JSlider slider= new JSlider(JSlider.HORIZONTAL,Constants.SORT_MINIMUM,Constants.SORT_MAXIMUM,Constants.SORT_PARTITIONS);
        slider.setPreferredSize(Constants.sliderLength);
        slider.setMajorTickSpacing(Constants.SORT_PARTITIONS);
        slider.setMinorTickSpacing(Constants.SORT_PARTITIONS);
        slider.setPaintTicks(true);
        slider.setValue(Constants.SORT_PARTITIONS);
        sortedness = Constants.SORT_PARTITIONS;
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(10));
        slider.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                sortedness = ((JSlider)e.getSource()).getValue();
                if(sortedness == 100)
                {
                    reverseCheckBox.setSelected(false);
                    reverseCheckBox.disable();
                }
            }
        });
        sortednessSliderPanel.add(slider);
        fUser.setBorder(null);
        fUser.add(sortednessSliderPanel);
    }
    
    private void createSpeedSlider()
    {
        speedSliderPanel = new JPanel();
        speedSliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, Constants.hGap+5, Constants.vGap));
        JLabel jlabel = new JLabel(Constants.SPEED_LABEL);
        jlabel.setFont(new Font(Constants.FONT,Constants.FONT_STYLE,Constants.FONT_SIZE));
        speedSliderPanel.add(jlabel);
        JSlider slider= new JSlider(JSlider.HORIZONTAL,Constants.SPEED_MINIMUM,Constants.SPEED_MAXIMUM,Constants.SPEED_PARTITIONS);
        slider.setMajorTickSpacing(Constants.SPEED_PARTITIONS);
        slider.setMinorTickSpacing(Constants.SPEED_PARTITIONS);
        slider.setPaintTicks(true);
        slider.setPreferredSize(Constants.sliderLength);
        slider.setValue(2);
        speed = 2;
        slider.setPaintLabels(true);
        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(1, new JLabel(Constants.SPEED_SLOW_LABEL));
        labels.put(2, new JLabel(Constants.SPEED_MEDIUM_LABEL));
        labels.put(3, new JLabel(Constants.SPEED_FAST_LABEL));
        slider.setLabelTable(labels);
        //slider.setLabelTable(slider.createStandardLabels(1));
        slider.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                speed = ((JSlider)e.getSource()).getValue();
            }
        });
        speedSliderPanel.add(slider);
        fUser.setBorder(null);
        fUser.add(speedSliderPanel);
    }

    private void startThreads() 
    {
        tBubble.start();
        tSelection.start();
        tInsertion.start();
        tMerge.start();
        tQuick.start();
    }
    class InternalFrame extends JInternalFrame 
    {
        int x = Constants.x;
        int y = Constants.y;
        public InternalFrame(String str, int i) 
        {
            super(str, false,false,false,false);
            setSize(x, y);
            int xPos = 0, yPos = 0;
            switch(i)
            {
                case 1: xPos = 0; yPos=0;
                        break;
                case 2: xPos = x; yPos=0;
                        break;
                case 3: xPos = 2*x; yPos=0;
                        break;
                case 4: xPos = 0; yPos=y;
                        break;
                case 5: xPos = x; yPos=y;
                        break;
                case 6: xPos = 2*x; yPos=y;
                        break;
            }
            setLocation(xPos, yPos);
        }
        @Override
        public int getWidth()
        {
            return x;
        }
        @Override
        public int getHeight()
        {
            return y;
        }
    }
    private static class ImmovableDesktopManager extends DefaultDesktopManager 
    {
         
        public void dragFrame( JComponent f, int x, int y ) {
            if ( !( f instanceof InternalFrame ) ) {
                super.dragFrame( f, x, y );
            } 
        }
    }
}