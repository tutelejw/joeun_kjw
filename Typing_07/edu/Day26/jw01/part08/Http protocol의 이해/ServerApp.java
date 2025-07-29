import java.io.*;
import java.net.*;

/*
  File Name : ServerApp.java
  :: Browser 에서 http protocol 이용 ServerApp 로 request 시
  :: Data 를 어떻게 전송하는지 확인 위한 port 7000 을 open 하는 
  :: Server 역할의 ServerApp
*/
public class ServerApp{

	///main method		
	public static void main(String[] args) throws Exception{

		// 7000 port 를 open 하는  ServerSocket 인스턴스 생성
		ServerSocket ss = new ServerSocket(7000);
		System.out.println("7000번 port 로 client 의 접속을 기다립니다.......");

		// 7000 prot 접속시 socket 인스턴스 리턴
		Socket s = ss.accept();

		System.out.println("Client : "+s.getInetAddress().getHostName()+ " :: 7000 port 접속");
			
		System.out.println("\n*****************************************************");
		System.out.println("**** IE에서 보내온 내용은 아래와 같습니다.****");
		System.out.println("*****************************************************\n");

		// browser에서 전송된 정보를 읽기위한 InputStream 생성
		InputStream is = s.getInputStream();
		
		// client (browser)로 부터 전달 받은 내용 출력
		while(true){
			int i = is.read();
			System.out.print((char)i);
			if(i == -1) break;
		}
		
	}//end of main

}//end of class
