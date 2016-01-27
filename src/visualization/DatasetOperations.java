/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import java.util.Random;

/**
 *
 * @author sumana
 */
public class DatasetOperations 
{
    public static int[] DATASET;
    //Generates a data set of given size and given level of sortedness
    public static int maxInDataset = 0;
    DatasetOperations(int length, int sortedness)
    {
        DATASET =  generateDataSet(length,sortedness);
    }
    
    private int[] generateDataSet(int length, int sortedness)
    {
        int[] dataset = randomDataSet(length);
        dataset = sortednessHandler(dataset, sortedness);
        return dataset;
    }
    private int[] randomDataSet(int length)
    {
        int[] randomDataSet = new int[length];
        Random r = new Random();
        for (int i = 0; i < randomDataSet.length; i++) 
        {
            boolean notZero = false;
            while(!notZero)
            {
                int value = r.nextInt(Constants.MAX_VALUE_DATASET);
                if( value != 0 && value>Constants.MIN_VALUE_DATASET)
                {
                    randomDataSet[i] = value;
                    notZero = true;
                }
                else
                {
                    notZero = false;
                }
            }
            if(maxInDataset < randomDataSet[i])
            {
                maxInDataset = randomDataSet[i];
            }
        }
        
        return randomDataSet;
    }
    private int[] sortednessHandler(int[] dataset, int sortedness)
    {
        //Fully order data set. No reverse sorting.
        if(sortedness == 100)
        {
            return sortPartial(dataset,0,dataset.length);
        }
        //Fully Unsorted. Either random or fully reverse sorted
        else if(sortedness == 0)
        {
            if(FrameOperations.isReverseSort)
            {
                return sortReverse(dataset);
            }
            else
            {
                return dataset;
            }
        }
        //Sortedness not 0 or 100
        else
        {
            int subArrayLength = dataset.length * sortedness/100;
            Random r = new Random();
            int start = r.nextInt(dataset.length - subArrayLength);
            int end = start + subArrayLength;
            if(FrameOperations.isReverseSort)
            {
                dataset =  sortReverse(dataset);
            }
            return sortPartial(dataset,start,end);
        }
    }
    private int[] sortPartial(int[] array, int start, int end)
    {
        for (int i = start+1; i < end; i++) 
        {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) 
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    private int[] sortReverse(int[] dataset) 
    {
        for (int i = 0; i < dataset.length; i++) 
        {
            int key = dataset[i];
            int j = i - 1;
            while (j >= 0 && key > dataset[j]) 
            {
                dataset[j + 1] = dataset[j];
                j--;
            }
            dataset[j + 1] = key;
        }
        return dataset;
    }
}
