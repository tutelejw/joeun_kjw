package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//이 클래스는 Singleton + Factory 역할을 합니다.
public class RequestMapping {
	
	// RequestMapping 객체를 하나만 만들기 위한 static 변수 (싱글톤)
	private static RequestMapping requestMapping;
	// 요청 경로 (ex: /user/login.do) 와 Action 객체를 매핑할 Map
	private Map<String, Action> map;
	// actionmapping.properties 파일의 내용을 저장할 객체
	private Properties properties;
	
	// 생성자 (private): 외부에서 new로 객체를 만들 수 없음
	// 설정파일(resources)을 읽어서 프로퍼티 객체로 저장
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();  // 매핑용 해시맵 생성
		InputStream in = null;
		try{
			// 클래스패스에서 설정 파일 로딩 (예: WEB-INF/classes/actionmapping.properties)
			in = getClass().getClassLoader().getResourceAsStream(resources);
			properties = new Properties();
			properties.load(in); // properties 파일 내용 읽기
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
		}finally{
			// InputStream 닫기 (자원 해제)
			if(in != null){
				try{ in.close(); } catch(Exception ex){}
			}
		}
	}
	
	// getInstance(): Singleton 방식으로 객체를 하나만 생성해 반환
	public synchronized static RequestMapping getInstance(String resources){
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	// 실제 요청 경로(/user/login.do 등)에 대응하는 Action 객체를 반환하는 메서드
	public Action getAction(String path){
		// 이미 생성된 Action이 있다면 재사용 (캐싱)
		Action action = map.get(path);
		
		if(action == null){  // 없다면 새로 생성
			// properties 파일에서 해당 경로에 대응하는 클래스 이름을 가져옴
			String className = properties.getProperty(path);
			System.out.println("prop : " + properties);
			System.out.println("path : " + path);			
			System.out.println("className : " + className);
			className = className.trim();  // 공백 제거
			try{
				Class c = Class.forName(className);  // 클래스 이름으로 실제 클래스 로드
				Object obj = c.newInstance();   // 객체 생성 (기본 생성자 이용)
				if(obj instanceof Action){  // Action 타입인지 확인 (instanceof 체크)
					map.put(path, (Action)obj);  // 맵에 저장 후 반환
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action;
	}
}