package S01;

class Book_Bean{
	private String title ; //제목
	private String author ; //저자
	private int price ; // 가격
	
	//default constructor
	public Book_Bean() {};
	//all constructor
	public Book_Bean(String title, String author, int price) {
		this.title=title;
		this.author=author;
		this.price=price;
	}
	
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public int getPrice() {return price;}
	
	public void setTitle(String title) {
		this.title=title;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	
	public String toString() {
		return title + author + price;
	}
}// end of class Book_Bean
public class Book {
	public static void main(String[] args) {
		Book_Bean b1 = new Book_Bean();
		b1.setTitle("자바야 놀자");
		b1.setAuthor("홍길동");
		b1.setPrice(11000);
		System.out.println("b1 toString" + b1);
		System.out.println(b1.getTitle() + b1.getAuthor() +  b1.getPrice());
		Book_Bean b2 = new Book_Bean("코딩클럽", "김유신", 5000);
		System.out.println(b2);
	}

}
