package com.semi.view.volOffer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.dao.VolOfferDAO;

public class ListVolOfferAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // DAO 객체 생성
        VolOfferDAO dao = new VolOfferDAO();
        
        // DAO를 통해 봉사 목록 데이터 가져오기
        List<VolOffer> volOfferList = dao.getVolOfferList();
        
        // request에 봉사 목록 저장
        request.setAttribute("list", volOfferList);
        
        // 데이터를 보여줄 JSP 페이지로 포워딩
        //return "forward:/volunteer/volOfferList.jsp"; 
        return "forward:/volOfferList.jsp";
    }
}