package com.semi.view.volRequest;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolRequest;
import com.semi.framework.Action;
import com.semi.service.volRequest.VolRequestService;
import com.semi.service.volRequest.impl.VolRequestServiceImpl;

public class UpdateVolRequestAction extends Action {

    private final VolRequestService service = new VolRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));
        VolRequest before = service.getVolRequest(volunteerId);
        if (before == null) {
            throw new IllegalArgumentException("수정 대상 게시글을 찾을 수 없습니다.");
        }

        // 세션 지역 우선 아님: 폼 값이 없으면 '기존 DB 값'을 그대로 사용
        String title    = fallback(nvl(request.getParameter("title")), before.getTitle());
        String content  = fallback(nvl(request.getParameter("content")), before.getContent());
        String phone    = fallback(trim(request.getParameter("phone")), before.getPhone());
        String region   = fallback(nvl(request.getParameter("region")), before.getRegion());
        String category = fallback(trim(request.getParameter("category")), before.getCategory());
        String startStr = nvl(request.getParameter("start"));
        String endStr   = nvl(request.getParameter("end"));

        LocalDateTime start = (startStr.isEmpty()) ? before.getStartTime() : parseDateTime(startStr);
        LocalDateTime end   = (endStr.isEmpty())   ? before.getEndTime()   : parseDateTime(endStr);
        if (start == null || end == null || !end.isAfter(start)) {
            throw new IllegalArgumentException("start/end 유효성을 확인하세요.(end > start)");
        }

        VolRequest vo = new VolRequest();
        vo.setPostId(volunteerId);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setPhone(phone);
        vo.setRegion(region);
        vo.setCategory(category);
        vo.setStartTime(start);
        vo.setEndTime(end);

        service.updateVolRequest(vo);

        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/detailVolRequest.do?volunteerId=" + volunteerId + qs;
    }

    // ===== helpers =====
    private static String nvl(String s){ return s==null?"" : s.trim(); }
    private static String trim(String s){ return s==null?null: s.trim(); }
    private static String fallback(String v, String def){ return (v==null || v.isEmpty()) ? def : v; }
    private static LocalDateTime parseDateTime(String s){
        String x = s==null? "" : s.trim();
        if (x.isEmpty()) return null;
        String[] pats = {"yyyy-MM-dd HH:mm","yyyy-MM-dd'T'HH:mm","yyyy/MM/dd HH:mm","yyyy.MM.dd HH:mm","yyyyMMdd HHmm"};
        for(String p : pats){
            try{ return LocalDateTime.parse(x, java.time.format.DateTimeFormatter.ofPattern(p)); }catch(Exception ignore){}
        }
        try{ return LocalDateTime.parse(x); }catch(Exception ignore){}
        return null;
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
