package S01;

class Student_Bean01{
	
	///Filed
	private String name;
	private int studentId;
	private String major;
	
	/// default Constructor
	public Student_Bean01() {	}
	
	/// all Constructor
	public Student_Bean01(String name, int studentId,  String major) {
		this.name = name;
		this.studentId=studentId;
		this.major=major;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return name +"/" + studentId +"/" +major;
	}
	
}

public class Student01 {
	public static void main(String[] args) {
		//Student_Bean01 sb01 = new Student_Bean01("홍길동", 11, "날아라");
		//System.out.println(sb01);
		Student_Bean01 sb02 = new Student_Bean01();
		sb02.setName("홍길동");
		sb02.setStudentId(1111);
		sb02.setMajor("호이호이");
		System.out.println(sb02.getName() + sb02.getStudentId());
		System.out.println(sb02);
	}
}
