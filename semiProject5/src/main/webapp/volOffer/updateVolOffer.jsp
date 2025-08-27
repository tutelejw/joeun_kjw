<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>손길 제공 수정</title>

<link rel="stylesheet" href="/css/site.css" type="text/css">
<style>
    /* 테이블의 배경을 반투명하게 */
    table {
        background-color: rgba(255, 255, 255, 0.9); /* White with 90% opacity */
    }
</style>
<script type="text/javascript">
<!--
function fncUpdateVolOffer() {
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

    document.detailForm.action = "/updateVolOffer.do";
    document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
    <%@ include file="/common/top.jspf"%>

<form name="detailForm" method="post">
<input type="hidden" name="postId" value="${volOffer.postId}">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37"/></td>
        <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="93%" class="ct_ttl01">손길나눔 수정</td>
                    <td width="20%" align="right">&nbsp;</td>
                </tr>
            </table>
        </td>
        <td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37" /></td>
    </tr>
</table>

<!-- 시작시간 수정 -->
<tr>
    <td class="ct_write">손길나눔 시작시간</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">
        <input type="date" name="startDate" value="${volOffer.startTime.substring(0, 10)}" />
        <select name="startHour">
            <c:forEach var="i" begin="0" end="23">
                <c:set var="hour" value="${i < 10 ? '0' + i : i}" />
                <option value="${hour}" ${volOffer.startTime.substring(11, 13) == hour ? 'selected' : ''}>${hour}시</option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

<!-- 종료시간 수정 -->
<tr>
    <td class="ct_write">손길나눔 종료시간</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">
        <input type="date" name="endDate" value="${volOffer.endTime.substring(0, 10)}" />
        <select name="endHour">
            <c:forEach var="i" begin="0" end="23">
                <c:set var="hour" value="${i < 10 ? '0' + i : i}" />
                <option value="${hour}" ${volOffer.endTime.substring(11, 13) == hour ? 'selected' : ''}>${hour}시</option>
            </c:forEach>
        </select>
    </td>
</tr>

<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

<!-- 나머지 폼 요소들 (예시: 내용, 전화번호 등) -->
<tr>
    <td class="ct_write">손길나눔 내용</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">${volOffer.content}</td>
</tr>
<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>
<tr>
    <td class="ct_write">전화번호</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">${volOffer.phone}</td>
</tr>
<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>
<tr>
    <td class="ct_write">지역</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">${volOffer.region}</td>
</tr>
<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>
<tr>
    <td class="ct_write">카테고리</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">${volOffer.category}</td>
</tr>
<tr><td colspan="3" height="1" bgcolor="D6D6D6"></td></tr>

<!-- 수정 버튼 및 취소 버튼 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
    <tr>
        <td width="53%"> </td>
        <td align="right">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="17" height="23">
                        <img src="/images/ct_btnbg01.gif" width="17" height="23" />
                    </td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                        <a href="javascript:fncUpdateVolOffer();">수정</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23" />
                    </td>
                    <td width="30"></td>                    
                    <td width="17" height="23">
                        <img src="/images/ct_btnbg01.gif" width="17" height="23" />
                    </td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                         <a href="javascript:history.go(-1);">취소</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</form>

</body>
</html>
