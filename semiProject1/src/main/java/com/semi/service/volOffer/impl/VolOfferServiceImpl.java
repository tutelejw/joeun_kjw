package com.semi.service.volOffer.impl;

import java.util.Map;

import com.semi.common.Search;
import com.semi.domain.VolOffer;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.dao.VolOfferDao;


public class VolOfferServiceImpl implements VolOfferService{
	
	///Field
	private VolOfferDao volOfferDao;
	
	///Constructor
	public VolOfferServiceImpl() {
		volOfferDao=new VolOfferDao();
	}

	///Method
//	public void addVolOffer(VolOffer volOffer) throws Exception {
//		volOfferDao.insertVolOffer(volOffer);
//	}
	public void addVolOffer(VolOffer volOffer) throws Exception {
	volOfferDao.insertVolOffer(volOffer);
}

//	public VolOffer getVolOffer(String volOfferId) throws Exception {
//		return volOfferDao.findVolOffer(volOfferId);
//	}

	public Map<String,Object> getVolOfferList(Search search) throws Exception {
		return volOfferDao.getVolOfferList(search);
	}

//	public void updateVolOffer(VolOffer volOffer) throws Exception {
//		volOfferDao.updateVolOffer(volOffer);
//	}

//	public boolean checkDuplication(String volOfferId) throws Exception {
//		boolean result=true;
//		VolOffer volOffer=volOfferDao.findVolOffer(volOfferId);
//		if(volOffer != null) {
//			result=false;
//		}
//		return result;
//	}
}