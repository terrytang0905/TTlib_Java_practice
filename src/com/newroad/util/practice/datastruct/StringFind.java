package com.newroad.util.practice.datastruct;

public class StringFind {
    public static boolean find(int[][] array,int number){
        if(array==null){
            return false;
        }
        int column=array[0].length-1;
        int row=0;
        while (row<array.length && column>=0){
            if(array[row][column]==number){
                return true;
            }
            if(array[row][column]>number){
                column--;
            }else{
                row++;
            } }
        return false;
    }

    public static boolean search(String[] dictionary,String wordToFind){
        if(dictionary==null){
            return false;
        }
        if(dictionary.length==0){
            return false;
        }

        int left=0,right=dictionary.length-1;
        while (left<=right){
            int middle=(left+right)/2;
            if(dictionary[middle].equals(wordToFind)){
                return true;
            }else{
                if(dictionary[middle].compareTo(wordToFind)>0){
                    right=middle-1;
                }else{
                    left=middle-1;
                }
            }
        }
        return false;
    }

    public static void main(String args[]){
        int[][] testarray=new int[4][4];
        testarray[0][0]=1;
        testarray[0][1]=2;
        testarray[0][2]=8;
        testarray[0][3]=9;
        testarray[1][0]=2;
        testarray[1][1]=4;
        testarray[1][2]=9;
        testarray[1][3]=12;
        testarray[2][0]=4;
        testarray[2][1]=7;
        testarray[2][2]=10;
        testarray[2][3]=13;
        testarray[3][0]=6;
        testarray[3][1]=8;
        testarray[3][2]=11;
        testarray[3][3]=15;
        System.out.println(find(testarray, 1));
    }
}
