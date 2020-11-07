public class ChekStr {
	public static boolean isnumplusLONG(String str) {
		long num;
		if (str == null || str.length() == 0) {
			return false;
		}
		try {
			//num = Integer.parseInt(str);
			num = Long.parseLong(str);
			if (num > 0) {
				return true;
			}
			else {
				return false;
			}
		}catch (NumberFormatException e) {
			return false;
		}
		
	}

	public static boolean isnumplus(String str) {
		int num;
		if (str == null || str.length() == 0) {
			return false;
		}
		try {
			num = Integer.parseInt(str);
			if (num > 0) {
				return true;
			}
			else {
				return false;
			}
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	public static boolean isnumplus2(String str) {
		int num;
		if (str == null) {
			return true;
		}
		else if (str.length() == 0) {
			return false;
		}
		
		try {
			num = Integer.parseInt(str);
			if (num > 0) {
				return true;
			}
			else {
				return false;
			}
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	public static boolean isnumNor(String str) {
		double num;
		
		if (str == null) {
			return true;
		}
		try {
			num = Double.parseDouble(str);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
}
