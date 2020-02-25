package com.my.ippv;

import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
	    PhoneNo ippv = new PhoneNo();
	    ippv.add("1");
	    ippv.add("852");

	    var stream = Stream.of("85940056","185940056","585940056");
	    stream.forEach(no -> System.out.println(no + " -> " + ippv.match(no)));
	}
}
