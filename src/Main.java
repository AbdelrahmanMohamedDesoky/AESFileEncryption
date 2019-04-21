import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) throws Exception {
		JFrame parent = new JFrame();
		int mode = 0;

	    int response = JOptionPane.showConfirmDialog(null, "Do you want to Encrypt? Choose No if you want to Decrypt", "Choose the mode",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    
	    
	    if (response == JOptionPane.NO_OPTION) {
	      mode = 2; // decrypt mode
	    } else if (response == JOptionPane.YES_OPTION) {
	      mode = 1; // encrypt mode 
	    } else if (response == JOptionPane.CLOSED_OPTION) {
	      System.exit(0);
	    }
	    
		String myFileDir = JOptionPane.showInputDialog(null, "Please Enter The File Directory", "Enter File Location", JOptionPane.WARNING_MESSAGE);

	
		BufferedReader reader = new BufferedReader(new FileReader(myFileDir));
		
		String key = JOptionPane.showInputDialog(null, "Please Enter The Key for Encryption/Decryption", "Enter The Key", JOptionPane.WARNING_MESSAGE);
		
		AESEnc AESManger = new AESEnc(key);
		
		if(mode == 1){ // encrypt
			String line;
			while((line = reader.readLine()) != null){
				writer(AESManger.encrypt(line), myFileDir);
			}
			reader.close();
			JOptionPane.showMessageDialog(parent, "Encrypting Completed file saved in same directory");
			System.exit(0);
		}
		else if (mode == 2){
			String line;
			while((line = reader.readLine()) != null){
				writer(AESManger.decrypt(line), myFileDir);
			}
			reader.close();
			JOptionPane.showMessageDialog(parent, "Decrypting Completed file saved in same directory");
			System.exit(0);
		}
		reader.close();
	}
	static void writer(String text,String myFileDir) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(myFileDir + "- done.txt",true));
		writer.write(text);
		writer.newLine();
		writer.close();
	}
}
