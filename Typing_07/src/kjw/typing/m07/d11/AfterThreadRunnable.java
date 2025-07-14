package kjw.typing.m07.d11;

public class AfterThreadRunnable implements Runnable{
	
	private String name;
	
	public AfterThreadRunnable() {
	}
	public AfterThreadRunnable(String name) {
		this.name=name;
	}
	
	public void run() {
		for(int i=1; i<100;i++) {
			System.out.println(name+" : " +i);
			
			///*
				//sleep() ==> API 확인  java.lang.Thread.sleep()
				try{
					
					Thread.sleep(100);
					}catch(InterruptedException e){
					System.out.println(e);
					}
			//*/
		}
		System.out.println("끝끝끝끝끝끝끝");
	}

///main method
	public static void main(String[] args) {
	
		System.out.println("여기는 main start......");
		AfterThreadRunnable bt1 = new AfterThreadRunnable("1번째");
		AfterThreadRunnable bt2 = new AfterThreadRunnable("2번째");
	
		Thread t1 = new Thread(bt1);
		Thread t2 = new Thread(bt2);
		
		t1.start();
		t2.start();
		System.out.println("여기는 main end"	);
		
	}//end of main
}//end of class
