package com.semi.view.volOffer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.VolOfferService;

public class ListVolOfferAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VolOfferService volOfferService = new VolOfferService();
        List<VolOffer> volOfferList = volOfferService.getVolOfferList();

        request.setAttribute("volOfferList", volOfferList);

        return "/volOfferList.jsp";
    }
}