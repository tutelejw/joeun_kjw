package study0830;

interface ClickListener {
	void onClick();
}

class ButtonHandler implements ClickListener {
	public void onClick() {
		System.out.println("버튼 클릭되었음 작업실행합니다.");
	}
}

public class Button {
	private ClickListener listener;
	
	public void setOnClickListener(ClickListener listener) {
		this.listener=listener;
	}
	public void click() {
		if(listener != null) {
			System.out.println("버튼이 눌렸습니다 리스너 호출");
			listener.onClick(); //콜백 메서드 호출
		}
	}
	
	public static void main(String[] args) {
		Button myButton = new Button();
		
		myButton.setOnClickListener(new ButtonHandler());
		myButton.click();
	}
}
