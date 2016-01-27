/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.awt.Color;
import java.awt.Graphics;
import visualization.Constants;
import visualization.DatasetOperations;
import visualization.FrameOperations;

/**
 *
 * @author sumana
 */
public class InsertionSort extends javax.swing.JPanel
{
    public int ITERATION_SLEEP = 100;
    int[] insertionData;
    int[] compareIndex = new int[2];
    public InsertionSort(int[] dataset) 
    {
        System.gc();
        System.out.println("Insertion Sort");
        ITERATION_SLEEP = FrameOperations.getSleepTime();
        insertionData = dataset;
        InsertionSort();
    }
    
    private void InsertionSort() 
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                for (int i = 1; i < insertionData.length; i++) 
                {
                    int key = insertionData[i];
                    int j = i - 1;
                    compareIndex[0] = i; compareIndex[1] = j;
                    while(j>=0 && key<insertionData[j]) 
                    {
                        compareIndex[0] = i; compareIndex[1] = j;
                        sleepThread();
                        repaint();
                        insertionData[j+1] = insertionData[j];
                        j--;
                    }
                    insertionData[j+1] = key;
                    sleepThread();
                    repaint();
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
        int width = Constants.x;
        int height = Constants.y;
        int sepVal = insertionData.length-3;
        int rectWidth = (width-sepVal)/insertionData.length;
        int x = 1;
        int num;
        for(int i = 0; i<insertionData.length ; i++)
        {
            num = insertionData[i];
            int colHeight = (int) ((float) height * ((float) num / (float) DatasetOperations.maxInDataset));
            
            if(compareIndex[0] == -1 && compareIndex[1] == -1)
            {
                g.setColor(Color.green);
            }
            else if(i == compareIndex[0] || i == compareIndex[1])
            {
                g.setColor(Color.RED);
            } 
            else
            {
                g.setColor(Color.BLACK);
            }
            g.fillRect(x, height - colHeight, rectWidth, colHeight);
            x += rectWidth + 1;
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawPass(g);
    }
}
