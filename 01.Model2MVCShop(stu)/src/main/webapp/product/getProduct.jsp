<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>

<%
	ProductVO vo = (ProductVO)request.getAttribute("vo");
%>	

<%
String prodNo = String.valueOf(vo.getProdNo());  // vo에서 상품번호 추출

// 기존 history 쿠키 가져오기
String history = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie c : cookies) {
        if (c.getName().equals("history")) {
            history = c.getValue();
        }
    }
}

// 중복 저장 방지
if (!history.contains(prodNo)) {
    // 최대 10개까지만 저장 (선택사항)
    String[] historyArr = history.split(",");
    if (historyArr.length >= 10) {
        // 맨 앞 제거
        history = history.substring(history.indexOf(",") + 1);
    }

    if (!history.isEmpty()) {
        history += ",";
    }
    history += prodNo;
}

// 쿠키로 저장
Cookie historyCookie = new Cookie("history", history);
historyCookie.setMaxAge(60 * 60 * 24 * 7); // 7일
historyCookie.setPath("/"); // 전체 경로에서 사용 가능
response.addCookie(historyCookie); 
%>

<html>
<head>
<title>상품정보조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품정보조회</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<!-- form 시작: 구매 요청 시 필요한 모든 값들을 같이 전달 -->
<form action="/addPurchaseView.do" method="post">

<!-- 상품정보 테이블 시작 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 13px;">
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품번호 -->
  <tr>
    <td width="104" class="ct_write">상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">
      <%=vo.getProdNo() %>
      <input type="hidden" name="prodNo" value="<%=vo.getProdNo()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품명 -->
  <tr>
    <td class="ct_write">상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getProdName() %>
      <input type="hidden" name="prodName" value="<%=vo.getProdName()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품이미지 -->
  <tr>
    <td class="ct_write">상품이미지 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getFileName() %>
      <input type="hidden" name="fileName" value="<%=vo.getFileName()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품상세정보 -->
  <tr>
    <td class="ct_write">상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getProdDetail() %>
      <input type="hidden" name="prodDetail" value="<%=vo.getProdDetail()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 제조일자 -->
  <tr>
    <td class="ct_write">제조일자</td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getManuDate() %>
      <input type="hidden" name="manuDate" value="<%=vo.getManuDate()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 가격 -->
  <tr>
    <td class="ct_write">가격</td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getPrice() %>
      <input type="hidden" name="price" value="<%=vo.getPrice()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 등록일자 -->
  <tr>
    <td class="ct_write">등록일자</td>
    <td bgcolor="D6D6D6"></td>
    <td class="ct_write01">
      <%=vo.getRegDate() %>
      <input type="hidden" name="regDate" value="<%=vo.getRegDate()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>
</table>

<!-- 버튼 영역 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>

					<!-- 구매 버튼: form submit -->
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						<button type="submit" style="all:unset; cursor:pointer;">구매</button>
					</td>

					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>

					<td width="30"></td>					

					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:history.go(-1);">확인</a>
					</td>
					<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>
