<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>

<%
	ProductVO vo=(ProductVO)request.getAttribute("productVO");
%>

<html>
<head>
<title>상품정보수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncUpdateProduct() {

	var name=document.detailForm.userName.value;
	
	if(name == null || name.length <1){
		alert("이름은  반드시 입력하셔야 합니다.");
		return;
	}
		
	document.detailForm.action='/updateProduct.do';
	document.detailForm.submit();
}

function check_email(frm) {
	alert
	var email=document.detailForm.email.value;
    if(email != "" && (email.indexOf('@') < 1 || email.indexOf('.') == -1)){
    	alert("이메일 형식이 아닙니다.");
		return false;
    }
    return true;
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<input type="hidden" name="prodNo" value="<%=vo.getProdNo() %>">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품정보수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="prodName" value="<%=vo.getProdName() %>" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="prodDetail" value="<%=vo.getProdDetail() %>" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">상품제조일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="manuDate" value="<%=vo.getManuDate() %>" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100">
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">상품가격</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="price" value="<%=vo.getPrice() %>" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100">
		</td>
	</tr>
		<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">상품이미지</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="fileName" value="<%=vo.getFileName() %>" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100">
		</td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncUpdateProduct();">상품수정</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>

</body>
</html>
