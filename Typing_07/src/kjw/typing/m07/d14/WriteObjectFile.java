//package jp03.part06;
package kjw.typing.m07.d14;

import java.io.*;

/*
 * FileName : WriteObjectFile.java
 * 
 * ㅇ ObjectOutputStream을 이용 회원정보를 갖는 UserVO instance 를 
 *      (객체는 상태를 갖는다/ 상태정보를) persistence data(File)로 저장
 */
public class  WriteObjectFile{
	
	///main Method
	public static void main(String[] args) throws Exception{
		
		//1. instance 를 File에 저장할 SinkStream :: FileOutputStream 생성
		//2. instance 를 전송하는 FilterStream :  ObjectOutputStream 생성
		ObjectOutputStream oos  
//				= new ObjectOutputStream(new FileOutputStream("UserInfo.obj")); //project 경로에 있음.
				= new ObjectOutputStream(new FileOutputStream("./src/kjw/typing/m07/d14/UserInfo.obj"));
		
		oos.writeObject(new UserVO(1,"홍길동")); //==>API확인
		oos.writeObject(new UserVO(2,"홍길순")); //==>API확인
		oos.flush();	
			
		//Stream close()
		oos.close();
		
	}//end of main
	
}//end of class