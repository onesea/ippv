package com.my.ippv;

public class Main {
	public static void main(String[] args) {
	    PhoneNo ippv = new PhoneNo();
	    ippv.add("1");
	    ippv.add("852");

	    int len = ippv.match("85940056");
	    System.out.println("len = " + len);

		len = ippv.match("185940056");
		System.out.println("len = " + len);

		len = ippv.match("585940056");
		System.out.println("len = " + len);
	}
}
