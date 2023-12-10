package org.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;


public class handson {
    public static void main(String[] args) {

        String xyz= "Today is Monday";

        System.out.println(String.valueOf(xyz.charAt(4)));
//        countofOccurance("Today is Monday");
        int[] arr = {1,4,7,8,90,4,3,5,34};
        sorting_array(arr);
    }

    private static void sorting_array(int[] arr) {
        int tmp;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
               if(arr[i]>arr[j]){
                   tmp = arr[i];
                   arr[i]=arr[j];
                   arr[j]=tmp;
               }
            }
        }
        System.out.printf(Arrays.toString(arr));
    }

    private static void countofOccurance(String todayIsMonday) {


        HashMap<Character, Integer> output = new HashMap<>();
        int len = todayIsMonday.length();
        for (int i = 0; i < len; i++) {
            Character val = todayIsMonday.charAt(i);
            if(!output.containsKey(val)){
                output.put(val,1);
            }else {
                output.put(val,output.get(val)+1);
            }
        }

        System.out.println(output);
    }
}

