package net.greentie.demo;

import net.greentie.util.sort.annotation.Sortable;

public class TestClass{
	@Sortable("string")
	String f1;
	@Sortable("int")
	int f2;
	@Sortable("long")
	long f3;
	@Sortable("double")
	float f4;
	@Sortable("char")
	char f5;
	
	int f6;
	
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s"
				, String.valueOf(f1)	,String.valueOf(f2)
				, String.valueOf(f3)	,String.valueOf(f4)
				, String.valueOf(f5)	,String.valueOf(f6));
	}
}