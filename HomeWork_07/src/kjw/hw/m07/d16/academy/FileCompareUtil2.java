package kjw.hw.m07.d16.academy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompareUtil2 {

	public FileCompareUtil2() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList compareFile(String fstFileName, String scdFileName) throws Exception {
	
		ArrayList<String> result = new ArrayList<String>();
		
		BufferedReader fstBr = new BufferedReader(new FileReader(fstFileName));
		BufferedReader scdBr =  new BufferedReader(new FileReader(scdFileName));
		
		String fstLine;
        String scdtLine;
        
        int num = 0;
        
        while ((fstLine = fstBr.readLine()) != null) {
            num++;
            scdtLine = scdBr.readLine();

            if (!fstLine.equals(scdtLine)) {
                result.add("LINE" + num + ":" + scdtLine);
            }
        }
		return result;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileCompareUtil util = new FileCompareUtil();
		System.out.println( util.compareFile("files/fstFile1.txt", "files/scdFile1.txt"));
		System.out.println("Successfull!!");
	}

}
