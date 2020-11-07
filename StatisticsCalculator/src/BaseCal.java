public class BaseCal {
	public String BINtoDEC(String BIN) {
		long temp,DEC = 0;
		for(int i = 0;i<BIN.length();i++) {
			temp = BIN.charAt(i)-48;
			DEC += temp*(Math.pow(2, BIN.length()-(i+1)));
		}
		String DecStr = Long.toString(DEC);
		return (DecStr);
	}
	public String OCTtoDEC(String OCT) {
		long  temp,DEC = 0;
		for(int i = 0;i<OCT.length();i++) {
			temp = OCT.charAt(i)-48;
			DEC += temp*(Math.pow(8, OCT.length()-(i+1)));
		}
		String DecStr = Long.toString(DEC);
		return (DecStr);
	}
	public String HEXtoDEC(String HEX) {
		long  temp = 0,DEC = 0;
		for(int i = 0;i<HEX.length();i++) {
			if (HEX.charAt(i)-48 >= 0 && HEX.charAt(i)-48 <=  9) {
				temp = HEX.charAt(i)-48;
			}
			else {
				if(HEX.charAt(i) == 65) {
					temp = 10;
				}
				else if(HEX.charAt(i) == 66) {
					temp = 11;
				}
				else if(HEX.charAt(i) == 67) {
					temp = 12;
				}
				else if(HEX.charAt(i) == 68) {
					temp = 13;
				}
				else if(HEX.charAt(i) == 69) {
					temp = 14;
				}
				else if(HEX.charAt(i) == 70) {
					temp = 15;
				}
			}
			DEC += temp*(Math.pow(16, HEX.length()-(i+1)));
		}
		String DecStr = Long.toString(DEC);
		return (DecStr);
	}
	
	
	
	
	public String BIN(long DEC) {
		String BIN = "";
		long temp = DEC;
		while(temp >= 2) {
			BIN = Long.toString(temp%2)+BIN;
			temp = temp/2;
		}
		BIN = temp+BIN;
		return BIN;
	}
	public String OCT(long DEC) {
		String OCT = "";
		long temp = DEC;
		while(temp >= 8) {
			OCT = Long.toString(temp%8)+OCT;
			temp = temp/8;
		}
		OCT = temp+OCT;
		return OCT;
	}
	public String HEX(long DEC) {
		String HEX = "";
		long temp = DEC;
		String Tstr = "";
		while(temp >= 16) {
			Tstr = Long.toString(temp%16);
			HEX = toABC(Tstr)+HEX;
			temp = temp/16;
		}
		HEX = toABC(Long.toString(temp))+HEX;
		return HEX;
	}
	public String toABC(String str) {
		if (str.equals("10")) {
			str = "A";
		}
		else if (str.equals("11")) {
			str = "B";
		}
		else if (str.equals("12")) {
			str = "C";
		}
		else if (str.equals("13")) {
			str = "D";
		}
		else if (str.equals("14")) {
			str = "E";
		}
		else if (str.equals("15")) {
			str = "F";
		}
		return str;
	}
}
