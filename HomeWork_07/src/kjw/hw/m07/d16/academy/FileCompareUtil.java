package kjw.hw.m07.d16.academy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//java.io, java.util  api 연습
public class FileCompareUtil {
	public ArrayList<String> compareFile(String fstFileName, String scdFileName) throws Exception{
		//implements compareFile method.
		BufferedReader fstBr = new BufferedReader(new FileReader(fstFileName));
		BufferedReader scdBr = new BufferedReader(new FileReader(scdFileName));
		
		String fstLine = fstBr.readLine();
		String scdLine = scdBr.readLine();
		
		int lineNum = 0;
		ArrayList<String>resultList = new ArrayList<String>();
		
		while (fstLine != null) {
			lineNum++;
			if(!fstLine.equals(scdLine)) {
				resultList.add("LINE" + lineNum + ":" + scdLine);
			}
			fstLine = fstBr.readLine();
			scdLine = scdBr.readLine();
			
		}
		return resultList;
	}
	
	public static void main(String[] args) throws Exception{
		FileCompareUtil util = new FileCompareUtil();
		System.out.println(util.compareFile("./files/fstFile1.txt", "./files/scdFile1.txt"));
		System.out.println("Successful!!!");
	}//end of main
	
} // end of class
