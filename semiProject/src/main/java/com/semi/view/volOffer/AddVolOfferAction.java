package com.semi.view.volOffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.VolOfferService;

public class AddVolOfferAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Service 클래스를 통해 비즈니스 로직 처리
        VolOfferService volOfferService = new VolOfferService();
        volOfferService.addVolOffer(request);

        return "redirect:/listVolOffer.do";
    }
}