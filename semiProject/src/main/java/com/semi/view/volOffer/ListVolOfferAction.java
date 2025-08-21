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
        // 기존 코드: 직접 컨텐츠 페이지인 volOfferList.jsp로 포워딩 (주석 처리)
        //return "forward:/volOfferList.jsp";
        
        // [신규] layout.jsp의 <main> 영역에 포함될 컨텐츠 페이지(손길나눔 목록)의 경로를 request에 저장합니다.
        request.setAttribute("contentPage", "/volOfferList.jsp");
        
        // [신규] layout.jsp의 <title> 태그에 표시될 페이지 제목을 request에 저장합니다.
        request.setAttribute("pageTitle", "손길나눔");
        
        // [수정] 최종 목적지를 layout.jsp로 변경하여, 템플릿 안에서 페이지가 열리도록 합니다.
        return "forward:/common/layout.jsp";
    }
}