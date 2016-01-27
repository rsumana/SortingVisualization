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
public class QuickSort extends javax.swing.JPanel
{
    public int ITERATION_SLEEP = 100;
    int[] quickData;
    int[] compareIndex = new int[2];
    public QuickSort(int[] dataset) 
    {
        System.gc();
        System.out.println("Quick Sort");
        ITERATION_SLEEP = FrameOperations.getSleepTime();
        quickData = dataset;
        QuickSort();
    }
    
    private void QuickSort() 
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                implementQuickSort(0, quickData.length-1);
                compareIndex[0] = -1; compareIndex[1] = -1;
                repaint();
                sleepThread();
            }
        }).start();
    }
     private void implementQuickSort(int low, int high)
    {
        int i = low, j = high;
        // Get the pivot element
        int pivot = low + (high - low) / 2;
        int value = quickData[pivot];
        // Divide into two lists
        while (i <= j) 
        {

            while (quickData[i] < value) 
            {
                i++; //counter fo values less than pivot
                compareIndex[0] = i; compareIndex[1] = pivot;
                repaint();
                sleepThread();
            }
            while (quickData[j] > value) 
            {
                j--; //counter of values greater than pivot
                compareIndex[0] = j; compareIndex[1] = pivot;
                repaint();
                sleepThread();
            }

            if (i <= j) //swapping i and j values                                           
            {
                compareIndex[0] = i; compareIndex[1] = j;
                int temp = quickData[i];
                quickData[i] = quickData[j];
                quickData[j] = temp;
                i++;
                j--;
            }
            repaint();
            sleepThread();
        }
        // Recursion
        if (low < j) 
        {
            implementQuickSort(low, j);
        }
        if (i < high) 
        {
            implementQuickSort(i, high);
        }
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
        int sepVal = quickData.length-3;
        int rectWidth = (width-sepVal)/quickData.length;
        int x = 1;
        int num;
        for(int i = 0; i<quickData.length ; i++)
        {
            num = quickData[i];
            int colHeight = (int) ((float) height * ((float) num / (float) DatasetOperations.maxInDataset));
            
            if(i == compareIndex[0] || i == compareIndex[1])
            {
                g.setColor(Color.RED);
            } 
            else if(compareIndex[0]==-1 && compareIndex[1]==-1)
            {
                g.setColor(Color.green);
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
