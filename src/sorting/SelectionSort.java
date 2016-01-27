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
public class SelectionSort extends javax.swing.JPanel
{
    public int ITERATION_SLEEP = 100;
    int[] selectionData;
    int[] compareIndex = new int[2];
    double startTime = 0, endTime = 0;
    public SelectionSort(int[] dataset) 
    {
        System.gc();
        ITERATION_SLEEP = FrameOperations.getSleepTime();
        System.out.println("Selection Sort");
        selectionData = dataset;
        selectionSort();
        
    }
    
    private void selectionSort() 
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                startTime = System.currentTimeMillis();
                for (int i = 0; i < selectionData.length - 1; i++) 
                {
                    int index = i;
                    for (int j = i + 1; j < selectionData.length; j++) 
                    {
                        compareIndex[0] = i; compareIndex[1] = j;
                        if (selectionData[index] > selectionData[j]) 
                        {
                            index = j;
                        }
                        sleepThread();
                        repaint();
                    }
                    if(index!=i)
                    {
                        int temp = selectionData[index];
                        selectionData[index] = selectionData[i];
                        selectionData[i] = temp;
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
        int sepVal = selectionData.length-3;
        int rectWidth = (width-sepVal)/selectionData.length;
        int x = 1;
        int num;
        for(int i = 0; i<selectionData.length ; i++)
        {
            num = selectionData[i];
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
            x += rectWidth  +1;
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawPass(g);
    }
}
