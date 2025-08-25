package com.semi.view.volRequest;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.volRequest.VolRequestService;
import com.semi.service.volRequest.impl.VolRequestServiceImpl;

public class DetailVolRequestAction extends Action {

    private final VolRequestService service = new VolRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));
        Map<String, Object> detail = service.getVolRequestDetail(volunteerId);

        request.setAttribute("item", detail.get("item"));
        request.setAttribute("authorName", detail.get("authorName"));

        // 목록 컨텍스트 유지용
        request.setAttribute("region", request.getParameter("region"));
        request.setAttribute("category", request.getParameter("category"));
        request.setAttribute("page", request.getParameter("page"));
        request.setAttribute("pageSize", request.getParameter("pageSize"));

        return "forward:/volRequest/detailVolRequest.jsp";
    }
}
