package com.blog.blogapp.entity;

public class Answers {

    static int[] a = new int[] {1, 9, 6, 4,5};

    //method for inversion
    static int  inversionCount(int n) {
        int count = 0;
        for (int i = 0; i < n-1; i++ ) {
             for (int j=i+1; j<n ; j++) {
                 if (a[i]>a[j]) {
                     count++;
                 }
             }
        }
        return  count;
    }

    //method for sum of n digit digit  equal to sum
    static void findNDigitSum(String result, int n , int target) {
        //base case
        //if n is less than n-digit and its sum of digit is less than given sum
        if (n>0 && target>=0) {
            char d = '0';
            if (result.equals("")) {  //case number cannot start from 0
                d = '1';
            }
            //for valid digit put in current index and recursion for next index
            while (d<='9') {
                findNDigitSum(result+d,n-1,target-(d-'0'));
                d++;
            }
        }
        //print if number becomes n-digit and sum equals to given sum
        else if (n==0 && target == 0) {
              System.out.print(result + " ");
        }

    }

    public static void main(String[] args) {

        System.out.println("Total number of inversions are " +inversionCount(a.length));

        int num = 9;
        int target = 81;
        String result = "";
        findNDigitSum(result,num,target);
    }

}
