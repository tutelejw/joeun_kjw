<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 등록</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>
  <style>
    /* 리스트와 톤 매칭: 유리 패널 + 둥근 라운드 */
    .panel{
      background: rgba(255,255,255,0.55);
      border: 1px solid var(--line);
      border-radius: 16px;
      overflow: hidden;
      background-clip: padding-box;
      max-width: 1100px;
      margin: 24px auto;
      box-shadow: 0 8px 24px rgba(0,0,0,.12);
      backdrop-filter: blur(12px) saturate(120%);
      -webkit-backdrop-filter: blur(12px) saturate(120%);
    }
    .panel-header{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 12px 14px;
      border-bottom: 1px solid var(--line);
    }
    .panel-title{
      font-size: 18px;
      font-weight: 700;
    }
    .panel-body{
      padding: 16px 18px;
    }
    .panel-footer{
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 12px 14px;
      border-top: 1px solid var(--line);
    }

    /* 입력 영역: 불투명 네모 박스 */
    .form-card{
      background: var(--surface);
      border: 1px solid var(--line);
      border-radius: 14px;
      padding: 18px;
      box-shadow: 0 4px 14px rgba(0,0,0,.06);
    }
    .form-row{
      display: flex;
      gap: 12px;
      margin-bottom: 12px;
    }
    .form-col{
      flex: 1;
      display: flex;
      flex-direction: column;
    }
    label{
      font-weight: 600;
      margin-bottom: 6px;
    }
    input[type="text"], textarea{
      width: 100%;
      padding: 10px 12px;
      border: 1px solid #e5e7eb;
      border-radius: 10px;
      font: inherit;
      background: #fff; /* 불투명 */
    }
    textarea{
      min-height: 220px;
      resize: vertical;
    }

    .muted{
      color: #6b7280;
    }
    .btn{
      display: inline-block;
      border: 1px solid #222;
      padding: 8px 12px;
      text-decoration: none;
      color: #111;
      background: #fff;
      border-radius: 4px;
      cursor: pointer;
      font-weight: 600;
    }
    .btn.primary{
      background: #111;
      color: #fff;
    }

    @supports not ((backdrop-filter: blur(1px)) or (-webkit-backdrop-filter: blur(1px))){
      .panel{ background: rgba(255,255,255,0.9); }
    }
  </style>
</head>
<body>

  <section class="panel">
    <div class="panel-header">
      <div class="panel-title">공지 등록</div>
      <div>
        <a class="btn" href="<c:url value='/listNotice.do'/>">목록</a>
      </div>
    </div>

    <div class="panel-body">
      <c:if test="${not empty error}">
        <div style="color:#b00; margin-bottom:10px;"><c:out value="${error}"/></div>
      </c:if>

      <form method="post"
            action="<c:url value='/addNotice.do'/>"
            class="form-card">
        <c:set var="authorIdFromSession"
               value="${not empty sessionScope.user.userId
                       ? sessionScope.user.userId
                       : (not empty sessionScope.user.id
                           ? sessionScope.user.id
                           : (not empty sessionScope.loginUser.userId
                               ? sessionScope.loginUser.userId
                               : (not empty sessionScope.loginUser.id
                                   ? sessionScope.loginUser.id
                                   : '')))}" />

        <input type="hidden" name="authorId" value="${authorIdFromSession}" />

        <div class="form-row">
          <div class="form-col">
            <label for="title">제목</label>
            <input id="title" name="title" type="text" required
                   value="${fn:escapeXml(param.title)}" />
          </div>
        </div>

        <div class="form-row">
          <div class="form-col">
            <label for="content">내용</label>
            <textarea id="content" name="content" required>${fn:escapeXml(param.content)}</textarea>
          </div>
        </div>

        <div class="form-row" style="justify-content:flex-end; gap:8px;">
          <button class="btn primary" type="submit">등록</button>
          <a class="btn" href="<c:url value='/listNotice.do'/>">취소</a>
        </div>
      </form>
    </div>
  </section>

</body>
</html>