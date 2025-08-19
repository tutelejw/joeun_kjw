package S01;

import java.util.Set;

class Student_Bean02{
	private String name;
	private int studentId;
	private String major;
	
	///default constructor
	public Student_Bean02() {
	}
	
	///all constructor
//	public Student_Bean02(String name, int studentId, String major) {
//		this.name=name;
//		this.studentId=studentId;
//		this.major=major;
//	}
	
	public String getName() {return name;}
	public int getStudentId() {return studentId;}
	public String getMajor() {return major;}
	
	public void setName(String name) {
		this.name=name;
	}
	public void setStudentId(int studentId) {
		this.studentId=studentId;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	
	public String toString() {
		return name + studentId + major;
	}

}
	
	
public class Student02 {
	public static void main(String[] args) {
		Student_Bean02 sb02 = new Student_Bean02();
		sb02.setName("호호호");
		sb02.setStudentId(1212);
		System.out.println(sb02);
	}
}
