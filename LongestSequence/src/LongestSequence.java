import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by AhmedAmr on 1/1/16.
 */
public class LongestSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> line = readInts(nextLine(in));
        int n = line.get(0);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(readInts(nextLine(in)).get(0));
        }

        System.out.println(LIS_fast(res));
    }

    /**
     * Longest Increasing Subsequence (LIS) of a list of integers.
     * Running time : O(n^2). look for LIS_fast for calculating length of LIS in O(nlgn ).
     * @param input : the list that contains the integers.
     * @return The LIS of the input list.
     */
    public static ArrayList<Integer> LIS (ArrayList<Integer> input){
        int size = input.size();
        ArrayList<ArrayList<Integer>> L = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        //init
        current.add(input.get(0));
        L.add(new ArrayList<>(current));

        for (int i = 1; i < size; i++) { /// O(N)
            current = new ArrayList<>();
            for (int j = 0; j < i; j++) { /// O(N)
                if(input.get(j)<input.get(i) && L.get(j).size()>current.size()){ // Strict Increasing
                    current = new ArrayList<>(L.get(j));
                }
            }
            current.add(input.get(i));
            if(result.size()<current.size()){
                    result = new ArrayList<>(current);
            }
            L.add(new ArrayList<>(current));
        }

        return result;
    }


    /**
     * This algorithm calculates the Longest Increasing Sub-Sequence length .
     * Running time : O(nlgn)
     * @param input : list that have the integers .
     * @return the length of the longest increasing sub-sequence
     *
     * @dependes position_fast method : to get the update index.
     */
    public static int LIS_fast(ArrayList<Integer>input){
        ArrayList<Integer>res = new ArrayList<>();
        int last = 0;
        res.add(input.get(0));
        input.remove(0);
        for (Integer integer : input) { // O(n)
            if(integer>res.get(last)){
                //append it to the result
                res.add(integer);
                last++;
            }else {
                int index = position_fast(res,integer); // O(lg n)
                if(index!=-1){
                    res.set(index,integer);
                }
            }
        }
        return last+1;

    }

    /**
     * This is method to get the position for update using binary search.
     * Running time : O(lg n)
     * @param list: the list that search will be apply on
     * @param value : the value need to get the appropriate position for it
     * @return {
     *     -1 : if the value is already in the list
     *     i : where i the appropriate insertion index where list[i] is strictly greater than value && list[i-1] (if found) is strictly less than value.
     * }
     *
     */
    public static int position_fast(ArrayList<Integer>list,int value){
        int start = 0;
        int end = list.size()-1;
        while(start!=end){
            int mid = (int)Math.floor((start+end)/2);
            if(value==list.get(mid))return -1;
            if(value>list.get(mid)){
                start=mid+1;
            }else{
                end = mid;
            }
        }
        return start;
    }


    public static ArrayList<Integer> readInts(String line) {
        String[] arr = line.split(" ");
        ArrayList<Integer> res = new ArrayList<>();
        for (String s : arr) {
            if (!s.isEmpty()) res.add(Integer.parseInt(s));
        }
        return res;
    }


    public static String nextLine(BufferedReader in) throws IOException {
        String line = in.readLine();
        while (line.isEmpty()) {
            line = in.readLine();
        }
        return line;
    }

}
