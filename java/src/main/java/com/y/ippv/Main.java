package com.y.ippv;

import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
	    var ippv = new PhoneNo();
	    ippv.add("1", "US");
	    ippv.add("852", "HK");

	    var stream = Stream.of("85940056","185940056","85285940056");
	    stream.forEach(no -> System.out.println(no + " -> " + ippv.match(no)));
	}

}
