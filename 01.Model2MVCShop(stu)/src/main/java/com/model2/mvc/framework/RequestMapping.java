package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 요청 URL(Path)에 따라 해당하는 Action 클래스를 생성하고 관리하는 클래스
 * - Singleton 패턴 적용
 * - actionmapping.properties 파일을 읽어 경로-클래스명 매핑
 */
public class RequestMapping {

	private static RequestMapping requestMapping;  // Singleton 인스턴스
	private Map<String, Action> map;               // URL(Path) -> Action 객체 캐시
	private Properties properties;                 // properties 파일의 내용을 담을 객체

	/**
	 * 생성자 - 외부에서 직접 호출 불가 (private)
	 * @param resources : properties 파일명 (예: actionmapping.properties)
	 */
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();       // URL-Action 캐시 맵 초기화
		InputStream in = null;

		try {
			// 클래스패스에서 properties 파일 로딩
			in = getClass().getClassLoader().getResourceAsStream(resources);
			properties = new Properties();
			properties.load(in);  // key=value 형태 로딩

		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :" + ex);

		} finally {
			// 스트림 종료
			if (in != null) {
				try { in.close(); } catch (Exception ex) {}
			}
		}
	}

	/**
	 * Singleton 객체 반환
	 * - 최초 1회만 RequestMapping 생성됨
	 */
	public synchronized static RequestMapping getInstance(String resources) {
		if (requestMapping == null) {
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}

	/**
	 * 요청 경로에 해당하는 Action 객체 반환
	 * @param path : 요청 URI (예: "/addProduct.do")
	 * @return 해당하는 Action 객체
	 */
	public Action getAction(String path) {
		Action action = map.get(path);  // 기존에 생성된 객체가 있으면 그대로 사용

		if (action == null) {
			// properties에서 path에 해당하는 클래스 이름을 가져옴
			String className = properties.getProperty(path);

			System.out.println("prop : " + properties);  // 디버깅용 로그
			System.out.println("path : " + path);
			System.out.println("className : " + className);

			className = className.trim();  // 공백 제거

			try {
				// 문자열로 된 클래스 이름으로 클래스 객체를 로딩
				Class<?> c = Class.forName(className);
				Object obj = c.newInstance();  // 객체 생성

				// 생성된 객체가 Action 인터페이스를 구현했는지 확인
				if (obj instanceof Action) {
					// 캐시에 저장하고 반환
					map.put(path, (Action) obj);
					action = (Action) obj;
				} else {
					throw new ClassCastException("Class형변환시 오류 발생");
				}

			} catch (Exception ex) {
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action;
	}
}
