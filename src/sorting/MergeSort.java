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
public class MergeSort extends javax.swing.JPanel
{
    public int ITERATION_SLEEP = 100;
    int[] mergeData;
    int[] compareIndex = new int[2];
    public MergeSort(int[] dataset) 
    {
        System.gc();
        System.out.println("Merge Sort");
        ITERATION_SLEEP = FrameOperations.getSleepTime();
        mergeData = dataset;
        MergeSort();
    }
    
    private void MergeSort() 
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                mergesort(0,mergeData.length-1);
                compareIndex[0] = -1; compareIndex[1] = -1;
                repaint(); sleepThread();
            }
        }).start();
    }
    private void mergesort(int low, int high) 
    {
        if (low < high) 
        {
            int middle = low + (high - low) / 2;
            compareIndex[0] = low;
            compareIndex[1] = middle;
            repaint();
            sleepThread();
            mergesort(low, middle);
            compareIndex[0] = middle+1;
            compareIndex[1] = high;
            repaint();
            sleepThread();
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }
  private void merge(int low, int middle, int high) 
    {
        int[] temp = new int[mergeData.length];

        for (int i = low; i <= high; i++) 
        {
            temp[i] = mergeData[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) 
        {
            if (temp[i] <= temp[j]) 
            {
                mergeData[k] = temp[i];
                compareIndex[0] = i; compareIndex[1] = k;
                i++;
            } 
            else 
            {
                mergeData[k] = temp[j];
                compareIndex[0] = j; compareIndex[1] = k;
                j++;
            }
            k++;
            repaint();
            sleepThread();
        }
        while (i <= middle) 
        {
            mergeData[k] = temp[i];
            compareIndex[0] = i; compareIndex[1] = k;
            k++;
            i++;
            repaint();
            sleepThread();
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
        int width = getWidth()+10;
        int height = getHeight();
        int sepVal = mergeData.length-3;
        int rectWidth = (width-sepVal)/mergeData.length;
        int x = 1;
        int num;
        for(int i = 0; i<mergeData.length ; i++)
        {
            num = mergeData[i];
            int colHeight = (int) ((float) height * ((float) num / (float) DatasetOperations.maxInDataset));
            
            if(i == compareIndex[0] || i == compareIndex[1])
            {
                g.setColor(Color.RED);
            } 
            else if(compareIndex[0] == -1 && compareIndex[1] == -1)
            {
                g.setColor(Color.green);
            }
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
