<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 본문 조각용: html/head/body/공통 CSS 포함 금지 -->

<c:choose>
  <c:when test="${empty user}">
    <section class="section">
      <h2>사용자 정보를 찾을 수 없습니다.</h2>
      <p>로그인이 필요할 수 있어요.</p>
      <p>
        <a href="<c:url value='/loginView.do'/>" class="btn">로그인 하러 가기</a>
      </p>
    </section>
  </c:when>

  <c:otherwise>
    <section class="section">
      <h2 class="page-title">내 정보 보기</h2>

      <div class="card card--wide"><%-- card 폭은 CSS에서 제어 --%>
        <table class="table table--profile">
          <colgroup>
            <col style="width:160px;" />
            <col />
          </colgroup>
          <tbody>
            <tr>
              <th>아이디</th>
              <td><c:out value="${user.userId}"/></td>
            </tr>
            <tr>
              <th>이름</th>
              <td><c:out value="${user.name}"/></td>
            </tr>
            <tr>
              <th>생년월일</th>
              <td>
                <c:catch var="bdErr">
                  <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd" var="bdStr"/>
                </c:catch>
                <c:choose>
                  <c:when test="${empty bdErr and not empty bdStr}">
                    ${bdStr}
                  </c:when>
                  <c:otherwise>
                    <c:out value="${user.birthDate}"/>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>전화번호</th>
              <td><c:out value="${user.phone}"/></td>
            </tr>
            <tr>
              <th>성별</th>
              <td>
                <c:choose>
                  <c:when test="${user.gender == 'M'}">남</c:when>
                  <c:when test="${user.gender == 'F'}">여</c:when>
                  <c:otherwise><c:out value="${user.gender}"/></c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>지역</th>
              <td><c:out value="${user.region}"/></td>
            </tr>
            <tr>
              <th>카테고리</th>
              <td><c:out value="${user.category}"/></td>
            </tr>
           
          </tbody>
        </table>
      </div>

      <!-- 로그인 사용자 정보 확인 -->
      <c:set var="login" value="${sessionScope.user != null ? sessionScope.user : sessionScope.loginUser}" />

      <!-- 링크 URL 변수화 -->
      <c:url var="updateUrl" value="/updateUserView.do">
        <c:param name="userId" value="${user.userId}"/>
      </c:url>

      <c:url var="pwdUrl" value="/updatePwdView.do">
        <c:param name="userId" value="${user.userId}"/>
      </c:url>

      <c:url var="deleteUrl" value="/deleteAccView.do"/>

      <div class="actions btn-row">
        <a href="javascript:history.back()" class="btn">뒤로</a>

        <c:if test="${not empty login and login.userId == user.userId}">
          <a class="btn" href="${updateUrl}">정보 수정</a>
          <a class="btn" href="${pwdUrl}">비밀번호 변경</a>
          <a class="btn secondary" href="${deleteUrl}"
            >회원 탈퇴</a>
        </c:if>
      </div>
    </section>
  </c:otherwise>
</c:choose>
