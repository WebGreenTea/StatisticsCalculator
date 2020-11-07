import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuOne extends JFrame implements ActionListener{
	JPanel P1,P2,P3,Pedit,P4,Pdel,PF1,Pbase;
	JTextArea databox;
	JButton menuBtn;
	//Panel1
	JLabel logo,logo2,logo3;
	JButton Start,StartBase;
	//Panel2
	JButton Bps1,Bps2,backP2;
	//Panel3
	JLabel showN;
	JButton addData,editData,delData,delAll,NextP3,Savebtn;
	JScrollPane scrollP3;
	//Paneledit
	JLabel showNPedit,labelPedit;
	JScrollPane scrollPedit;
	JTextField TboxPedit;
	JButton okPedit,backPedit;
	//Pabel4
	JScrollPane scrollP4;
	JLabel showNP4,TypedataP4;
	JButton showNewdata,showOlddata,editDataP4,Avgbtn,Medbtn,Modebtn,QtilBtn,PtilBtn,DtilBtn,Rbtn,IQRbtn,S2btn,Sbtn,MDADbtn,QDbtn,CofRbtn,CVbtn;
	JComboBox comboP4;
	//PanelDel
	JLabel showNPdel,labelPdel;
	JScrollPane scrollPdel;
	JTextField TboxPdel;
	JButton okPdel,backPdel;
	//PanelPF1
	JButton Browse,okPF1,backPF1;
	JTextField location;
	JLabel showPF1;
	//PanelBase
	JLabel lBIN,lOCT,lHEX,lDEC;
	JTextField tdec,tbin,thex,toct;
	JButton clearPbase,backBase;
	JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnA,btnB,btnC,btnD,btnE,btnF,mode1,mode2,mode3,mode4;
	
	int modeBase;
	
	
	File f;
	ChekStr Chek = new ChekStr();
	MessInDialog mess = new MessInDialog();
	Datavalue value = new Datavalue();
	BaseCal BASE = new BaseCal();
	public MenuOne() {
		super("Base Number & Statistics Calculator");
		//Panel1
		Panel1();
	}
	
	public void actionPerformed(ActionEvent event) {
		String strBase;
		if (event.getSource() == menuBtn) {
			getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel1();
        	getContentPane().revalidate();
		}
		//panel1
		else if (event.getSource() == Start) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel2();
        	getContentPane().revalidate();
        	
        }
        else if (event.getSource() == StartBase) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	PanelBase();
        	getContentPane().revalidate();
        }
        //panel2
        else if (event.getSource() == Bps2) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel3();
        	getContentPane().revalidate();
        }
        else if (event.getSource() == Bps1) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	PanelPF1();
        	getContentPane().revalidate();
        }
        else if (event.getSource() == backP2) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel1();
        	getContentPane().revalidate();
        }
        //Panel3
        else if (event.getSource() == NextP3) {
        	if(value.dataInarr() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(8), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (value.dataInarr() < 2) {
        		JOptionPane.showMessageDialog(null, mess.Mess(11), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		value.setArrangeData();
            	getContentPane().removeAll();
            	getContentPane().invalidate();
            	Panel4();
            	getContentPane().revalidate();
        	}
        }
        else if (event.getSource() == addData) {
        	String str;
        	boolean flag;
        	int N;
        	while(true){
        		N = value.dataInarr();
	        	do {
		        	str = JOptionPane.showInputDialog(null, mess.Customtxt("<html>กรุณาใส่ค่าของจำนวนที่ "+ (N+1) + "<br> กด Cancel เพื่อหยุดใส่ค่า</html>"));
		        	flag = Chek.isnumNor(str);
					if (!flag) {
						if (str.equals("")) {
							JOptionPane.showMessageDialog(null, mess.Mess(4), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
						}
						else if (str == null) {
							flag = true;
						}
						else{
							JOptionPane.showMessageDialog(null, mess.Mess(3), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
						}
					}
	        	}while(!flag);
	        	if (str == null) {
	        		break;
	        	}
	        	if (value.dataInarr() == 0) {
	        		value.dataAll = new double[1];
	        		value.dataAll[0] = Double.parseDouble(str);
	        	}
	        	else {
	        		double temp[];
	        		
	        		temp = new double[value.dataAll.length];
	        		temp = value.dataAll;
	        		value.dataAll = new double[temp.length+1];
	        		for(int i = 0;i<temp.length;i++) {
	        			value.dataAll[i] = temp[i];
	        		}
	        		value.dataAll[value.dataAll.length-1] = Double.parseDouble(str);
	        	}
	        	drawDataInBox();
	        	value.setN(value.dataAll.length);
	        	showN.setText("จำนวนข้อมูล "+value.getN());
        	}
        }
        else if (event.getSource() == delData) {
        	if (value.dataInarr() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(9), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		getContentPane().removeAll();
            	getContentPane().invalidate();
            	PanelDel();
            	getContentPane().revalidate();
        	}
        	
        }
        else if (event.getSource() == delAll) {
        	if(value.getN() > 0) {
        		int flag;
            	flag = JOptionPane.showConfirmDialog(null, mess.Customtxt("ต้องการลบข้อมูลทั้งหมด?"),"Are you sure?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            	if (flag == 0) {
            		value.dataAll = new double[0];
            		value.setN(value.dataAll.length);
            		drawDataInBox();
            		showN.setText("จำนวนข้อมูล "+value.getN());
            	}
        	}
        }
        else if (event.getSource() == editData) {
        	if (value.dataInarr() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(9), "Something was wrong!", JOptionPane.ERROR_MESSAGE);        		
        	}
        	else {
        		getContentPane().removeAll();
            	getContentPane().invalidate();
            	PanelPedit();
            	getContentPane().revalidate();
        	}
        }
        else if (event.getSource() == Savebtn) {
        	if(value.dataInarr() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(8), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (value.dataInarr() < 2) {
        		JOptionPane.showMessageDialog(null, mess.Mess(11), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		JFileChooser fileChooser = new JFileChooser();
            	//fileChooser.setDialogTitle("Specify a file to save");   
            	
            	int userSelection = fileChooser.showSaveDialog(null);
            	
            	if (userSelection == JFileChooser.APPROVE_OPTION) {//userSelection == 0
            	    File fileToSave = fileChooser.getSelectedFile();
            	    
            	    FileWriter writer;
            	    try {
            	    	writer = new FileWriter(fileToSave, false);  //True = Append to file, false = Overwrite
            	    	for(int i = 0;i<value.dataAll.length;i++) {
            	    		writer.write(Double.toString(value.dataAll[i])+"\n");
            	    	}
            	    	writer.close();
        	    		//System.out.println("Save Complete");
            	    }catch(IOException e) {
            	    	e.printStackTrace();
            	    }
            	    
            	    //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            	}
        	}
        }
        //Paneledit
        else if (event.getSource() == backPedit) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel3();
        	getContentPane().revalidate();
        }
        else if (event.getSource() == okPedit) {
        	if (Chek.isnumplus(TboxPedit.getText()) && Integer.parseInt(TboxPedit.getText()) <= value.getN()) {
        		int N;
        		String str;
        		boolean flag;
        		N = (Integer.parseInt(TboxPedit.getText())-1);
    			do {
    				str = JOptionPane.showInputDialog(null,mess.Customtxt("<html>ข้อมูลจำนวนที่ " + TboxPedit.getText() + " ปัจจุบันมีค่าเท่ากับ " + value.dataAll[N] + "<br>ต้องการเปลี่ยนค่าเป็น ?</html>"));
    				flag = Chek.isnumNor(str);
    				
    				if (!flag && str.length() == 0) {
    					JOptionPane.showMessageDialog(null, mess.Mess(4), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
    				}
    				else if (!flag) {
    					JOptionPane.showMessageDialog(null, mess.Mess(3), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
    				}
    			}while(!flag);
    			if (str != null) {
    				value.dataAll[N] = Double.parseDouble(str);
    				drawDataInBox();
        			JOptionPane.showMessageDialog(null, mess.Mess(7));	
    			}
    			TboxPedit.setText("");
        	}
        	else if (Chek.isnumplus(TboxPedit.getText())) {
        		JOptionPane.showMessageDialog(null, mess.Mess(TboxPedit.getText(),value.getN()), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (TboxPedit.getText().length() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(4), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(null, mess.Mess(6), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        }
        //Panel4
        else if (event.getSource() == editDataP4) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel3();
        	getContentPane().revalidate();
        }
        else if (event.getSource() == showNewdata) {
        	drawDataInBoxNEW();
        }
        else if (event.getSource() == showOlddata) {
        	drawDataInBox();
        }
        else if (event.getSource() == Avgbtn) {
        	double Avg;
        	Avg = value.AvgCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("ค่าเฉลี่ย  = " + Avg));
        }
        else  if (event.getSource() == Medbtn) {
        	double Med;
        	Med = value.MedCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("มัธยฐาน  = " + Med));
        }
        else  if (event.getSource() == Modebtn) {
        	String strshow = value.ModeCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt(strshow));
        	
        }
        else if (event.getSource() == QtilBtn) {
        	String str;
        	boolean flag = false;
        	int r = 0;
        	do {
        		str = JOptionPane.showInputDialog(null, mess.Customtxt("ต้องการหาควอไทลล์ที่ ? (ใส่ค่า 1-3)"));
            	flag = Chek.isnumplus2(str);
            	if(flag) {
            		if (str == null) {
            			break;
            		}
            		r = Integer.parseInt(str);
            		if (r < 1 || r >= 4) {
            			flag = false;
            		}
            	}
            	if (!flag) {
            		JOptionPane.showMessageDialog(null, mess.Customtxt("ใส่ค่าได้ตั้งแต่ 1 ถึง 3 เท่านั้น"), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
            	}
        	}while(!flag);
        	
        	if (str != null) {
        		double result = value.QtilCal(r);
            	JOptionPane.showMessageDialog(null, mess.Customtxt("ควอไทล์ที่ "+ str + " = "+ result));
        	}
        	
        }
        else if (event.getSource() == PtilBtn) {
        	String str;
        	boolean flag = false;
        	int r = 0;
        	do {
        		str = JOptionPane.showInputDialog(null, mess.Customtxt("ต้องการหาเปอร์เซ็นไทลล์ที่ ? (ใส่ค่า 1-99)"));
            	flag = Chek.isnumplus2(str);
            	if(flag) {
            		if (str == null) {
            			break;
            		}
            		r = Integer.parseInt(str);
            		if (r < 1 || r >= 100) {
            			flag = false;
            		}
            	}
            	if (!flag) {
            		JOptionPane.showMessageDialog(null, mess.Customtxt("ใส่ค่าได้ตั้งแต่ 1 ถึง 99 เท่านั้น"), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
            	}
        	}while(!flag);
        	
        	if (str != null) {
        		double result = value.PtilCal(r);
        		JOptionPane.showMessageDialog(null, mess.Customtxt("เปอร์เซ็นไทลล์ที่ "+ str + " = "+ result));
        	}
        }
        else if (event.getSource() == DtilBtn) {
        	String str;
        	boolean flag = false;
        	int r = 0;
        	do {
        		str = JOptionPane.showInputDialog(null, mess.Customtxt("ต้องการหาเดไซล์ที่ ?  (ใส่ค่า 1-9)"));
            	flag = Chek.isnumplus2(str);
            	if(flag) {
            		if (str == null) {
            			break;
            		}
            		r = Integer.parseInt(str);
            		if (r < 1 || r >= 10) {
            			flag = false;
            		}
            	}
            	if (!flag) {
            		JOptionPane.showMessageDialog(null, mess.Customtxt("ใส่ค่าได้ตั้งแต่ 1 ถึง 9 เท่านั้น"), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
            	}
        	}while(!flag);
        	
        	if (str != null) {
        		double result = value.DtilCal(r);
        		JOptionPane.showMessageDialog(null, mess.Customtxt("เดไซล์ที่ "+ str + " = "+ result));
        	}
        	
        }
        else if (event.getSource() == Rbtn) {
        	double R;
        	R = value.RCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("พิสัย = "+R));
        }
        else if (event.getSource() == IQRbtn) {
        	double IQR;
        	IQR = value.IQRCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("พิสัยควอไทล์ = "+IQR));
        }
        else if (event.getSource() == S2btn) {
        	double S2;
        	int type = comboP4.getSelectedIndex();
        	S2 = value.S2Cal(type);
        	JOptionPane.showMessageDialog(null, mess.Customtxt("ความแปรปรวน = "+S2));
        }
        else if (event.getSource() == Sbtn) {
        	double S;
        	int type = comboP4.getSelectedIndex();
        	S = value.SCal(type);
        	JOptionPane.showMessageDialog(null, mess.Customtxt("ส่วนเบี่ยงเบนมาตรฐาน = "+S));
        }
        else if (event.getSource() == MDADbtn) {
        	double MDAD;
        	MDAD = value.MDADCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("ส่วนเบี่ยงเบนเฉลี่ย = "+MDAD));
        }
        else if (event.getSource() == QDbtn) {
        	double QD;
        	QD = value.QDCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("ส่วนเบี่ยงเบนควอร์ไทล์ = "+QD));
        }
        else if (event.getSource() == CofRbtn) {
        	double CofR;
        	CofR = value.CofRCal();
        	JOptionPane.showMessageDialog(null, mess.Customtxt("สัมประสิทธิ์ของพิสัย = "+CofR));
        }
        else if (event.getSource() == CVbtn) {
        	double CV;
        	int type = comboP4.getSelectedIndex();
        	CV = value.CVCal(type);
        	JOptionPane.showMessageDialog(null, mess.Customtxt("สัมประสิทธิ์ความแปรผัน = "+CV));
        }
        //PanelDel
        else if(event.getSource() == backPdel) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel3();
        	getContentPane().revalidate();
        }
        else if(event.getSource() == okPdel) {
        	if (Chek.isnumplus(TboxPdel.getText()) && Integer.parseInt(TboxPdel.getText()) <= value.getN() && value.getN() != 0) {
        		int N;
        		String str;
        		boolean flag;
        		N = (Integer.parseInt(TboxPdel.getText())-1);
        		double temp[] = new double[value.dataAll.length-1];
        		int I = 0;
    			for(int i = 0 ;i<value.dataAll.length;i++) {
    				if (i != N) {
    					temp[I] = value.dataAll[i];
    					I++;
    				}
    			}
    			value.dataAll = new double[value.dataAll.length-1];
    			value.dataAll = temp;
    			drawDataInBox();
    			TboxPdel.setText("");
    			value.setN(value.dataAll.length);
	        	showNPdel.setText("จำนวนข้อมูล "+value.getN());
        	}
        	else if (Chek.isnumplus(TboxPdel.getText())) {
        		JOptionPane.showMessageDialog(null, mess.Mess(TboxPdel.getText(),value.getN()), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (TboxPdel.getText().length() == 0) {
        		JOptionPane.showMessageDialog(null, mess.Mess(4), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(null, mess.Mess(6), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
        	}
        }
        //PanelPF1
        else if (event.getSource() == Browse) {
        	JFileChooser chooser = new JFileChooser();
        	chooser.showOpenDialog(null);
        	boolean Fpass = true;//หากเป็นtrue ไฟล์ถูกต้อง  false ไฟล์ไม่ถูกต้อง
        	if (chooser.getSelectedFile() != null) {
        		f = chooser.getSelectedFile();
            	//System.out.println(chooser.getSelectedFile());
            	BufferedReader reader;
            	double chack;
            	try {
            		reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
            		int numline = 0;
            		String line = reader.readLine();
            		while (line != null) {
            			if (Chek.isnumNor(line) == true) {
            				numline ++;
            				//System.out.println(line+" " + numline);
            				line = reader.readLine();
            			}
            			else if (line.length() == 0) {
            				line = reader.readLine();
            			}
            			else {
            				JOptionPane.showMessageDialog(null, mess.Customtxt("File Error"), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
            				Fpass = false;
            				break;
            			}
            		}
            		reader.close();
            		//ถ้าข้อมูลมีน้อยกว่าสองตัว
            		if (numline < 2) {
            			JOptionPane.showMessageDialog(null, mess.Customtxt("ไม่สามารถเปิดไฟล์ที่มีจำนวนข้อมูลน้อยกว่า 2 ได้"), "Something was wrong!", JOptionPane.ERROR_MESSAGE);
            			Fpass = false;
            		}
            		
            		if (Fpass == true) {
            			String FLocation = f.getAbsolutePath();
                    	location.setText(FLocation);
                    	value.setN(numline);
            		}
            		else {
            			location.setText("");
            		}
            		
            	}catch(IOException e){
            		//e.printStackTrace();
            		JOptionPane.showMessageDialog(null, mess.Customtxt("File not found."),"Something was wrong!", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	
        }
        else  if (event.getSource() == okPF1) {
        	if(location.getText().length() != 0) {
        		
        		value.setArray(value.getN());
        		BufferedReader reader;
        		try {
        			reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
        			int element = 0;
        			String line = reader.readLine();
        			while (line != null) {
        				if(line.length() != 0) {
        					value.dataAll[element] = Double.parseDouble(line);
            				element++;
            				line = reader.readLine();
        				}
        				else {
        					line = reader.readLine();
        				}
        			}
        			reader.close();
        			
        		}catch(IOException e){
            		e.printStackTrace();
            	}
        		value.setArrangeData();
        		databox = new JTextArea();
        		databox.setBounds(50, 50, 700, 300);
        		databox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        		databox.setEditable(false);
        		drawDataInBox();
        		getContentPane().removeAll();
            	getContentPane().invalidate();
            	Panel4();
            	getContentPane().revalidate();
        	}
        }
        else if (event.getSource() == backPF1) {
        	getContentPane().removeAll();
        	getContentPane().invalidate();
        	Panel2();
        	getContentPane().revalidate();
        }
        //PanelBase
        else if (event.getSource() == mode1) {
        	setbase2();
        }
        else if (event.getSource() == mode2) {
        	setbase8();
        }
        else if (event.getSource() == mode3) {
        	setbase10();
        }
        else if (event.getSource() == mode4) {
        	setbase16();
        }
        else if (event.getSource() == btn1) {
        	if (modeBase == 1) {
        		strBase = tbin.getText();
        		strBase += 1 ;
        		tbin.setText(strBase);
        		Bmode2sol();
        	}
        	else if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 1;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 1;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 1;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        	
        }
        else if (event.getSource() == btn2) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 2;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 2;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 2;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn3) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 3;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 3;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 3;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn4) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 4;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 4;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 4;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn5) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 5;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 5;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 5;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn6) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 6;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 6;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 6;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn7) {
        	if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 7;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 7;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 7;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn8){
        	if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 8;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 8;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn9){
        	if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 9;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 9;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btn0) {
        	if (modeBase == 1) {
        		strBase = tbin.getText();
        		strBase += 0 ;
        		tbin.setText(strBase);
        		Bmode2sol();
        	}
        	else if (modeBase == 2) {
        		strBase = toct.getText();
        		strBase += 0;
        		toct.setText(strBase);
        		Bmode8sol();
        	}
        	else if (modeBase == 3) {
        		strBase = tdec.getText();
        		strBase += 0;
        		tdec.setText(strBase);
        		Bmode10sol();
        	}
        	else if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += 0;
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnA) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "A";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnB) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "B";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnC) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "C";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnD) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "D";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnE) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "E";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == btnF) {
        	if (modeBase == 4) {
        		strBase = thex.getText();
        		strBase += "F";
        		thex.setText(strBase);
        		Bmode16sol();
        	}
        }
        else if (event.getSource() == backBase) {
        	if(modeBase == 1) {
        		String str = tbin.getText();
        		if (str.length() == 1) {
        			tbin.setText("");
        			Clear3Base();
        		}
        		else if(str.length() > 1) {
	        		tbin.setText(str.substring(0,str.length()-1));
	        		Bmode2sol();
        		}
        	}
        	else if(modeBase == 2) {
        		String str = toct.getText();
        		if (str.length() == 1) {
        			toct.setText("");
        			Clear3Base();
        		}
        		else if(str.length() > 1) {
        			toct.setText(str.substring(0,str.length()-1));
            		Bmode8sol();
        		}
        		
        	}
        	else if(modeBase == 3) {
        		String str = tdec.getText();
        		if (str.length() == 1) {
        			tdec.setText("");
        			Clear3Base();
        		}
        		else if(str.length() > 1) {
        			tdec.setText(str.substring(0,str.length()-1));
            		Bmode10sol();
        		}
        		
        	}
        	else if(modeBase == 4) {
        		String str = thex.getText();
        		if (str.length() == 1) {
        			thex.setText("");
        			Clear3Base();
        		}
        		else if(str.length() > 1) {
        			thex.setText(str.substring(0,str.length()-1));
            		Bmode16sol();
        		}
        	}
        	
        }
        else if (event.getSource() == clearPbase) {
        	Clear3Base();
        }
    }
	
	public void Panel1() {
		P1 = new JPanel(null);
		//add(P1);
		getContentPane().add(P1);
		logo = new JLabel("Base Number");
		logo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Dimension size = logo.getPreferredSize();
		logo.setBounds(400-(size.width/2), 100, size.width, size.height);
		P1.add(logo);
		
		logo2 = new JLabel("&");
		logo2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Dimension size2 = logo2.getPreferredSize();
		logo2.setBounds(400-(size2.width/2), 150, size2.width, size2.height);
		P1.add(logo2);
		
		logo3 = new JLabel("Statistics Calculator ");
		logo3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Dimension size3 = logo3.getPreferredSize();
		logo3.setBounds(400-(size3.width/2), 200, size3.width, size3.height);
		P1.add(logo3);

		Start = new JButton("หาค่าทางสถิติ");
		Start.setFont(new Font("Tahoma", Font.BOLD, 18));
		Start.addActionListener(this);
		Start.setBounds(300, 400, 200, 50);
		P1.add(Start);
		
		StartBase = new JButton("หาเลขฐาน");
		StartBase.setFont(new Font("Tahoma", Font.BOLD, 18));
		StartBase.addActionListener(this);
		StartBase.setBounds(300, 500, 200, 50);
		P1.add(StartBase);
	}
	public void Panel2() {
		P2 = new JPanel(null);
		getContentPane().add(P2);
		Bps1 = new JButton("เปิดไฟล์ข้อมูล");
		Bps1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Bps1.addActionListener(this);
		Bps1.setBounds(400-150, 200, 300, 100);
		P2.add(Bps1);
		
		Bps2 = new JButton("กรอกข้อมูลเอง");
		Bps2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		Bps2.addActionListener(this);
		Bps2.setBounds(400-150, 400, 300, 100);
		P2.add(Bps2);
		
		backP2 = new JButton("ย้อนกลับ");
		backP2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backP2.addActionListener(this);
		backP2.setBounds(100, 650, 110, 40);
		P2.add(backP2);
		
	}
	public void Panel3() {
		P3 = new JPanel(null);
		getContentPane().add(P3);
		showN = new JLabel("จำนวนข้อมูล "+ value.getN());
		showN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Dimension size = showN.getPreferredSize();
		showN.setBounds(100, 10, 200,size.height );
		P3.add(showN);
		
		databox = new JTextArea();
		databox.setBounds(50, 50, 700, 300);
		databox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		databox.setEditable(false);
		if (value.dataInarr() != 0) {
			drawDataInBox();
		}
		scrollP3 = new JScrollPane(databox);
		scrollP3.setBounds(50, 50, 700, 300);
		P3.add(scrollP3);
		
		addData = new JButton("เพิ่มข้อมูลลงในชุดข้อมูล");
		addData.setFont(new Font("Tahoma", Font.BOLD, 16));
		addData.addActionListener(this);
		addData.setBounds(100, 360, 280, 50);
		//addData.setForeground(new Color(11,100,0));
		addData.setBackground(new Color(204,255,153));
		addData.setOpaque(true);
		P3.add(addData);
		
		delData = new JButton("ลบข้อมูลออกจากชุดข้อมูล");
		delData.setFont(new Font("Tahoma", Font.BOLD, 16));
		delData.addActionListener(this);
		delData.setBounds(420, 360, 280, 50);
		//delData.setForeground(Color.RED);
		delData.setBackground(new Color(255,153,153));
		P3.add(delData);
		
		editData = new JButton("แก้ไขข้อมูล");
		editData.setFont(new Font("Tahoma", Font.BOLD, 16));
		editData.addActionListener(this);
		editData.setBounds(100, 420, 280, 50);
		//editData.setForeground(new Color(0,111,111));
		editData.setBackground(new Color(153,204,255));
		P3.add(editData);
		
		delAll = new JButton("ลบข้อมูลทั้งหมด");
		delAll.setFont(new Font("Tahoma", Font.BOLD, 16));
		delAll.addActionListener(this);
		delAll.setBounds(420, 420, 280, 50);
		//delAll.setForeground(Color.RED);
		delAll.setBackground(new Color(255,153,153));
		P3.add(delAll);
		
		NextP3 = new JButton("ตกลง");
		NextP3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NextP3.addActionListener(this);
		NextP3.setBounds(590, 650, 110, 40);
		P3.add(NextP3);
		
		Savebtn = new JButton("Save As...");
		Savebtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Savebtn.addActionListener(this);
		Savebtn.setBounds(100, 650, 200, 40);
		P3.add(Savebtn);
		
		menuBtn = new JButton("กลับ เมนูหลัก");
		menuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBtn.addActionListener(this);
		menuBtn.setBounds(325, 730, 150, 35);
		menuBtn.setForeground(Color.RED);
		P3.add(menuBtn);
	}
	
	public void PanelPedit() {
		Pedit = new JPanel(null);
		getContentPane().add(Pedit);
		
		showNPedit = new JLabel("จำนวนข้อมูล "+value.getN());
		showNPedit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Dimension size = showNPedit.getPreferredSize();
		showNPedit.setBounds(100, 10, size.width,size.height );
		Pedit.add(showNPedit);
		
		scrollPedit = new JScrollPane(databox);
		scrollPedit.setBounds(50, 50, 700, 300);
		Pedit.add(scrollPedit);
		
		labelPedit = new JLabel("แก้ข้อมูลจำนวนที่");
		labelPedit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Dimension size2 = labelPedit.getPreferredSize();
		labelPedit.setBounds(390-size2.width, 400, size2.width, size2.height);
		Pedit.add(labelPedit);
		
		TboxPedit = new JTextField();
		TboxPedit.setBounds(410, 400, 150, 40);
		TboxPedit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Pedit.add(TboxPedit);
		
		okPedit = new JButton("ตกลง");
		okPedit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		okPedit.addActionListener(this);
		okPedit.setBounds(300, 460, 200, 50);
		Pedit.add(okPedit);
		
		backPedit = new JButton("ย้อนกลับ");
		backPedit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backPedit.addActionListener(this);
		backPedit.setBounds(100, 650, 110, 40);
		Pedit.add(backPedit);
		
	}
	public void Panel4() {
		P4 = new JPanel(null);
		getContentPane().add(P4);
		
		showNP4 = new JLabel("จำนวนข้อมูล "+value.getN());
		showNP4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Dimension size = showNP4.getPreferredSize();
		showNP4.setBounds(100, 10, size.width,size.height );
		P4.add(showNP4);
		
		scrollP4 = new JScrollPane(databox);
		scrollP4.setBounds(50, 50, 700, 300);
		P4.add(scrollP4);
		
		showNewdata = new JButton("แสดงข้อมูลทีจัดเรียงแล้ว");
		showNewdata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		showNewdata.addActionListener(this);
		showNewdata.setBounds(50, 360, 350, 40);
		P4.add(showNewdata);
		
		showOlddata = new JButton("แสดงข้อมูลแบบเดิม(ที่ยังไม่จัดเรียง)");
		showOlddata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		showOlddata.addActionListener(this);
		showOlddata.setBounds(400, 360, 350, 40);
		P4.add(showOlddata);
		
		editDataP4 = new JButton("แก้ไขข้อมูล หรือ เพิ่มข้อมูล");
		editDataP4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editDataP4.addActionListener(this);
		editDataP4.setBounds(50, 400, 350, 40);
		P4.add(editDataP4);
		
		TypedataP4 = new JLabel("ประเภทข้อมูล");
		TypedataP4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Dimension size2 = TypedataP4.getPreferredSize();
		TypedataP4.setBounds(500, 15, size2.width, size2.height);
		P4.add(TypedataP4);
		
		comboP4 = new JComboBox();
		comboP4.addItem("ประชากร");
		comboP4.addItem("กลุ่มตัวอย่าง");
		comboP4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboP4.setBounds(size2.width+510, 15, 120, 30);
		if (value.gettype()) {
			comboP4.setSelectedIndex(0);
		}
		else {
			comboP4.setSelectedIndex(1);			
		}
		P4.add(comboP4);
		
		Avgbtn = new JButton("ค่าเฉลี่ย");
		Avgbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Avgbtn.addActionListener(this);
		Avgbtn.setBounds(85, 460, 210, 40);
		P4.add(Avgbtn);
		
		Medbtn = new JButton("มัธยฐาน");
		Medbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Medbtn.addActionListener(this);
		Medbtn.setBounds(295, 460, 210, 40);
		P4.add(Medbtn);
		
		Modebtn = new JButton("ฐานนิยม");
		Modebtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Modebtn.addActionListener(this);
		Modebtn.setBounds(505, 460, 210, 40);
		P4.add(Modebtn);
		
		PtilBtn = new JButton("เปอร์เซ็นไทล์");
		PtilBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PtilBtn.addActionListener(this);
		PtilBtn.setBounds(85, 510, 210, 40);
		P4.add(PtilBtn);
		
		DtilBtn = new JButton("เดไซล์");
		DtilBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DtilBtn.addActionListener(this);
		DtilBtn.setBounds(295, 510, 210, 40);
		P4.add(DtilBtn);
		
		QtilBtn = new JButton("ควอร์ไทล์");
		QtilBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		QtilBtn.addActionListener(this);
		QtilBtn.setBounds(505, 510, 210, 40);
		P4.add(QtilBtn);
		
		Rbtn = new JButton("พิสัย");
		Rbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Rbtn.addActionListener(this);
		Rbtn.setBounds(85, 560, 210, 40);
		P4.add(Rbtn);
		
		IQRbtn = new JButton("พิสัยควอร์ไทล์");
		IQRbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		IQRbtn.addActionListener(this);
		IQRbtn.setBounds(295, 560, 210, 40);
		P4.add(IQRbtn);
		
		S2btn = new JButton("ความแปรปรวน");
		S2btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		S2btn.addActionListener(this);
		S2btn.setBounds(505, 560, 210, 40);
		P4.add(S2btn);
		
		Sbtn = new JButton("ส่วนเบี่ยงเบนมาตรฐาน");
		Sbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Sbtn.addActionListener(this);
		Sbtn.setBounds(85, 610, 210, 40);
		P4.add(Sbtn);
		
		MDADbtn = new JButton("ส่วนเบี่ยงเบนเฉลี่ย");
		MDADbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MDADbtn.addActionListener(this);
		MDADbtn.setBounds(295, 610, 210, 40);
		P4.add(MDADbtn);
		
		QDbtn = new JButton("ส่วนเบี่ยงเบนควอร์ไทล์");
		QDbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		QDbtn.addActionListener(this);
		QDbtn.setBounds(505, 610, 210, 40);
		P4.add(QDbtn);
		
		CofRbtn = new JButton("สัมประสิทธิ์ของพิสัย");
		CofRbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CofRbtn.addActionListener(this);
		CofRbtn.setBounds(150, 660, 250, 40);
		P4.add(CofRbtn);
		
		CVbtn = new JButton("สัมประสิทธิ์ความแปรผัน");
		CVbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CVbtn.addActionListener(this);
		CVbtn.setBounds(400, 660, 250, 40);
		P4.add(CVbtn);
		
		menuBtn = new JButton("กลับ เมนูหลัก");
		menuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBtn.addActionListener(this);
		menuBtn.setBounds(325, 730, 150, 35);
		menuBtn.setForeground(Color.RED);
		P4.add(menuBtn);
	}
	
	public void PanelDel() {
		Pdel = new JPanel(null);
		getContentPane().add(Pdel);
		
		showNPdel = new JLabel("จำนวนข้อมูล "+value.getN());
		showNPdel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Dimension size = showNPdel.getPreferredSize();
		showNPdel.setBounds(100, 10, size.width,size.height );
		Pdel.add(showNPdel);
		
		scrollPdel = new JScrollPane(databox);
		scrollPdel.setBounds(50, 50, 700, 300);
		Pdel.add(scrollPdel);
		
		labelPdel = new JLabel("ลบข้อมูลจำนวนที่");
		labelPdel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Dimension size2 = labelPdel.getPreferredSize();
		labelPdel.setBounds(390-size2.width, 400, size2.width, size2.height);
		Pdel.add(labelPdel);
		
		TboxPdel = new JTextField();
		TboxPdel.setBounds(410, 400, 150, 40);
		TboxPdel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Pdel.add(TboxPdel);
		
		okPdel = new JButton("ตกลง");
		okPdel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		okPdel.addActionListener(this);
		okPdel.setBounds(300, 460, 200, 50);
		Pdel.add(okPdel);
		
		backPdel = new JButton("ย้อนกลับ");
		backPdel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backPdel.addActionListener(this);
		backPdel.setBounds(100, 650, 110, 40);
		Pdel.add(backPdel);
	}
	public void PanelPF1() {
		PF1 = new JPanel(null);
		getContentPane().add(PF1);
		
		Browse = new JButton("BROWSE");
		Browse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Browse.addActionListener(this);
		Browse.setBounds(600, 350, 120, 40);
		PF1.add(Browse);
		
		location = new JTextField();
		location.setBounds(30, 350, 550, 40);
		location.setEditable(false);
		location.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PF1.add(location);
		
		okPF1 = new JButton("ตกลง");
		okPF1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		okPF1.addActionListener(this);
		okPF1.setBounds(300, 460, 200, 50);
		PF1.add(okPF1);
		
		backPF1 = new JButton("ย้อนกลับ");
		backPF1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backPF1.addActionListener(this);
		backPF1.setBounds(100, 650, 110, 40);
		PF1.add(backPF1);
		
		showPF1 = new JLabel("เปิดไฟล์ของคุณ");
		showPF1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Dimension size1 = showPF1.getPreferredSize();
		showPF1.setBounds(400-(size1.width/2), 220, size1.width, size1.height);
		PF1.add(showPF1);
		
		menuBtn = new JButton("กลับ เมนูหลัก");
		menuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBtn.addActionListener(this);
		menuBtn.setBounds(325, 730, 150, 35);
		menuBtn.setForeground(Color.RED);
		PF1.add(menuBtn);
	}
	
	public void PanelBase() {
		Pbase = new JPanel(null);
		getContentPane().add(Pbase);
		
		
		btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn1.addActionListener(this);
		btn1.setBounds(255, 350, 50, 50);
		Pbase.add(btn1);
		
		btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn2.addActionListener(this);
		btn2.setBounds(315, 350, 50, 50);
		Pbase.add(btn2);
		
		btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn3.addActionListener(this);
		btn3.setBounds(375, 350, 50, 50);
		Pbase.add(btn3);
		
		btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn4.addActionListener(this);
		btn4.setBounds(255, 410, 50, 50);
		Pbase.add(btn4);
		
		btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn5.addActionListener(this);
		btn5.setBounds(315, 410, 50, 50);
		Pbase.add(btn5);
		
		btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn6.addActionListener(this);
		btn6.setBounds(375, 410, 50, 50);
		Pbase.add(btn6);
		
		btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn7.addActionListener(this);
		btn7.setBounds(255, 470, 50, 50);
		Pbase.add(btn7);
		
		btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn8.addActionListener(this);
		btn8.setBounds(315, 470, 50, 50);
		Pbase.add(btn8);
		
		btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn9.addActionListener(this);
		btn9.setBounds(375, 470, 50, 50);
		Pbase.add(btn9);
		
		btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn0.addActionListener(this);
		btn0.setBounds(255, 530, 50, 50);
		Pbase.add(btn0);
		
		btnA = new JButton("A");
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnA.addActionListener(this);
		btnA.setBounds(435, 350, 50, 50);
		Pbase.add(btnA);

		btnB = new JButton("B");
		btnB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnB.addActionListener(this);
		btnB.setBounds(495, 350, 50, 50);
		Pbase.add(btnB);
		
		btnC = new JButton("C");
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnC.addActionListener(this);
		btnC.setBounds(435, 410, 50, 50);
		Pbase.add(btnC);
		
		btnD = new JButton("D");
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnD.addActionListener(this);
		btnD.setBounds(495, 410, 50, 50);
		Pbase.add(btnD);
		
		btnE = new JButton("E");
		btnE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnE.addActionListener(this);
		btnE.setBounds(435, 470, 50, 50);
		Pbase.add(btnE);
		
		btnF = new JButton("F");
		btnF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnF.addActionListener(this);
		btnF.setBounds(495, 470, 50, 50);
		Pbase.add(btnF);
		
		mode1 = new JButton("แปลงจากฐาน 2");
		mode1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		mode1.addActionListener(this);
		mode1.setBounds(50, 5, 160, 40);
		Pbase.add(mode1);
		
		mode2 = new JButton("แปลงจากฐาน 8");
		mode2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		mode2.addActionListener(this);
		mode2.setBounds(210, 5, 160, 40);
		Pbase.add(mode2);
		
		mode3 = new JButton("แปลงจากฐาน 10");
		mode3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		mode3.addActionListener(this);
		mode3.setBounds(370, 5, 160, 40);
		//mode3.setBackground(new Color(255,179,0));
		Pbase.add(mode3);
		
		mode4 = new JButton("แปลงจากฐาน 16");
		mode4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		mode4.addActionListener(this);
		mode4.setBounds(530, 5, 160, 40);
		Pbase.add(mode4);
		
		tbin = new JTextField();
		tbin.setBounds(410, 100, 250, 40);
		tbin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tbin.setEditable(false);
		Pbase.add(tbin);
		
		toct = new JTextField();
		toct.setBounds(410, 160, 250, 40);
		toct.setFont(new Font("Tahoma", Font.PLAIN, 25));
		toct.setEditable(false);
		Pbase.add(toct);
		
		tdec = new JTextField();
		tdec.setBounds(410, 220, 250, 40);
		tdec.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tdec.setEditable(false);
		Pbase.add(tdec);
		
		thex = new JTextField();
		thex.setBounds(410, 280, 250, 40);
		thex.setFont(new Font("Tahoma", Font.PLAIN, 25));
		thex.setEditable(false);
		Pbase.add(thex);
		
		lBIN = new JLabel("BIN ( ฐาน 2 ) : ");
		lBIN.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Dimension size2 = lBIN.getPreferredSize();
		lBIN.setBounds(180, 100, 620, size2.height);
		Pbase.add(lBIN);
		
		lOCT = new JLabel("OCT ( ฐาน 8 ) : ");
		lOCT.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Dimension size3 = lOCT.getPreferredSize();
		lOCT.setBounds(180, 160, 620, size3.height);
		Pbase.add(lOCT);
		
		lDEC = new JLabel("DEC ( ฐาน 10 ) : ");
		lDEC.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Dimension size5 = lDEC.getPreferredSize();
		lDEC.setBounds(180, 220, 620, size5.height);
		Pbase.add(lDEC);
		
		lHEX = new JLabel("HEX ( ฐาน 16 ) : ");
		lHEX.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Dimension size4 = lHEX.getPreferredSize();
		lHEX.setBounds(180, 280, 620, size4.height);
		Pbase.add(lHEX);
		
		clearPbase = new JButton("Clear");
		clearPbase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clearPbase.addActionListener(this);
		clearPbase.setBounds(315, 530, 110, 50);
		Pbase.add(clearPbase);
		
		backBase = new JButton();
		backBase.setIcon(new ImageIcon(this.getClass().getResource("/img/back.png")));
		backBase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBase.addActionListener(this);
		backBase.setBounds(435, 530, 110, 50);
		Pbase.add(backBase);
		
		menuBtn = new JButton("กลับ เมนูหลัก");
		menuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBtn.addActionListener(this);
		menuBtn.setBounds(325, 730, 150, 35);
		menuBtn.setForeground(Color.RED);
		Pbase.add(menuBtn);
		
		setbase10();
	}
	
	public void setbase2(){
		mode1.setBackground(new Color(255,179,0));
		mode2.setBackground(new Color(224,224,224));
		mode3.setBackground(new Color(224,224,224));
		mode4.setBackground(new Color(224,224,224));
		btn1.setBackground(new Color(204,255,153));
		btn2.setBackground(new Color(255,51,51));
		btn3.setBackground(new Color(255,51,51));
		btn4.setBackground(new Color(255,51,51));
		btn5.setBackground(new Color(255,51,51));
		btn6.setBackground(new Color(255,51,51));
		btn7.setBackground(new Color(255,51,51));
		btn8.setBackground(new Color(255,51,51));
		btn9.setBackground(new Color(255,51,51));
		btn0.setBackground(new Color(204,255,153));
		btnA.setBackground(new Color(255,51,51));
		btnB.setBackground(new Color(255,51,51));
		btnC.setBackground(new Color(255,51,51));
		btnD.setBackground(new Color(255,51,51));
		btnE.setBackground(new Color(255,51,51));
		btnF.setBackground(new Color(255,51,51));
		lBIN.setForeground(Color.BLUE);
		lOCT.setForeground(Color.black);
		lDEC.setForeground(Color.black);
		lHEX.setForeground(Color.black);
		modeBase = 1;
	}
	
	public void setbase8(){
		mode1.setBackground(new Color(224,224,224));
		mode2.setBackground(new Color(255,179,0));
		mode3.setBackground(new Color(224,224,224));
		mode4.setBackground(new Color(224,224,224));
		btn1.setBackground(new Color(204,255,153));
		btn2.setBackground(new Color(204,255,153));
		btn3.setBackground(new Color(204,255,153));
		btn4.setBackground(new Color(204,255,153));
		btn5.setBackground(new Color(204,255,153));
		btn6.setBackground(new Color(204,255,153));
		btn7.setBackground(new Color(204,255,153));
		btn8.setBackground(new Color(255,51,51));
		btn9.setBackground(new Color(255,51,51));
		btn0.setBackground(new Color(204,255,153));
		btnA.setBackground(new Color(255,51,51));
		btnB.setBackground(new Color(255,51,51));
		btnC.setBackground(new Color(255,51,51));
		btnD.setBackground(new Color(255,51,51));
		btnE.setBackground(new Color(255,51,51));
		btnF.setBackground(new Color(255,51,51));
		lBIN.setForeground(Color.black);
		lOCT.setForeground(Color.BLUE);
		lDEC.setForeground(Color.black);
		lHEX.setForeground(Color.black);
		modeBase = 2;
	}
	
	public void setbase10(){
		mode1.setBackground(new Color(224,224,224));
		mode2.setBackground(new Color(224,224,224));
		mode3.setBackground(new Color(255,179,0));
		mode4.setBackground(new Color(224,224,224));
		btn1.setBackground(new Color(204,255,153));
		btn2.setBackground(new Color(204,255,153));
		btn3.setBackground(new Color(204,255,153));
		btn4.setBackground(new Color(204,255,153));
		btn5.setBackground(new Color(204,255,153));
		btn6.setBackground(new Color(204,255,153));
		btn7.setBackground(new Color(204,255,153));
		btn8.setBackground(new Color(204,255,153));
		btn9.setBackground(new Color(204,255,153));
		btn0.setBackground(new Color(204,255,153));
		btnA.setBackground(new Color(255,51,51));
		btnB.setBackground(new Color(255,51,51));
		btnC.setBackground(new Color(255,51,51));
		btnD.setBackground(new Color(255,51,51));
		btnE.setBackground(new Color(255,51,51));
		btnF.setBackground(new Color(255,51,51));
		lBIN.setForeground(Color.black);
		lOCT.setForeground(Color.black);
		lDEC.setForeground(Color.BLUE);
		lHEX.setForeground(Color.black);
		modeBase = 3;
	}
	
	public void setbase16(){
		mode1.setBackground(new Color(224,224,224));
		mode2.setBackground(new Color(224,224,224));
		mode3.setBackground(new Color(224,224,224));
		mode4.setBackground(new Color(255,179,0));
		btn1.setBackground(new Color(204,255,153));
		btn2.setBackground(new Color(204,255,153));
		btn3.setBackground(new Color(204,255,153));
		btn4.setBackground(new Color(204,255,153));
		btn5.setBackground(new Color(204,255,153));
		btn6.setBackground(new Color(204,255,153));
		btn7.setBackground(new Color(204,255,153));
		btn8.setBackground(new Color(204,255,153));
		btn9.setBackground(new Color(204,255,153));
		btn0.setBackground(new Color(204,255,153));
		btnA.setBackground(new Color(204,255,153));
		btnB.setBackground(new Color(204,255,153));
		btnC.setBackground(new Color(204,255,153));
		btnD.setBackground(new Color(204,255,153));
		btnE.setBackground(new Color(204,255,153));
		btnF.setBackground(new Color(204,255,153));
		lBIN.setForeground(Color.black);
		lOCT.setForeground(Color.black);
		lDEC.setForeground(Color.black);
		lHEX.setForeground(Color.BLUE);
		modeBase = 4;
	}
	
	public void Bmode2sol() {
		String DEC,OCT,HEX;
		long dec;
		//cal base10
		DEC = BASE.BINtoDEC(tbin.getText());
		tdec.setText(DEC);
		
		dec =Long.parseLong(DEC);
		//cal base8
		OCT = BASE.OCT(dec);
		toct.setText(OCT);
		
		//cal base16
		HEX = BASE.HEX(dec);
		thex.setText(HEX);
		
	}
	public void Bmode8sol() {
		String DEC,BIN,HEX;
		long dec;
		//cal base10
		DEC = BASE.OCTtoDEC(toct.getText());
		tdec.setText(DEC);
		
		dec =Long.parseLong(DEC);
		//cal base2
		BIN = BASE.BIN(dec);
		tbin.setText(BIN);
		
		//cal base16
		HEX = BASE.HEX(dec);
		thex.setText(HEX);
		
	}
	public void Bmode10sol() {
		String OCT,BIN,HEX;
		long dec = Long.parseLong(tdec.getText());
		//cal base8
		OCT = BASE.OCT(dec);
		toct.setText(OCT);
		
		//cal base2
		BIN = BASE.BIN(dec);
		tbin.setText(BIN);
		
		//cal base16
		HEX = BASE.HEX(dec);
		thex.setText(HEX);
		
	}
	public void Bmode16sol() {
		String DEC,BIN,OCT;
		long dec;
		//cal base10
		DEC = BASE.HEXtoDEC(thex.getText());
		tdec.setText(DEC);
		
		dec =Long.parseLong(DEC);
		//cal base2
		BIN = BASE.BIN(dec);
		tbin.setText(BIN);
		
		//cal base8
		OCT = BASE.OCT(dec);
		toct.setText(OCT);
	}
	
	

	public void drawDataInBox() {
		String txtinbox = "";
		txtinbox = "";
		for(int I = 0;I<value.dataAll.length;I++) {
			txtinbox += ("("+(I+1)+") "+value.dataAll[I] + "\t");
			if ((I + 1) %10 == 0) {
				txtinbox += "\n";
			}
		}
		databox.setText(txtinbox);
		
		
	}
	public void drawDataInBoxNEW() {
		String txtinbox = "";
		txtinbox = "";
		for(int I = 0;I<value.dataAll2.length;I++) {
			txtinbox += ("("+(I+1)+") "+value.dataAll2[I] + "\t");
			if ((I + 1) %10 == 0) {
				txtinbox += "\n";
			}
		}
		databox.setText(txtinbox);
		
	}
	
	public void Clear3Base() {
		tbin.setText("");
		toct.setText("");
		tdec.setText("");
		thex.setText("");
	}
}
