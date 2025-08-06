package spring.model2.control;

import spring.model2.service.user.view.HomeController;
import spring.model2.service.user.view.LogonActionController;
import spring.model2.service.user.view.LogonController;


/*
 * FileName : ControllerMapping.java
 Client Request 요구사항 command 를 처리한다 Controller 생성 리턴한다.
 Command Factory Pattern
 */
public class ControllerMapping {
	//Field
	private static ControllerMapping controllerMapping;
	//default Constructor
	private ControllerMapping() {
	}
	
	//Method
	public static ControllerMapping getInstance() {
		if(controllerMapping ==null) {
			controllerMapping = new ControllerMapping();
		}
		return controllerMapping;
	}
	
	//Client Request Page(actionPage)를 받아 Request 처리 Controller 생성 return
	public Controller getController(String actionPage) {
		System.out.println("[ControllerMapping.getController() start..................]");
		Controller controller =null;
		
		if(actionPage.equals("logon")) {
			controller = new LogonController();
		}else if(actionPage.equals("logonAction")) {
			controller = new LogonActionController();
		}else if(actionPage.equals("home")) {
			controller = new HomeController();
		}
		/*==>추가 사항이 발생한다면 아래와 같이 추가하면 된다.
		 }else if(actionPage.equals("member")) {
			controller = new MemberAction();
		}else if(actionPage.equals("logout")) {
			controller = new LogoutAction();
		}
		 */
		System.out.println("[ControllerMapping.getController() end-----------]");
		return controller;
	}
	
}//end of class












