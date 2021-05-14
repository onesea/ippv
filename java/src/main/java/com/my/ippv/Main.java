package com.my.ippv;

import io.lktk.NativeBLAKE3;
import io.lktk.NativeBLAKE3Util;

import java.util.Base64;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
	    var ippv = new PhoneNo();
	    ippv.add("1", "US");
	    ippv.add("852", "HK");

	    var stream = Stream.of("85940056","185940056","85285940056");
	    stream.forEach(no -> System.out.println(no + " -> " + ippv.match(no)));
	    testBLAKE3();
	}

	static void testBLAKE3() {
		assert(io.lktk.NativeBLAKE3.isEnabled());
		// Initialize the hasher
		var hasher = new NativeBLAKE3();
		hasher.initDefault();

		//read data
		var data = "my data".getBytes();
		hasher.update(data);
		//more data
		var moredata = "more data. ".getBytes();
		hasher.update(moredata);

		//Finalize the hash. BLAKE3 output lenght defaults to 32 bytes
		try {
			var output = hasher.getOutput();
			System.out.println(output.length + " " + new String(Base64.getEncoder().encode(output)));
		} catch (NativeBLAKE3Util.InvalidNativeOutput invalidNativeOutput) {
			invalidNativeOutput.printStackTrace();
		}

		//hasher should be treated as a resource since there is an equivalent object allocated in memory in c.
		hasher.close();
	}
}
