package kjw.hw.m07.d17;

import java.io.FileNotFoundException;
import java.util.Vector;

class Score{
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	
	public Score(String name, int kor, int eng, int math) {
		this.name=name;
		this.kor=kor;
		this.eng=eng;
		this.math=math;
		this.sum = kor + eng + math;
	} //end of Constructor
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name=name;
		}
		public int getKor() {
			return kor;
		}
		public void setKor(int kor) {
			this.kor=kor;
		}
		public int getEng() {
			return eng;
		}
		public void setEng(int eng) {
			this.eng=eng;
		}
		public int getMath() {
			return math;
		}
		public void setMath(int math) {
			this.math=math;
		}
		public int getSum() {
			return sum;
		}
		public void setSum(int sum) {
			this.sum=sum;
		}
}//end of class Score

public class Prob {
	public Vector getScore(String filename) throws Exception {
		Vector<Score> scoresVector = new Vector<>();
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(filename));
		String str;
		while ((str = br.readLine()) != null) {
			String data[] = str.split(" ");
			if (data.length >= 4) {
				String name = data[0];
				int kor = Integer.parseInt(data[1]);
	            int eng = Integer.parseInt(data[2]);
	            int math = Integer.parseInt(data[3]);
		        scoresVector.add(new Score(name, kor, eng, math));
			}else {
				System.out.println("요효하지 않은 데이터 : " + str);
			}
	    }
		return scoresVector;
	}
	
	public static void main(String[] args) throws Exception {
		Prob p2 = new Prob();
		Vector scores = p2.getScore("files/data.txt");
		for(int i=0; i<scores.size(); i++) {
			Score score = (Score)scores.get(i);
			System.out.println(score.getName()+ ":"
								+score.getKor()+" " + score.getEng() + " " 
								+score.getMath()+" " + score.getSum() );	
		}
	}//out of main
	
}//out of class Prob
