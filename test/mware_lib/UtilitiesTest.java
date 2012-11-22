package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilitiesTest {

	@Test
	public void testJoin() {
		assertEquals("hallo1,hallo2",Utilities.join(",","hallo1","hallo2"));
		assertEquals("",Utilities.join(","));
		assertEquals("hallo1",Utilities.join(",","hallo1"));
		assertEquals("",Utilities.join(null,"hallo1"));
		assertEquals("",Utilities.join("hallo1",(String[])null));
		assertEquals("hallo1,hallo2,hallo3",Utilities.join(",","hallo1","hallo2","hallo3"));
		
	}
}
