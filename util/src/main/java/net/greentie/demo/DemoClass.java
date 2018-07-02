package net.greentie.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.greentie.util.sort.Sort;
import net.greentie.util.sort.Sort.Direction;
import net.greentie.util.sort.SortUtils;

public class DemoClass {

	public static 
	List<TestClass> getTestSet(int size){
		TestClass[] test=new TestClass[size];
		Random r=new Random();
		for(int i=0;i<size;i++) {
			test[i]=new TestClass();
			test[i].f2=r.nextInt(size);
			test[i].f3=r.nextLong();
			test[i].f4=r.nextFloat();
			test[i].f5=(char)(r.nextInt(108)+20);
			test[i].f1=r.nextBoolean()?"a":"b";
			test[i].f6=r.nextInt(size);		
		}
		return Arrays.asList(test);
	}

	public static void printList(List<?> x) {
		System.out.println("==start==");
		x.stream().forEach(a->System.out.println(a));
		System.out.println("==end==");
	}


	public static void main(String[] args) {
		List<Sort.Order> orders=new ArrayList<>();
		orders.add(new Sort.Order(Direction.ASC,"string"));
		orders.add(new Sort.Order(Direction.DESC,"int"));
		Sort sort=new Sort(orders);
		SortUtils util=new SortUtils();
		List<TestClass> x=getTestSet(20);
		printList(x);
		util.sort(x, sort, TestClass.class);
		printList(x);
	}
}
