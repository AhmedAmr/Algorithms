import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by AhmedAmr on 1/15/16.
 */
public class MaxSubArray {
    /**
     * This is an implementation for Kadane's Algorithm for calculating the Maximum subarray in O(n)
     * @param a : the array of integers
     * @param size : the size of the array
     * @return a MaxSumNode contains the max sum subarray along with the start and the end of the interval
     */
    public static MaxSumNode getMaxSum(int a[],int size){
        MaxSumNode result = new MaxSumNode();
        int currentMax=0;
        int currentStart=0;
        for (int currentEnd = 0; currentEnd < size; currentEnd++) {
            currentMax+=a[currentEnd];

            if(currentMax>=result.max){
                if((currentMax == result.max)&&(result.end-result.start)<(currentEnd-currentStart))
                    result = new MaxSumNode(currentMax,currentStart,currentEnd);
                else if(currentMax>result.max){
                    result = new MaxSumNode(currentMax,currentStart,currentEnd);
                }
            }

            if(currentMax<0){
                currentMax=0;
                currentStart=currentEnd+1;
            }
        }
        return result;
    }

}
class MaxSumNode
{

    public MaxSumNode() {
    }

    public MaxSumNode(int max, int start, int end) {
        this.max = max;
        this.start = start;
        this.end = end;
    }

    int max=Integer.MIN_VALUE;
    int start = 0;
    int end = 0;

}

