package shop.sevices.user;

public class User {
	
	private String name;
	int age;
	protected String add;
	public String geder;
	public static final String nationality = "대한민국";
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, int age, String add, String geder) {
		super();
		this.name = name;
		this.age = age;
		this.add = add;
		this.geder = geder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getGeder() {
		return geder;
	}

	public void setGeder(String geder) {
		this.geder = geder;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", age=" + age + ", add=" + add + ", geder=" + geder + "]";
	}

}
