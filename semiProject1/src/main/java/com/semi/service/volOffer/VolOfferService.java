package com.semi.service.volOffer;

import java.util.Map;

import com.semi.common.Search;
import com.semi.domain.VolOffer;

/*
 * 회원관리를 추상화 캡슐화한 VolOfferService Interface
 */
public interface VolOfferService {
	
	public void addVolOffer(VolOffer volOffer) throws Exception;
	
	//public VolOffer loginVolOffer(VolOffer volOffer) throws Exception;
	
	//public VolOffer getVolOffer(String volOfferId) throws Exception;
	
	public Map<String, Object> getVolOfferList(Search search) throws Exception;
	
	//public void updateVolOffer(VolOffer volOffer) throws Exception;
	
	//public boolean checkDuplication(String volOfferId) throws Exception;
	
}