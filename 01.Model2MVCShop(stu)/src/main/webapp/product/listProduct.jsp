<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
    String uri = request.getRequestURI();
    String url = request.getRequestURL().toString();
    String query = request.getQueryString();
    String method = request.getMethod();

    System.out.println("[로그] 요청 URI: " + uri);
    System.out.println("[로그] 전체 URL: " + url);
    System.out.println("[로그] 쿼리스트링: " + query);
    System.out.println("[로그] 요청 방식: " + method);

    HashMap<String,Object> map = (HashMap<String,Object>)request.getAttribute("map");
    SearchVO searchVO = (SearchVO)request.getAttribute("searchVO");
    UserVO userVO = (UserVO)session.getAttribute("user");

    int total = 0;
    String menu = "search";

    ArrayList<ProductVO> list = null;
    if (map != null) {
        total = ((Integer)map.get("count")).intValue();
        list = (ArrayList<ProductVO>)map.get("list");
    }

    int currentPage = searchVO.getPage();
    int totalPage = 0;
    if (total > 0) {
        totalPage = total / searchVO.getPageUnit();
        if (total % searchVO.getPageUnit() > 0)
            totalPage += 1;
    }

    String menuParam = request.getParameter("menu");
    String link;
    String title = "상품 목록 조회";

    if ("manage".equals(menuParam)) {
        title = "상품관리";
    }

    // 페이징을 위한 검색 조건 유지 처리
    String condition = searchVO.getSearchCondition();
    String keyword = searchVO.getSearchKeyword();
    String queryParam = "";
    if (condition != null && keyword != null && !keyword.trim().equals("")) {
        queryParam = "&searchCondition=" + condition + "&searchKeyword=" + keyword;
    }

    // null 방지를 위한 안전한 값 초기화
    condition = condition != null ? condition : "";
    keyword = keyword != null ? keyword : "";
%>

<html>
<head>
<title><%=title %></title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript">
function fncGetProductList(){
    document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
    <tr>
        <td width="15" height="37">
            <img src="/images/ct_ttl_img01.gif" width="15" height="37">
        </td>
        <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="93%" class="ct_ttl01"><%=title %></td>
                </tr>
            </table>
        </td>
        <td width="12" height="37">
            <img src="/images/ct_ttl_img03.gif" width="12" height="37">
        </td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
<tr>
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <option value="0" <%= "0".equals(condition) ? "selected" : "" %>>상품ID</option>
            <option value="1" <%= "1".equals(condition) ? "selected" : "" %>>상품명</option>
        </select>
        <input type="text" name="searchKeyword"
               value="<%= keyword %>"
               class="ct_input_g" style="width:200px; height:19px">
    </td>
    <td align="right" width="70">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
                <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                    <a href="javascript:fncGetProductList();">검색</a>
                </td>
                <td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
            </tr>
        </table>
    </td>
</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
<tr>
    <td colspan="11">전체  <%= total %> 건수, 현재 <%= currentPage %> 페이지</td>
</tr>
<tr>
    <td class="ct_list_b" width="100">No</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="150">상품ID</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="150">상품명</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b">등록일</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b">상태</td>
</tr>
<tr>
    <td colspan="9" bgcolor="808285" height="1"></td>
</tr>

<%
    int no = list.size();
    for (int i = 0; i < list.size(); i++) {
        ProductVO vo = list.get(i);

        if ("manage".equals(menuParam)) {
            link = "/getProduct.do?prodNo=" + vo.getProdNo() + "&menu=manage";
        } else {
            if ("재고없음".equals(vo.getProTranCode())) {
                link = ""; // 링크 없음
            } else {
                link = "/getProduct.do?prodNo=" + vo.getProdNo();
            }
        }
%>
<tr class="ct_list_pop">
    <td align="center"><%= no-- %></td>
    <td></td>
    <td align="left">
        <% if (link.isEmpty()) { %>
            <%= vo.getProdNo() %>
        <% } else { %>
            <a href="<%=link%>"><%= vo.getProdNo() %></a>
        <% } %>
    </td>
    <td></td>
    <td align="left"><%= vo.getProdName() %></td>
    <td></td>
    <td align="left"><%= vo.getRegDate() %></td>
    <td></td>
    <td align="left"><%= vo.getProTranCode() %></td>
</tr>
<tr>
    <td colspan="9" bgcolor="D6D7D6" height="1"></td>
</tr>
<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
<tr>
    <td align="center">
        <% for (int i = 1; i <= totalPage; i++) { %>
            <a href="/listProduct.do?page=<%=i%><%=queryParam %>"><%= i %></a>
        <% } %>
    </td>
</tr>
</table>

</form>
</div>
</body>
</html>
