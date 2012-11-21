package mware_lib;

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
}
