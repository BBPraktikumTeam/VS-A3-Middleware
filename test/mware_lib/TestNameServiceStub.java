package mware_lib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestNameServiceStub {

	private static final String NS_HOST = "localhost";
	private static final int NS_PORT = 6666;
	
	private static NameServiceStub nss = null;
	private static Socket socket = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			socket = new Socket(NS_HOST, NS_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		nss = new NameServiceStub(socket);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void nameNotFound() {
		assertNull(nss.resolve("name0"));
	}

	@Test
	public void rebindAndResolve() {
		assertNull(nss.resolve("name1"));
		nss.rebind("", "name1");
		Object obj = nss.resolve("name1");
		assertEquals("class java.lang.Object", obj.getClass().toString());
	}
	
//	@Test
//	public void manager() {
//		nss.rebind(new branch_access.ManagerDummy(), "name2");
//		Object obj = nss.resolve("name2");
//		assertEquals("class branch_access.ManagerStub", obj.getClass().toString());
//	}
	
}
