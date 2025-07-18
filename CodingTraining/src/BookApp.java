
class Book{
	private String title; //제목
	private String author; //저자
	private int price; //가격
	
	public Book() { // default constructor
	}
	
	public Book(String title, String author, int price) { // all init field
		this.title=title;
		this.author=author;
		this.price=price;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price=price;
	}

	public void updatePrice(int newPrice) {
		this.price=newPrice;//새로운 가격으로 필드값 업데이트
		System.out.println("" + this.title + " 의 가격이 " + newPrice + "원으로 변경되었습니다.");
	}
	
	public int getDiscountPrice(double discountRate) {
		int discountAmount = (int)(this.price * discountRate);
		return this.price - discountAmount;		
	}
	
	public void printBookInfo() {
		System.out.println("제목 : " + this.title +" 저자 : " + this.author + " 가격: " + this.price);
	}
}//out of Bean

public class BookApp {	
	public static void main (String[] args) {
		Book javaBook = new Book("자바자바자바자바", "홍길동" , 30000);
		
		javaBook.printBookInfo();
		
		javaBook.updatePrice(33000);
		int discountedPrice = javaBook.getDiscountPrice(0.15);
		System.out.println("15% 할인 가격 : " + discountedPrice + " 원");
	}//out of main
}// out of class
