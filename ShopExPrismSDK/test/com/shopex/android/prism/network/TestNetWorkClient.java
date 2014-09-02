package com.shopex.android.prism.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestNetWorkClient {

	NetworkClient client;
	
	@Before
	public void init(){
		client = new NetworkClient(null, null);
	}
	@Test
	public void testArrayCopy() {
		String[] str1 = {"one","two"};
		String[] str2 = {"three","four","five"};
		
		String[] finalArray = client.concatAll(str1, str2);
		assertTrue("size", finalArray.length == 5 );
		assertArrayEquals(new String[]{"one","two","three","four","five"}, finalArray);
	}

}
