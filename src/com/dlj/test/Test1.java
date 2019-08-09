package com.dlj.test;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Person person = new Person();
			person.setName("Tim");
			person.setAge(25);
			
			Person person1 = new Person();
			person1.setName("Tim");
			person1.setAge(25);
			
//			Object 物体、对象、实体
			
//			匿名 vs 实名
			System.out.println(new Person().getAge());
			
			System.out.println(person.getName());
			System.out.println(person.getAge());
			
			//引用传递，对象
			function(person);
			
			System.out.println(person.getName());
			System.out.println(person.getAge());
	}
	
	public static void function(Person person) {
		person.setName("John");
		person.setAge(30);
		System.out.println(person.getName());
		System.out.println(person.getAge());
	}

}
