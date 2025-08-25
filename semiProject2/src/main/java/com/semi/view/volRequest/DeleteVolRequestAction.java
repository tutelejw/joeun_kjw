package com.semi.view.volRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.volRequest.VolRequestService;
import com.semi.service.volRequest.impl.VolRequestServiceImpl;

public class DeleteVolRequestAction extends Action {

    private final VolRequestService service = new VolRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));
        service.deleteVolRequest(volunteerId);

        // 목록 컨텍스트 유지
        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/listVolRequest.do"+ (qs.isEmpty() ? "" : ("?"+qs.substring(1)));
    }

    private static String buildContextQS(HttpServletRequest req, String... keys){
        StringBuilder sb = new StringBuilder();
        for(String k: keys){
            String v=req.getParameter(k);
            if(v!=null && !v.trim().isEmpty()){
                sb.append("&").append(k).append("=").append(url(v.trim()));
            }
        }
        return sb.toString();
    }
    private static String url(String s){
        try{ return java.net.URLEncoder.encode(s,"UTF-8"); }catch(Exception e){ return s; }
    }
}
