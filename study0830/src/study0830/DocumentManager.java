package study0830;

interface Printable {
	public void print();
}

interface Savable{
	public void save();
}

class Report implements Printable, Savable{
	private String content;
	
	//default construct
	public Report(String content){
		this.content = content;
	}
	public void print() {
		System.out.println("출력");
	}
	
	public void save() {
		System.out.println("저장");
	}
}

public class DocumentManager {
	public static void main(String[] args) {
		Report monthlyReport = new Report("월간업무보고서");
		processPrint(monthlyReport);
		
		processSave(monthlyReport);
	}
	public static void processPrint(Printable document) {
		document.print();
	}
	
	public static void processSave(Savable document) {
		document.save();
	}
}
