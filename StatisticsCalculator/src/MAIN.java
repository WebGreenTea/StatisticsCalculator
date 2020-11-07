import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MAIN {
	public static void main(String[] args) {
		MenuOne display = new MenuOne();
		display.setSize(800, 800);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setResizable(false);
		display.setLocationRelativeTo(null); //ทำให้โปรแกรมอยู่กลางจอ

	}
	
}
