import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MessInDialog {
	public static JLabel Mess(int x) {
		JLabel mess = new JLabel();
		mess.setFont(new Font("Tahoma", Font.PLAIN, 20));
		if (x == 1) {
			mess.setText("จำนวนข้อมูลต้องเป็น ตัวเลขจำนวนเต็มบวกเท่านั้น");
		}
		else if (x == 2) {
			mess.setText("คุณยังไม่ได้ใส่จำนวนของข้อมูล");
			
		}
		else if (x == 3) {
			mess.setText("ใส่ค่าที่เป็นตัวเลขเท่านั้น");
		}
		else if (x == 4) {
			mess.setText("คุณยังไม่ได้ใส่ค่า");
		}
		else if (x == 5) {
			mess.setText("ข้อมูลทุกตัวใส่ค่าไว้ครบหมดแล้ว");
		}
		else if (x == 6) {
			mess.setText("ใส่ตัวเลขที่เป็นตำแหน่งของข้อมูลที่จะแก้เท่านั้น");
		}
		else if (x == 7) {
			mess.setText("แก้ไขข้อมูลสำเร็จ");
		}
		else if (x == 8) {
			mess.setText("ยังไม่มีข้อมูลใดๆ");
		}
		else if (x == 9) {
			mess.setText("ไม่สามารถลบข้อมูลได้ เนื่องจากยังไม่มีข้อมูลใดๆ");
		}
		else if (x == 10) {
			mess.setText("ไม่สามารถแก้ข้อมูลได้ เนื่องจากยังไม่มีข้อมูลใดๆ");
		}
		else if (x == 11) {
			mess.setText("ต้องมีจำนวนข้อมูลทั้งหมดตั้งแต่ 2 จำนวนขึ้นไป");
		}
		return(mess);
	}
	public static JLabel Mess(String number,int N) {
		
		JLabel mess = new JLabel();
		mess.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		mess.setText("ไม่สามารถแก้ข้อมูลตัวที่ " + number + " ได้เนื่องจากชุดข้อมูลมีจำนวนข้อมูลอยู่ " + N + " ตัว");
		
		return(mess);
	}
	
	
	public static JLabel Customtxt(String str) {
		JLabel mess = new JLabel();
		mess.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mess.setText(str);
		return(mess);
	}
}
