package mware_lib;

import java.net.InetSocketAddress;

public class Utilities {

	private Utilities() {
	}

	public static String getTypeForObject(Object obj) {
		String type = "java.lang.Object";
		Class<?> clazz = obj.getClass();
		while (clazz.getSuperclass() != null) {
			if (clazz.toString().equals("class branch_access.Manager")) {
				type = "branch_access.Manager";
				break;
			} else if (clazz.toString().equals("class cash_access.Account")) {
				type = "cash_access.Account";
				break;
			}
			clazz = clazz.getSuperclass();
		}
		return type;
	}

	public static String join(String separator, String... strings) {
		String result = "";
		if (separator != null && strings != null) {
			for (int i = 0; i < strings.length; i++) {
				result += strings[i];
				if ((i + 1) != strings.length)
					result += separator;
			}
		}
		return result;
	}

	static Object createProxy(String name, String type, String host, int port) {
		Object result = null;
		try {
			final Class<?>[] CONSTRUCTOR_SIGNATURE = {
					Class.forName("java.lang.String"),
					Class.forName("java.net.InetSocketAddress") };
			final Object[] CONSTRUCTOR_ARGS = { "name",
					InetSocketAddress.createUnresolved("localhost", 1234) };
			if (type.equals("branch_access.Manager")) {
				result = Class.forName("branch_access.ManagerProxy")
						.getConstructor(CONSTRUCTOR_SIGNATURE)
						.newInstance(CONSTRUCTOR_ARGS);
			} else if (type.equals("cash_access.Account")) {
				result = Class.forName("cash_access.AccountProxy")
						.getConstructor(CONSTRUCTOR_SIGNATURE)
						.newInstance(CONSTRUCTOR_ARGS);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	static Skeleton createSkeleton(String name, Object servant) {
		Object result = null;
		String type = getTypeForObject(servant);
		try {
			if (type.equals("branch_access.Manager")) {
				final Class<?>[] CONSTRUCTOR_SIGNATURE = {
						Class.forName("java.lang.String"),
						Class.forName("branch_access.Manager") };
				final Object[] CONSTRUCTOR_ARGS = { name,
						Class.forName("branch_access.Manager").cast(servant)};
				result = Class.forName("branch_access.ManagerSkeleton")
						.getConstructor(CONSTRUCTOR_SIGNATURE)
						.newInstance(CONSTRUCTOR_ARGS);
			} else if (type.equals("cash_access.Account")) {
				final Class<?>[] CONSTRUCTOR_SIGNATURE = {
						Class.forName("java.lang.String"),
						Class.forName("cash_access.Account") };
				final Object[] CONSTRUCTOR_ARGS = { name,
						Class.forName("cash_access.Account").cast(servant)};
				result = Class.forName("cash_access.AccountSkeleton")
						.getConstructor(CONSTRUCTOR_SIGNATURE)
						.newInstance(CONSTRUCTOR_ARGS);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			throw new RuntimeException();
		}
		return (Skeleton)result;
	}
}
