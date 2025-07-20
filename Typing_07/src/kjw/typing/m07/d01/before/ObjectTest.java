public class ObjectTest{
	int i;
	static int j ;

	static{
		System.out.println("A>:j="+j);
		j=1;
		System.out.println("B>:j="+j);
	}

	public ObjectTest(){
	System.out.println("ObjectTest default Constructor :: C> : i = " + i);
	}

	public void printInstanceInt(){
		System.out.println("D> : i = " + i);
	}

	public static void printClassInt(){
	System.out.println("E>:j=" + j);
	}

	public static void main(String args[]){
	System.out.println("|========================|");
	ObjectTest obj1 = new ObjectTest();
	obj1.printInstanceInt();
	obj1.printClassInt();
	System.out.println("|========================|");
	ObjectTest.printClassInt();
	}

}