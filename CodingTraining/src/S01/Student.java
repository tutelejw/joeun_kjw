package S01;

class Student_Bean {
	String name;
	int studentId;
	String major;
	
	//default constructor
	public Student_Bean() {
	}
	public Student_Bean(String name, int studentId) {
		this.name=name;
		this.studentId=studentId;
	}
	public Student_Bean(String name, int studentId, String major) {
		this.name=name;
		this.studentId=studentId;
		this.major=major;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId=studentId;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public String toString() {
		return name +"/"+ studentId +"/"+ major;
	}
}

public class Student{
	public static void main(String[] args) {
		Student_Bean st = new Student_Bean();
		st.setName("홍길동");
		st.setStudentId(12);
		st.setMajor("컴공");
		
		Student_Bean st1 = new Student_Bean("객체1", 11, "abc");
		Student_Bean st2 = new Student_Bean("객체1", 11);
		st2.getName();
		st2.getStudentId();
		
		System.out.println(st1);
		System.out.println(st2.getName() +"/"+ st2.getStudentId());
		
//		System.out.println(st1);
//		System.out.println(st2);				
		
		
	}//end of main
} // end of class
