package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestObjectBroker {

	private static final String NS_HOST = "localhost";
	private static final int NS_PORT = 6666;

	@Test
	public void test() {
		ObjectBroker broker = ObjectBroker.getBroker(NS_HOST, NS_PORT);
		assertNotNull(broker);
		NameService ns = broker.getNameService();
		assertNotNull(ns);
	}

}
