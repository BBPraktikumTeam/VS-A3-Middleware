package mware_lib;

import static org.junit.Assert.*;
import static mware_lib.MessageId.getNewId;
import org.junit.Test;

public class TestMessageId {

	@Test
	public void messageIdInc() {
		long id1 = getNewId();
		long id2 = getNewId();
		assertTrue(id2 > id1);
	}

}
