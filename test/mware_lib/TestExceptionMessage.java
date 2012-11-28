package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestExceptionMessage {

	@Test
	public void testExceptionMessage() {
		ExceptionMessage excMsg = new ExceptionMessage("exception,42,class java.lang.RuntimeException,SomeText");
		assertEquals(42, excMsg.messageId());
		assertEquals("class java.lang.RuntimeException", excMsg.type());
		assertEquals("SomeText", excMsg.message());
		assertTrue(excMsg.exception());
		assertFalse(excMsg.result());
	}
	
	@Test
	public void testThrowAsException(){
		ExceptionMessage excMsg= new ExceptionMessage("exception,42,class java.lang.NumberFormatException,SomeText");
		try{
			excMsg.throwAsException();
			fail("Exception not thrown");
		}
		catch (NumberFormatException e){
			assertTrue(true);
		} catch (Exception e){
			fail("wrong exception thrown");
		}
		ExceptionMessage excMsg2= new ExceptionMessage("exception,42,class java.lang.IllegalAccessException,SomeText");
		try{
			excMsg2.throwAsException();
			fail("Exception not thrown");
		} catch (Exception e){
			assertTrue(e.getClass().toString().equals("class java.lang.RuntimeException"));
		}
	}
}
