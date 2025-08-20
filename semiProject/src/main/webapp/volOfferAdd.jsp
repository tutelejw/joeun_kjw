<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>봉사제공 등록 (테스트)</title>
  <link rel="stylesheet" href="/css/admin.css" type="text/css">
  <style>
    .wrap { max-width: 920px; margin: 24px auto; }
    .card { padding: 20px; border: 1px solid #e5e7eb; border-radius: 12px; }
    .row { display: grid; grid-template-columns: 1fr 1fr; gap: 12px; }
    .row-3 { display: grid; grid-template-columns: 1fr 1fr 1fr; gap: 12px; }
    label { font-weight: 600; display:block; margin: 8px 0 6px; }
    input, select, textarea { width: 100%; padding: 10px; border:1px solid #d1d5db; border-radius: 8px; }
    textarea { min-height: 120px; }
    .btns { display:flex; gap:8px; margin-top:16px; }
    .btn { padding:10px 16px; border-radius:10px; border:0; cursor:pointer; }
    .btn-primary { background:#16a34a; color:#fff; }
    .btn-ghost { background:#f3f4f6; }
    .hint { color:#6b7280; font-size:12px; }
  </style>
  <script>
    function onlyDigits(el){ el.value = el.value.replace(/[^0-9]/g,''); }
    function validateForm(f){
      const req = (name)=> (f[name] && f[name].value.trim().length>0);
      const must = ["title","authorId","startTime","endTime","content","region","category","offerDate","processStatus"];
      for(let k of must){
        if(!req(k)){ alert(k+" 는(은) 필수입니다."); f[k].focus(); return false; }
      }
      // 간단 시간 형식 체크: yyyy-MM-dd HH:mm
      const dt = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/;
      if(!dt.test(f.startTime.value)){ alert("시작시간 형식은 yyyy-MM-dd HH:mm"); f.startTime.focus(); return false; }
      if(!dt.test(f.endTime.value)){ alert("종료시간 형식은 yyyy-MM-dd HH:mm"); f.endTime.focus(); return false; }
      // 전화번호 숫자/하이픈만
      f.phone.value = f.phone.value.replace(/[^0-9\-]/g,'');
      return true;
    }
  </script>
</head>
<body>
<div class="wrap">
  <h2>봉사제공 등록 (VolOffer)</h2>
  <div class="card">
    <!-- TODO: 액션 URL을 프로젝트 액션으로 교체하세요 -->
    <form method="post" action="/addVolOffer.do" onsubmit="return validateForm(this);">
      <!-- Post 공통 영역 -->
      <label for="title">제목 *</label>
      <input id="title" name="title" maxlength="120" placeholder="제목을 입력하세요"/>

      <div class="row">
        <div>
          <label for="authorId">작성자 ID *</label>
          <input id="authorId" name="authorId" maxlength="40" placeholder="ex) user01"/>
        </div>
        <div>
          <label for="phone">연락처</label>
          <input id="phone" name="phone" maxlength="20" placeholder="010-1234-5678" oninput="this.value=this.value.replace(/[^0-9\-]/g,'');"/>
          <div class="hint">숫자와 하이픈만</div>
        </div>
      </div>

      <div class="row">
        <div>
          <label for="startTime">시작시간 *</label>
          <input id="startTime" name="startTime" placeholder="YYYY-MM-DD HH:mm"/>
        </div>
        <div>
          <label for="endTime">종료시간 *</label>
          <input id="endTime" name="endTime" placeholder="YYYY-MM-DD HH:mm"/>
        </div>
      </div>

      <label for="content">내용 *</label>
      <textarea id="content" name="content" placeholder="상세 내용을 입력하세요"></textarea>

      <div class="row">
        <div>
          <label for="region">지역 *</label>
          <input id="region" name="region" maxlength="40" placeholder="ex) 서울 강남구"/>
        </div>
        <div>
          <label for="category">카테고리 *</label>
          <input id="category" name="category" maxlength="40" placeholder="ex) 돌봄 / 청소 / 교육"/>
        </div>
      </div>

      <!-- VolOffer 전용 영역 -->
      <div class="row">
        <div>
          <label for="offerDate">제공 가능일(또는 기준일) *</label>
          <input id="offerDate" name="offerDate" placeholder="YYYY-MM-DD"/>
        </div>
        <div>
          <label for="processStatus">처리상태 *</label>
          <select id="processStatus" name="processStatus">
            <option value="">-- 선택 --</option>
            <option value="REQUESTED">요청접수(REQUESTED)</option>
            <option value="ACCEPTED">수락(ACCEPTED)</option>
            <option value="IN_PROGRESS">진행중(IN_PROGRESS)</option>
            <option value="COMPLETED">완료(COMPLETED)</option>
            <option value="CANCELED">취소(CANCELED)</option>
          </select>
        </div>
      </div>

      <div class="btns">
        <button type="submit" class="btn btn-primary">등록</button>
        <a class="btn btn-ghost" href="/listVolOffer.do">목록</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
