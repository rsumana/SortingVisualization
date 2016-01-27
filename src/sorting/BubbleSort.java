/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.awt.Color;
import java.awt.Graphics;
import visualization.DatasetOperations;
import visualization.FrameOperations;

/**
 *
 * @author sumana
 */
public class BubbleSort extends javax.swing.JPanel
{
    public int ITERATION_SLEEP;
    int[] bubbleData;
    int[] compareIndex = new int[2];
    public BubbleSort(int[] dataset) 
    {
        System.gc();
        ITERATION_SLEEP = FrameOperations.getSleepTime();
        System.out.println("Bubble Sort");
        bubbleData = dataset;
        bubbleSort();
    }
    
    private void bubbleSort() 
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                for (int i = 0; i < bubbleData.length; i++) 
                {
                    for (int j = i + 1; j < bubbleData.length; j++) 
                    {
                        compareIndex[0] = i; compareIndex[1] = j;
                        if (bubbleData[i] > bubbleData[j]) 
                        {
                            int temp = bubbleData[i];
                            bubbleData[i] = bubbleData[j];
                            bubbleData[j] = temp;  
                        }
                        sleepThread();
                        repaint();
                    }
                }         
                compareIndex[0] = -1; compareIndex[1] = -1;
                sleepThread();
                repaint();
            }
        }).start();
    }
    private void sleepThread()
    {
        try 
        {
            Thread.sleep(ITERATION_SLEEP);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
    private void drawPass(Graphics g) 
    {
        int width = getWidth();
        int height = getHeight();
        int sepVal = bubbleData.length-3;
        int rectWidth = (width-sepVal)/bubbleData.length;
        int x = 1;
        int num;
        for(int i = 0; i<bubbleData.length ; i++)
        {
            num = bubbleData[i];
            int colHeight = (int) ((float) height * ((float) num / (float) DatasetOperations.maxInDataset));
            
            //Sorted Portion
            if(compareIndex[0] == -1 && compareIndex[1] == -1)
            {
                g.setColor(Color.green);
            }
            //Entities being compared
            else if(i == compareIndex[0] || i == compareIndex[1])
            {
                g.setColor(Color.RED);
            } 
            //Entities not being compared
            else
            {
                g.setColor(Color.BLACK);
            }
            g.fillRect(x, height - colHeight, rectWidth, colHeight);
            x += rectWidth +1;
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawPass(g);
    }
}
