package com.semi.view.volRequest;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.Page;
import com.semi.common.Search;
import com.semi.framework.Action;
import com.semi.service.volRequest.VolRequestService;
import com.semi.service.volRequest.impl.VolRequestServiceImpl;

public class ListVolRequestAction extends Action {

    private final VolRequestService service = new VolRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // region : 필수 근데 이거 여기서 체크하면 안됨
        String region   = trim(request.getParameter("region"));
        String category = trim(request.getParameter("category"));

        // 작성자 ID : 세션에서 (목록은 본인 글 기준)
        String authorId = getLoginUserId(request);
//        if (authorId == null || authorId.isEmpty()) {
//            throw new IllegalArgumentException("로그인 정보가 없습니다.(authorId)");
//        }
//        if (region == null || region.isEmpty()) {
//            throw new IllegalArgumentException("region 파라미터가 필요합니다.");
//        }

        // 페이징 정보 구성
        int currentPage = parseIntOrDefault(request.getParameter("page"), 1);
        int pageSize    = parseIntOrDefault(request.getParameter("pageSize"), getInitParamInt(request, "pageSize", 10));
        int pageUnit    = getInitParamInt(request, "pageUnit", 10);

        Search search = new Search();
        search.setCurrentPage(currentPage);
        search.setPageSize(pageSize);
        search.setSearchCondition(request.getParameter("searchCondition"));
        search.setSearchKeyword(request.getParameter("searchKeyword"));
        
        String status = request.getParameter("status"); // "모집중" 또는 null

        Map<String, Object> map = service.getVolRequestList(search, category, pageUnit, status);

        //request.setAttribute("map", map);
        request.setAttribute("list", map.get("list"));
        request.setAttribute("page", (Page) map.get("page"));
        request.setAttribute("totalCount", map.get("totalCount"));
        request.setAttribute("search", map.get("search"));
//        request.setAttribute("region", region);
//        request.setAttribute("category", category);

        return "forward:/volRequest/listVolRequest.jsp";
    }

    // ===== helpers =====
    private static String trim(String s){ return s==null?null:s.trim(); }
    private static int parseIntOrDefault(String s,int def){ try{ return (s==null||s.trim().isEmpty())?def:Integer.parseInt(s.trim()); }catch(Exception e){ return def; } }
    private static int getInitParamInt(HttpServletRequest req,String name,int def){
        String v=req.getServletContext().getInitParameter(name);
        try{ return (v==null||v.trim().isEmpty())?def:Integer.parseInt(v.trim()); }catch(Exception e){ return def; }
    }
    private static String getLoginUserId(HttpServletRequest req){
        if(req.getSession(false)!=null){
            Object v1=req.getSession(false).getAttribute("userId"); if(v1!=null) return String.valueOf(v1);
            Object v2=req.getSession(false).getAttribute("loginUserId"); if(v2!=null) return String.valueOf(v2);
        }
        String p=req.getParameter("authorId"); return p==null?null:p.trim();
    }
}
