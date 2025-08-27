package com.semi.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RequestMapping {
	
	///Field
	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	///Constructor
	private RequestMapping(String resources) {
		
		map = new HashMap<String, Action>();
		
		InputStream in = null;
		try{
			in = getClass().getClassLoader().getResourceAsStream(resources);
			properties = new Properties();
			properties.load(in);
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
		}finally{
			if(in != null){
				try{ 
					in.close(); 
				} catch(Exception ex){ }
			}
		}
	} 
	
	///Method
	public synchronized static RequestMapping getInstance(String resources){
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	public Action getAction(String path){
	    try {
	        String className = properties.getProperty(path);
	        if (className == null || className.trim().isEmpty()) {
	            // 선택 1) 의미 있는 예외
	            throw new IllegalArgumentException("No action mapping for path: " + path);
	            // 선택 2) null 반환하고 상위에서 처리
	            // return null;
	        }
	        className = className.trim();

	        Action action = map.get(path);
	        if (action == null) {
	            action = (Action) Class.forName(className).newInstance();
	            map.put(path, action);
	        }
	        return action;
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to get action for path: " + path + " - " + e, e);
	    }
	}

}