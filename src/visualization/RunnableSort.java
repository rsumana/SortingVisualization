/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import javax.swing.SwingUtilities;
import sorting.BubbleSort;
import sorting.InsertionSort;
import sorting.MergeSort;
import sorting.QuickSort;
import sorting.SelectionSort;
import visualization.FrameOperations.InternalFrame;


/**
 *
 * @author sumana
 */
public class RunnableSort extends Thread
{
    private final int window;
    private final String sortType;
    private final InternalFrame fSort;
    
    RunnableSort(int windowNumber,String sortingType, InternalFrame f)
    {
        this.window = windowNumber;
        this.sortType = sortingType;
        fSort = f;
        fSort.setBorder(null);
        FrameOperations.jdp.add(fSort);
        System.out.println("Creating Thread - "+sortType+", "+window);
        //fSort.setVisible(true);
    }
    @Override
    public void run()
    {
        checkWindowAndRun();
        System.out.println("Running Thread - "+sortType+", "+window);
    }
    private void checkWindowAndRun()
    {
        Runnable run = null;
        switch(window)
        {
            case 2: run = bubbleFrame();break;
            case 3: run = selectionFrame();break;
            case 4: run = insertionFrame();break;
            case 5: run = mergeFrame();break;
            case 6: run = quickFrame();break;
        }
        SwingUtilities.invokeLater(run);
    }
    private Runnable mergeFrame()
    {
        System.out.println("FrameOperations.mergeFrame");
        int[] mergeFrame = new int[DatasetOperations.DATASET.length];
        copyTo(mergeFrame,DatasetOperations.DATASET);
        Runnable merge = new Runnable() 
        {
            @Override
            public void run() 
            {
                
                fSort.add(new MergeSort(mergeFrame));
                fSort.setVisible(true);
            }
        };
        return merge;
    }
    private Runnable quickFrame()
    {
        System.out.println("FrameOperations.quickFrame");
        int[] quickFrame = new int[DatasetOperations.DATASET.length];
        copyTo(quickFrame,DatasetOperations.DATASET);
        Runnable quick = new Runnable() 
        {
            @Override
            public void run() 
            {
                fSort.add(new QuickSort(quickFrame));
                fSort.setVisible(true);
            }
        };
        return quick;
        
    }
    private Runnable selectionFrame()
    {
        System.out.println("FrameOperations.selectionFrame");
        int[] selectionFrame = new int[DatasetOperations.DATASET.length];
        copyTo(selectionFrame,DatasetOperations.DATASET);
        Runnable selection = new Runnable() 
        {
            @Override
            public void run() 
            {
                fSort.add(new SelectionSort(selectionFrame));
                fSort.setVisible(true);
            }
        };
        return selection;
    }
    private Runnable bubbleFrame()  
    {
        System.out.println("FrameOperations.bubbleFrame");
        int[] bubbleFrame = new int[DatasetOperations.DATASET.length];
        copyTo(bubbleFrame,DatasetOperations.DATASET);
        Runnable bubble = new Runnable() 
        {
            @Override
            public void run() 
            {
                fSort.add(new BubbleSort(bubbleFrame));
                fSort.setVisible(true);
            }
        };
        return bubble;
    }
    private Runnable insertionFrame()
    {
        System.out.println("FrameOperations.insertionFrame");
        int[] insertionFrame = new int[DatasetOperations.DATASET.length];
        copyTo(insertionFrame,DatasetOperations.DATASET);
        Runnable insertion = new Runnable() 
        {
            @Override
            public void run() 
            {
                fSort.add(new InsertionSort(insertionFrame));
                fSort.setVisible(true);
                
            }
        };
        return insertion;
    }
    private void copyTo(int[] a, int[] b)
    {
        for(int i = 0; i<b.length; i++)
        {
            a[i] = b[i];
        }
    }
    
}
