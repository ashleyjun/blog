package com.dlj.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0;
		
		//传递值，基本类型
		function(i);
		System.out.println(i);
	}

	public static void function(int i) {
		i++;
		i++;
		i++;
		System.out.println(i);
	}
}
