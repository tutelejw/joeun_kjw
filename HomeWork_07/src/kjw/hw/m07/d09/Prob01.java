class Book{
	/// Field
	private String title;
	private int price;
	
	//Constructor
	public Book(){
	}
    public Book(String title, int price){
		this.title = title;
		this.price = price;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public String getTitle(){
		return title;
	}
	public int getPrice(){
		return price;
	}
} // end of class

class BookMgr{
	// 책 Book 객체 여러개를 저장 할 수 있는 책 목록 booklist 라는 배열을 멤버변수로 가져야 한다.
	// 책 목록 booklist 라는 배열변수를 초기화 하는 생성자 메서드를 작성해야 한다.
	// 책 목록을 화면에 출력하는 printBookList() 메서드가 있어야 한다.
	// 모든 책 가격의 합을 출력하는 printTotalPrice() 메서드가 있어야 한다.
	private Book[] booklist;
	private int count;
	private int sum;

	public BookMgr(){
	}

	public BookMgr(Book[] booklist){
	this.booklist = booklist;
	this.count = booklist.length;
	}

	public void printBookList(){
		for (int i = 0; i < count; i++) {
			System.out.println(booklist[i].getTitle());
	   }
	}

	public void printTotalPrice(){
		for (int j = 0; j< count ; j++ ){
			sum += booklist[j].getPrice();
//		System.out.println(booklist[j].getPrice());
		}
		System.out.println(sum);
	}

} // end of class

public class Prob01{
	public static void main(String[] args){
		
		Book[] booklist = new Book[5];
		Book book1 = new Book("Java Program", 25000);
		Book book2 = new Book("JSP Program", 15000);
		Book book3 = new Book("SQL Fundamentals", 30000);
		Book book4 = new Book("JDBC Program", 28000);
		Book book5 = new Book("EJB Program", 34000);

//		book5.setTitle();
//		book5.setPrice(34000);

		booklist[0] = book1;
		booklist[1] = book2;
		booklist[2] = book3;
		booklist[3] = book4;
		booklist[4] = book5;

		BookMgr mgr = new BookMgr(booklist);
		System.out.println("========책목록=========");
		mgr.printBookList();
		System.out.println("");

		System.out.println("=== 책 가격의 총합 ===");
		mgr.printTotalPrice();

	}//end of main
} // end of class
