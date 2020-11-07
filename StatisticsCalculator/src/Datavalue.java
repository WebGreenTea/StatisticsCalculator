public class Datavalue {
	int N = 0;
	boolean type = true;
	double dataAll[];
	double dataAll2[];
	//N value
	public void setN(int n) {
		N = n;
	}
	public int getN() {
		return N;
	}
	
	//type of data [true is ประชากร][false is ตัวอย่าง]
	public void settype(int t) {
		if (t == 0) {
			type = true;
		}
		else if (t == 1) {
			type = false;
		}
		 
	}
	public int dataInarr() {
		int n = 0;
		try {
			for(int i = 0;i<dataAll.length;i++) {
				n += 1;
			}
			return n;
		}catch(Exception e) {
			return 0;
		}
	}
	
	public void setArray(int n) {
		dataAll = new double[n];
		dataAll2 = new double[n];
		//System.out.print("Set array");
	}
	public void setArrangeData() {
		dataAll2 = new double[dataAll.length];
		for(int i = 0;i<dataAll.length;i++) {
			dataAll2[i] = dataAll[i];
		}
		
		//bubble sort
		for(int pass = 1;pass < dataAll2.length;pass++) {
			for(int i = 0;i<dataAll2.length-pass;i++) {
				if (dataAll2[i] > dataAll2[i+1]) {
					double temp = dataAll2[i];
					dataAll2[i] = dataAll2[i+1];
					dataAll2[i+1] = temp;	
				}
			}
		}
	}
	public boolean gettype() {
		return type;
	}

	public double  AvgCal() {
		double Avg = 0;
		double sum = 0;
		for(int i = 0;i<dataAll2.length;i++) {
			sum += dataAll2[i];
		}
		Avg = sum/N;
		
		return Avg;
	}
	public double MedCal() {
		double Med;
		int n;
		n = (N/2);
		if (N%2 == 0) {
			Med = (dataAll2[n] + dataAll2[n-1])/2;
		}
		else {
			
			Med = dataAll2[n];
		}
		return Med;
	}
	public String ModeCal() {
		int temp = 0;
		int NumOfRe = 0;
		int N1Mode = -1;
		int N2Mode = -1;
		boolean haveMode = true;
		for(int i = 0;i<dataAll2.length;i++) {
			for(int j = i+1;j<dataAll2.length;j++) {
				if (dataAll2[i] == dataAll2[j]) {
					temp +=1;
				}
			}
			if(temp > NumOfRe ) {
				NumOfRe = temp;
				N1Mode = i;
				N2Mode = -1;
				haveMode = true;
			}
			else if (temp == NumOfRe) {
				
				if (N2Mode == -1) {
					N2Mode = i;
				}
				else {
					haveMode = false;
				}
				
			}
			temp = 0;
		}
		if (haveMode) {
			if (N2Mode == -1) {
				return ("ฐานนิยม คือ " + dataAll2[N1Mode]);
			}
			else {
				return ("ฐานนิยม คือ " + dataAll2[N1Mode] + " และ " +dataAll2[N2Mode]);
			}
		}
		else {
			return "ไม่มีฐานนิยม";
		}
	}
	public double PtilCal(int r) {
		
		double Ptil = 0;
		double position  = 0;
		position = r/(double)100*(N+1);
		//position = (r*(N+1))/100;
		if (position >= dataAll2.length) {
			Ptil = dataAll2[dataAll2.length-1];
		}
		else if(position < 1){
			Ptil = dataAll2[0];
		}
		else {
			int NinArr = ((int)position)-1;
			double differ = dataAll2[NinArr+1] - dataAll2[NinArr];
			
			double differQ = position - (int)position;
			Ptil = (differ*differQ)+dataAll2[NinArr];
		}
		return Ptil;
	}
	public double DtilCal(int r) {
		double Dtil = 0;
		double position  = 0;
		position = r/(double)10*(N+1);
		//position = (r*(N+1))/100;
		if (position >= dataAll2.length) {
			Dtil = dataAll2[dataAll2.length-1];
			
		}
		else if (position < 1) {
			Dtil = dataAll2[0];
		}
		else {
			int NinArr = ((int)position)-1;
			double differ = dataAll2[NinArr+1] - dataAll2[NinArr];
			
			double differQ = position - (int)position;
			Dtil = (differ*differQ)+dataAll2[NinArr];
		}
		return Dtil;
	}
	public double QtilCal(int r) {
		double Qtil = 0;
		double position  = 0;
		position = r/(double)4*(N+1);
		//position = (r*(N+1))/100;
		if (position >= dataAll2.length) {
			Qtil = dataAll2[dataAll2.length-1];
			
		}
		else if (position < 1) {
			Qtil = dataAll2[0];
		}
		else {
			int NinArr = ((int)position)-1;
			double differ = dataAll2[NinArr+1] - dataAll2[NinArr];
			
			double differQ = position - (int)position;
			Qtil = (differ*differQ)+dataAll2[NinArr];
		}
		return Qtil;
	}
	public double RCal() {
		double R;
		R = dataAll2[dataAll2.length-1] - dataAll2[0];
		return R;
	}
	public double IQRCal() {
		double IQR;
		IQR = QtilCal(3) - QtilCal(1);
		return IQR;
	}
	public double S2Cal(int type) {
		double S2,avg,sum;
		avg = AvgCal();
		sum = 0;
		for(int i =0;i<dataAll2.length;i++) {
			sum += (dataAll2[i] - avg)*(dataAll2[i] - avg);
		}
		if (type == 0) {
			S2 = sum/N;
		}
		else {
			S2 = sum/(N-1);
		}
		return S2;
	}
	public double SCal(int type) {
		double S,S2;
		S2 = S2Cal(type);
		S = Math.sqrt(S2);
		return S;
	}
	public double MDADCal() {
		double MDAD,avg,temp,sum = 0;
		avg = AvgCal();
		for(int i = 0;i<dataAll2.length;i++) {
			temp = dataAll2[i] - avg;
			if (temp >= 0) {
				sum += temp;
			}
			else {
				temp = 0-temp;
				sum += temp;
			}
		}
		MDAD = sum/N;
		return MDAD;
	}
	public double QDCal() {
		double QD,IQR;
		IQR = IQRCal();
		QD = IQR/2;
		return QD;
	}
	public double CofRCal() {
		double CofR,R;
		R = RCal();
		CofR = R/(dataAll2[dataAll2.length-1] + dataAll2[0]);
		return CofR;
	}
	public double CVCal(int type) {
		double CV,S,avg;
		S = SCal(type);
		avg = AvgCal();
		CV = S/avg;
		return CV;
	}
}










