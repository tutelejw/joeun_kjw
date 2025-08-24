<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>회원가입</title>

<link rel="stylesheet" href="/css/site.css" type="text/css">

<script type="text/javascript">
<!--
function fncAddVolOffer() {
	// Form 유효성 검증
	var id=document.detailForm.volOfferId.value;
	var pw=document.detailForm.password.value;
	var pw_confirm=document.detailForm.password2.value;
	var name=document.detailForm.volOfferName.value;
	
	if(id == null || id.length <1){
		alert("아이디는 반드시 입력하셔야 합니다.");
		return;
	}
	if(pw == null || pw.length <1){
		alert("패스워드는  반드시 입력하셔야 합니다.");
		return;
	}
	if(pw_confirm == null || pw_confirm.length <1){
		alert("패스워드 확인은  반드시 입력하셔야 합니다.");
		return;
	}
	if(name == null || name.length <1){
		alert("이름은  반드시 입력하셔야 합니다.");
		return;
	}
	
	if(document.detailForm.password.value != document.detailForm.password2.value) {
		alert("비밀번호 확인이 일치하지 않습니다.");
		document.detailForm.password2.focus();
		return;
	}
		
	if(document.detailForm.phone2.value != "" && document.detailForm.phone2.value != "") {
		document.detailForm.phone.value = document.detailForm.phone1.value + "-" + document.detailForm.phone2.value + "-" + document.detailForm.phone3.value;
	} else {
		document.detailForm.phone.value = "";
	}
		
	document.detailForm.action='/addVolOffer.do';
	document.detailForm.submit();
}


function PortalJuminCheck(fieldValue){
    var pattern = /^([0-9]{6})-?([0-9]{7})$/; 
	var num = fieldValue;
    if (!pattern.test(num)) return false; 
    num = RegExp.$1 + RegExp.$2;

	var sum = 0;
	var last = num.charCodeAt(12) - 0x30;
	var bases = "234567892345";
	for (var i=0; i<12; i++) {
		if (isNaN(num.substring(i,i+1))) return false;
		sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
	}
	var mod = sum % 11;
	return ((11 - mod) % 10 == last) ? true : false;
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">손길나눔등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			아이디 (나중에세션하고지움) <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			손길나눔 시작시간 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>
		<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			손길나눔 종료시간 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			손길나눔 내용 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			전화번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			지역(내정보가져와야하는데) <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			카테고리 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">			<input 	type="text" name="volOfferName" class="ct_input_g" 
							style="width:300px; height:19px"  maxLength="50" />
		</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td width="53%">	</td>

		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncAddVolOffer();">등록</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>