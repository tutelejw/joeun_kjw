<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ✅ JSTL 디버깅용 메시지 -->
<c:if test="${!empty list}">
    <div style="color:red;">[디버깅] ${param.menu} / list에 데이터가 있습니다. </div>
</c:if>
<c:if test="${empty list}">
    <div style="color:red;">[디버깅] ${param.menu} / list가 비어 있습니다.</div>
</c:if>
<c:if test="${empty resultPage}">
    <div style="color:red;">[디버깅] ${param.menu} / resultPage가 비어 있습니다.</div>
</c:if>
<c:if test="${empty search}">
    <div style="color:red;">[디버깅] ${param.menu} / search가 비어 있습니다.</div>
</c:if>

<!-- ✅ 기본 변수 설정 -->
<c:set var="menuParam" value="${param.menu}" />
<c:set var="title" value="상품 목록 조회" />
<c:if test="${menuParam eq 'manage'}">
    <c:set var="title" value="상품관리" />
</c:if>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="/css/admin.css" type="text/css">

    <script type="text/javascript">
        function fncGetProductList(currentPage) {
            document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">
    <!-- menu 유지 -->
    <input type="hidden" name="menu" value="${param.menu}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}" />

    <!-- 제목 헤더 -->
    <table width="100%" height="37">
        <tr>
            <td width="15"><img src="/images/ct_ttl_img01.gif" width="15" height="37"/></td>
            <td background="/images/ct_ttl_img02.gif" style="padding-left:10px;">
                <span class="ct_ttl01">${title}</span>
            </td>
            <td width="12"><img src="/images/ct_ttl_img03.gif" width="12" height="37"/></td>
        </tr>
    </table>

    <!-- 검색 영역 -->
    <table width="100%" style="margin-top:10px;">
        <tr>
            <td align="right">
                <select name="searchCondition" class="ct_input_g" style="width:80px">
                    <option value="0" ${search.searchCondition == '0' ? 'selected' : ''}>상품ID</option>
                    <option value="1" ${search.searchCondition == '1' ? 'selected' : ''}>상품명</option>
                </select>
                <input type="text" name="searchKeyword" value="${search.searchKeyword}" class="ct_input_g" style="width:200px; height:20px"/>
            </td>
            <td align="right" width="70">
                <table>
                    <tr>
                        <td><img src="/images/ct_btnbg01.gif" width="17" height="23"/></td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                            <a href="javascript:fncGetProductList('1');">검색</a>
                        </td>
                        <td><img src="/images/ct_btnbg03.gif" width="14" height="23"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <!-- 상품 목록 -->
    <table width="100%" style="margin-top:10px;">
        <tr>
            <td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
        </tr>
        <tr>
            <td class="ct_list_b" width="100">No</td><td class="ct_line02"></td>
            <td class="ct_list_b" width="150">상품명</td><td class="ct_line02"></td>
            <td class="ct_list_b" width="150">가격</td><td class="ct_line02"></td>
            <td class="ct_list_b">등록일</td><td class="ct_line02"></td>
            <td class="ct_list_b">상태</td>
        </tr>
        <tr><td colspan="11" bgcolor="808285" height="1"></td></tr>

        <c:forEach var="product" items="${list}" varStatus="status">
            <c:set var="link" value="" />
            <c:choose>
                <c:when test="${menuParam eq 'manage'}">
                    <c:set var="link" value="/updateProductView.do?prodNo=${product.prodNo}" />
                </c:when>
                <c:when test="${product.proTranCode eq '재고없음'}">
                    <c:set var="link" value="" />
                </c:when>
                <c:otherwise>
                    <c:set var="link" value="/getProduct.do?prodNo=${product.prodNo}" />
                </c:otherwise>
            </c:choose>

            <tr class="ct_list_pop">
                <td align="center">${status.index + 1}</td><td></td>
                <td align="left">
                    <c:choose>
                        <c:when test="${empty link}">${product.prodNo}</c:when>
                        <c:otherwise><a href="${link}">${product.prodNo}</a></c:otherwise>
                    </c:choose>
                </td><td></td>
                <td align="left">${product.prodName}</td><td></td>
                <td align="left">${product.regDate}</td><td></td>
                <td align="left">
                    ${product.proTranCode}
                    <c:if test="${menuParam eq 'manage' and product.proTranCode eq '구매완료'}">
                        &nbsp;&nbsp;<a href="/updatePurchaseDelivery.do?prodNo=${product.prodNo}&tranCode=2">[배송하기]</a>
                    </c:if>
                    / [디버깅] 상품명: ${product.prodName} / 상태: ${product.proTranCode} / menu: ${menuParam}
                </td>
            </tr>
            <tr><td colspan="11" bgcolor="D6D7D6" height="1"></td></tr>
        </c:forEach>
    </table>

    <!-- 페이징 -->
    <table width="100%" style="margin-top:10px;">
        <tr>
            <td align="center">
                <c:choose>
                    <c:when test="${resultPage.currentPage <= resultPage.pageUnit}">
                        ◀ 이전
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:fncGetProductList('${resultPage.currentPage - 1}')">◀ 이전</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="i" begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}">
                    <a href="javascript:fncGetProductList('${i}')">${i}</a>
                </c:forEach>

                <c:choose>
                    <c:when test="${resultPage.endUnitPage >= resultPage.maxPage}">
                        이후 ▶
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:fncGetProductList('${resultPage.endUnitPage + 1}')">이후 ▶</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

</form>
</div>

</body>
</html>
