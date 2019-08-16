package com.dlj.test;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i1 = 1;
		int i2 = 1;
		//基础数据类型，直接判断值是否相等  int char boolean short long
		System.out.println(i1 == i2);// true
		String s1 = "Hello";
		String s2 = "Hello";
		
		// 引用数据类型，== 表示判断是否指向同一个对象
		System.out.println(s1 == s2);// true
		String s3 = new String("Hello");
		System.out.println(s1 == s3);//false

		System.out.println(s1.equals(s3));//true
	}

}
