package com.example.community;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 反转给定的整数
     *
     * @param x int整型 待反转的整数
     * @return int整型
     */
    public int reverse(int x) {
        // write code here
        String str = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder(str);
        StringBuilder res = new StringBuilder();
        if (stringBuilder.charAt(0) == '-') {
            res = new StringBuilder(stringBuilder.substring(1, stringBuilder.length())).reverse();
            while (res.charAt(0) == '0') {
                res.deleteCharAt(0);
            }
            res.insert(0, '-');
        } else {
            res = stringBuilder.reverse();
            while (res.charAt(0) == '0') {
                res.deleteCharAt(0);
            }
        }
        return Integer.parseInt(res.toString());
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 二进制字符串相加
     *
     * @param a string字符串
     * @param b string字符串
     * @return string字符串
     */
    public String addBinary(String a, String b) {
        // write code here
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i = 0;
        while (a.length() > i || b.length() > i || carry == 1) {
            int x = Integer.parseInt(i < a.length() ? a.substring(i, i + 1) : "0");
            int y = Integer.parseInt(i < b.length() ? b.substring(i, i + 1) : "0");
            int z = x + y + carry;
            res.append(z % 2);
            carry = z / 2;
            i++;
        }
        return res.reverse().toString();
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // write code here
        int m = nums1.length;
        int n = nums2.length;
        int i;
        int j;
        if ((m + n) % 2 == 1) {
            i = j = (m + n) / 2;
        } else {
            j = (m + n) / 2;
            i = j - 1;
        }
        int num1 = 0;
        int num2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (num1 < m && num2 < n && list.size() <= j) {
            list.add(nums1[num1] < nums2[num2] ? nums1[num1++] : nums2[num2++]);
        }
        while (num1 < m && list.size() <= j) {
            list.add(nums1[num1++]);
        }
        while (num2 < n && list.size() <= j) {
            list.add(nums2[num2++]);
        }
        return ((double) list.get(i) + (double) list.get(j)) / 2;
    }
}