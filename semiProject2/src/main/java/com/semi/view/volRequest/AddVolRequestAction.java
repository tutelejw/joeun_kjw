package com.semi.view.volRequest;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolRequest;
import com.semi.framework.Action;
import com.semi.service.volRequest.VolRequestService;
import com.semi.service.volRequest.impl.VolRequestServiceImpl;

public class AddVolRequestAction extends Action {

    private final VolRequestService service = new VolRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authorId = getLoginUserId(request);
//        if (authorId == null || authorId.isEmpty()) {
//            throw new IllegalArgumentException("로그인 정보가 없습니다.(authorId)");
//        }

        String title    = nvl(request.getParameter("title"));
        String content  = nvl(request.getParameter("content"));
        String phone    = trim(request.getParameter("phone"));
        String region   = nvl(request.getParameter("region"));     // 등록은 폼 값 사용
        String category = trim(request.getParameter("category"));  // nullable
        String startStr = nvl(request.getParameter("start"));
        String endStr   = nvl(request.getParameter("end"));

        if (title.isEmpty() || region.isEmpty() || startStr.isEmpty() || endStr.isEmpty()) {
            throw new IllegalArgumentException("필수 항목(title/region/start/end)을 확인하세요.");
        }

        LocalDateTime start = parseDateTime(startStr);
        LocalDateTime end   = parseDateTime(endStr);
        if (start == null || end == null || !end.isAfter(start)) {
            throw new IllegalArgumentException("start/end 유효성을 확인하세요.(end > start)");
        }

        VolRequest vo = new VolRequest();
        vo.setAuthorId(authorId);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setPhone(phone);
        vo.setRegion(region);
        vo.setCategory(category);
        vo.setStartTime(start);
        vo.setEndTime(end);

        long id = service.addVolRequest(vo);

        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/detailVolRequest.do?volunteerId=" + id + qs;
    }

    // ===== helpers =====
    private static String nvl(String s){ return s==null?"" : s.trim(); }
    private static String trim(String s){ return s==null?null: s.trim(); }
    private static String getLoginUserId(HttpServletRequest req){
        if(req.getSession(false)!=null){
            Object v1=req.getSession(false).getAttribute("userId"); if(v1!=null) return String.valueOf(v1);
            Object v2=req.getSession(false).getAttribute("loginUserId"); if(v2!=null) return String.valueOf(v2);
        }
        String p=req.getParameter("authorId"); return p==null?null:p.trim();
    }
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
