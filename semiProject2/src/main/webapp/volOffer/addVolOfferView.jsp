<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>손길나눔 등록</title>
<link rel="stylesheet" href="/css/site.css" type="text/css">

<script type="text/javascript">
function fncAddVolOffer() {
    var startDate = document.detailForm.startDate.value;
    var startHour = document.detailForm.startHour.value;
    var endDate = document.detailForm.endDate.value;
    var endHour = document.detailForm.endHour.value;

    if (!startDate || !endDate) {
        alert("시작일과 종료일을 입력하세요.");
        return;
    }

    var startTimeStr = startDate + " " + startHour + ":00:00";
    var endTimeStr = endDate + " " + endHour + ":00:00";

    // 기존 필드 삭제 (중복 방지)
    let oldStart = document.querySelector("input[name='startTime']");
    if (oldStart) oldStart.remove();
    let oldEnd = document.querySelector("input[name='endTime']");
    if (oldEnd) oldEnd.remove();

    let startInput = document.createElement("input");
    startInput.type = "hidden";
    startInput.name = "startTime";
    startInput.value = startTimeStr;
    document.detailForm.appendChild(startInput);

    let endInput = document.createElement("input");
    endInput.type = "hidden";
    endInput.name = "endTime";
    endInput.value = endTimeStr;
    document.detailForm.appendChild(endInput);

    // 전화번호 조합
    if (document.detailForm.phone2.value && document.detailForm.phone3.value) {
        document.detailForm.phone.value =
            document.detailForm.phone1.value + "-" +
            document.detailForm.phone2.value + "-" +
            document.detailForm.phone3.value;
    } else {
        document.detailForm.phone.value = "";
    }

    document.detailForm.action = "/addVolOffer.do";
    document.detailForm.submit();
}


</script>
</head>

<body bgcolor="#ffffff" text="#000000">
	<%@ include file="/common/top.jspf"%>
<form name="detailForm" method="post">

<!-- 숨김 필드 -->
<input type="hidden" name="phone" />

<!-- 상단 타이틀 -->
<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="15"><img src="/images/ct_ttl_img01.gif" width="15" height="37"/></td>
        <td background="/images/ct_ttl_img02.gif" style="padding-left:10px;">
            <span class="ct_ttl01">손길나눔 등록</span>
        </td>
        <td width="12"><img src="/images/ct_ttl_img03.gif" width="12" height="37"/></td>
    </tr>
</table>

<!-- 등록 폼 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- authorId (테스트용 입력) -->
    <tr>
        <td width="104" class="ct_write">작성자 ID (authorId) <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6" width="1"></td>
        <td class="ct_write01"><input type="text" name="authorId" class="ct_input_g" style="width:300px;" /></td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 제목 -->
    <tr>
        <td class="ct_write">제목 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01"><input type="text" name="title" class="ct_input_g" style="width:300px;" /></td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 시작시간 -->
    <tr>
        <td class="ct_write">손길나눔 시작시간 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <input type="date" name="startDate" />
            <select name="startHour">
                <c:forEach var="i" begin="0" end="23">
                    <c:set var="hour" value="${i < 10 ? '0' + i : i}" />
                    <option value="${hour}">${i}시</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 종료시간 -->
    <tr>
        <td class="ct_write">손길나눔 종료시간 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <input type="date" name="endDate" />
            <select name="endHour">
                <c:forEach var="i" begin="0" end="23">
                    <c:set var="hour" value="${i < 10 ? '0' + i : i}" />
                    <option value="${hour}">${i}시</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 내용 -->
    <tr>
        <td class="ct_write">손길나눔 내용 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <textarea name="content" class="ct_input_g" rows="4" cols="50" style="width:300px;"></textarea>
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 전화번호 -->
    <tr>
        <td class="ct_write">전화번호 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <input type="text" name="phone1" maxlength="3" size="3" /> -
            <input type="text" name="phone2" maxlength="4" size="4" /> -
            <input type="text" name="phone3" maxlength="4" size="4" />
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 지역 -->
    <tr>
        <td class="ct_write">지역 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <input type="text" name="region" class="ct_input_g" style="width:300px;" />
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

    <!-- 카테고리 -->
    <tr>
        <td class="ct_write">카테고리 <img src="/images/ct_icon_red.gif"/></td>
        <td bgcolor="D6D6D6"></td>
        <td class="ct_write01">
            <input type="text" name="category" class="ct_input_g" style="width:300px;" />
        </td>
    </tr>
    <tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>
</table>

<!-- 버튼영역 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
    <tr>
        <td width="53%"></td>
        <td align="right">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23"/></td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                        <a href="javascript:fncAddVolOffer();">등록</a>                    </td>
                    <td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"/></td>
                    <td width="30"></td>
                    <td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23"/></td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                        <a href="javascript:history.go(-1);">취소</a>                    </td>
                    <td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</form>
</body>
</html>
