package kjw.hw.m07.d17;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Vector;

//java.io api 연습
public class FileCalculator {
	public void addCalculateResult(String inputFileName) throws Exception{
		FileReader fr = new FileReader(inputFileName);
		BufferedReader br = new BufferedReader(fr);
		String temp = null;
		List<Integer> values = new Vector();
		while ((temp=br.readLine()) != null) {
			if(temp.length()==0) {
				values.add(Integer.parseInt(temp));
			}
		}
			br.close();
			int sum = 0;
			int multiple=1;
			for(int tempVal : values) {
				sum+=tempVal;
				multiple*=tempVal;
			}
			values.add(sum);
			values.add(multiple);
			BufferedWriter bw = new BufferedWriter(new FileWriter(inputFileName));
			
			for(int tempVal : values) {
				bw.write(tempVal+"");
				bw.newLine();
			}
			bw.flush();
			bw.close();
	}
	
	public static void main(String[] args) throws Exception{
		FileCalculator fc = new FileCalculator();
		fc.addCalculateResult("files/test.dat");
	}//end of main
} // end of class
