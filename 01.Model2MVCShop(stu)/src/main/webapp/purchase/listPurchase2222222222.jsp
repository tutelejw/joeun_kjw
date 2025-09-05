<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
    HashMap<String,Object> map = (HashMap<String,Object>)request.getAttribute("map");
    SearchVO searchVO = (SearchVO)request.getAttribute("searchVO");

    int total = 0;
    ArrayList<PurchaseVO> list = null;

    if (map != null) {
        total = ((Integer)map.get("count")).intValue();
        list = (ArrayList<PurchaseVO>)map.get("list");
    }

    int currentPage = searchVO.getPage();
    int totalPage = (total + searchVO.getPageUnit() - 1) / searchVO.getPageUnit();

    String condition = searchVO.getSearchCondition() != null ? searchVO.getSearchCondition() : "";
    String keyword = searchVO.getSearchKeyword() != null ? searchVO.getSearchKeyword() : "";
    String queryParam = "";
    if (!keyword.trim().equals("")) {
        queryParam = "&searchCondition=" + condition + "&searchKeyword=" + keyword;
    }
%>

<html>
<head>
<title>구매 내역 조회</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript">
function fncGetPurchaseList() {
    document.purchaseForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<div style="width:98%; margin-left:10px;">

<form name="purchaseForm" action="/listPurchase.do" method="post">

<!-- 상단 타이틀 -->
<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="15"><img src="/images/ct_ttl_img01.gif" width="15" height="37"></td>
        <td background="/images/ct_ttl_img02.gif" style="padding-left:10px;">
            <table width="100%">
                <tr>
                    <td class="ct_ttl01">구매 내역 조회</td>
                </tr>
            </table>
        </td>
        <td width="12"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
    </tr>
</table>

<!-- 검색 조건 -->
<table width="100%" style="margin-top:10px;">
<tr>
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <option value="0" <%= "0".equals(condition) ? "selected" : "" %>>구매번호</option>
            <option value="1" <%= "1".equals(condition) ? "selected" : "" %>>구매자ID</option>
        </select>
        <input type="text" name="searchKeyword" value="<%= keyword %>"
               class="ct_input_g" style="width:200px; height:19px">
    </td>
    <td align="right" width="70">
        <table border="0">
            <tr>
                <td><img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
                <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                    <a href="javascript:fncGetPurchaseList();">검색</a>
                </td>
                <td><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
            </tr>
        </table>
    </td>
</tr>
</table>

<!-- 리스트 테이블 -->
<table width="100%" style="margin-top:10px;">
<tr>
    <td colspan="9">전체 <%= total %> 건수, 현재 <%= currentPage %> 페이지</td>
</tr>
<tr>
    <td class="ct_list_b" width="60">No</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="100">구매번호</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="150">구매자ID</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="120">수령자명</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b" width="150">수령자 전화</td>
    <td class="ct_line02"></td>
    <td class="ct_list_b">상태</td>
</tr>
<tr>
    <td colspan="11" bgcolor="808285" height="1"></td>
</tr>

<%
    int no = total - (currentPage - 1) * searchVO.getPageUnit();
    for (int i = 0; i < list.size(); i++) {
        PurchaseVO vo = list.get(i);
%>
<tr class="ct_list_pop">
    <td align="center"><%= no-- %></td>
    <td></td>
    <td align="center"><%= vo.getTranNo() %></td>
    <td></td>
    <td align="center"><%= vo.getBuyer().getUserId() %></td>
    <td></td>
    <td align="center"><%= vo.getReceiverName() %></td>
    <td></td>
    <td align="center"><%= vo.getReceiverPhone() %></td>
    <td></td>
    <td align="center"><%= vo.getTranCode() %></td>
</tr>
<tr>
    <td colspan="11" bgcolor="D6D7D6" height="1"></td>
</tr>
<% } %>
</table>

<!-- 페이징 -->
<table width="100%" style="margin-top:10px;">
<tr>
    <td align="center">
        <% for (int i = 1; i <= totalPage; i++) { %>
            <a href="/listPurchase.do?page=<%=i%><%=queryParam %>"><%= i %></a>
        <% } %>
    </td>
</tr>
</table>

</form>
</div>
</body>
</html>
