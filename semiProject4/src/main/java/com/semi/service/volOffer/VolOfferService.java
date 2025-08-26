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
	
	public VolOffer getVolOffer(Long postId) throws Exception; //findVolOffer
	
	public Map<String, Object> getVolOfferList(Search search, String region) throws Exception;
	
	public void updateVolOffer(VolOffer volOffer) throws Exception;
	
	//public boolean checkDuplication(String volOfferId) throws Exception;
	
}