/* cards-slider.js (FULL)
 * - 중앙 캐러셀: 화면엔 N개(기본 3)만 보이게
 * - 5초마다 카드 1칸씩 이동
 * - 무한 루프(양끝 클론 + 경계 점프)
 * - CSS 충돌을 이기기 위해 초기화 시 인라인 스타일로 레이아웃 '강제'
 *   필수 마크업:
 *   <div id="cardsViewport" data-visible="3" data-interval="5000">
 *     <section class="cards"> ... <div class="card">...</div> ... </section>
 *   </div>
 */
(function () {
  var viewport = document.getElementById('cardsViewport');
  if (!viewport) return;

  var track = viewport.querySelector('.cards') || document.getElementById('cardsTrack');
  if (!track) return;

  // ===== 0) 레이아웃 하드 고정(중앙정렬 + 가로 1줄 + 클리핑) =====
  function hardenLayout() {
    // 뷰포트: 가운데 + 폭 제한 + 클리핑 + 상단 여백 토큰 무력화
    viewport.style.maxWidth = '1100px';
    viewport.style.width = '100%';
    viewport.style.marginLeft = 'auto';
    viewport.style.marginRight = 'auto';
    viewport.style.overflow = 'hidden';
    viewport.style.boxSizing = 'border-box';
    viewport.style.setProperty('--cards-top-offset', '0px');

    // 트랙: grid → flex로 치환(가로 한 줄)
    track.style.display = 'flex';
    track.style.gap = track.style.gap || '20px';
    track.style.margin = '0 auto';
    track.style.padding = track.style.padding || '0 24px';
    track.style.maxWidth = 'none';
    track.style.willChange = 'transform';
  }
  hardenLayout();

  // ===== 1) 옵션 =====
  var VISIBLE = parseInt(viewport.getAttribute('data-visible') || '3', 10);
  var INTERVAL_MS = parseInt(viewport.getAttribute('data-interval') || '5000', 10);

  // 원본 카드 수
  var originals = Array.prototype.slice.call(track.children);
  var ORG_COUNT = originals.length;

  if (ORG_COUNT <= VISIBLE) {
    // 카드가 3개 이하일 땐 슬라이더 불필요 — 최소 스타일만
    originals.forEach(function (c) { c.classList.add('shrink-0','bg-gray-50','rounded-xl','p-4','shadow-sm'); });
    return;
  }

  // ===== 2) 무한 루프용 클론 추가 =====
  addClones();
  var allItems = Array.prototype.slice.call(track.children);

  // 현재 인덱스: 앞쪽 클론 VISIBLE개 뒤(=원본 0번째가 화면 왼쪽에 오게)
  var index = VISIBLE;

  // 상태
  var gapPx = 0, cardW = 0, stepX = 0;
  var timerId = null;

  // ===== 3) 레이아웃 계산 =====
  function getGapPx() {
    var cs = window.getComputedStyle(track);
    return parseFloat(cs.gap || cs.columnGap || '0') || 0;
  }

  function layout() {
    gapPx = getGapPx();
    var vw = viewport.clientWidth; // 뷰포트 실제 폭
    cardW = (vw - gapPx * (VISIBLE - 1)) / VISIBLE;
    stepX = cardW + gapPx;

    allItems.forEach(function (it) {
      it.style.flex = '0 0 ' + cardW + 'px'; // 화면에 정확히 N개
    });

    // 리사이즈 시 현재 위치 보정
    setTransition(false);
    jumpTo(index);
    requestAnimationFrame(function(){ setTransition(true); });
  }

  function setTransition(on) {
    track.style.transition = on ? 'transform 0.5s ease-in-out' : 'none';
  }

  function jumpTo(i) {
    track.style.transform = 'translateX(' + (-i * stepX) + 'px)';
  }

  // ===== 4) 이동/루프 =====
  function next() {
    index += 1;
    jumpTo(index);
  }
  function prev() {
    index -= 1;
    jumpTo(index);
  }

  function handleEdgeLoop() {
    var firstOriginalIndex = VISIBLE;
    var lastOriginalIndex  = VISIBLE + ORG_COUNT - 1;

    if (index > lastOriginalIndex) {
      // 오른쪽 끝 통과 → 같은 위치의 '원본 첫 칸'으로 점프(Transition 끄고 순간이동)
      setTransition(false);
      index = firstOriginalIndex;
      jumpTo(index);
      requestAnimationFrame(function(){ setTransition(true); });
    } else if (index < firstOriginalIndex) {
      // 왼쪽 끝 통과 → 같은 위치의 '원본 마지막 칸'으로 점프
      setTransition(false);
      index = lastOriginalIndex;
      jumpTo(index);
      requestAnimationFrame(function(){ setTransition(true); });
    }
  }

  // ===== 5) 자동재생 =====
  function startAuto() { stopAuto(); timerId = setInterval(next, INTERVAL_MS); }
  function stopAuto()  { if (timerId) { clearInterval(timerId); timerId = null; } }

  // ===== 6) 초기화 & 이벤트 =====
  window.addEventListener('resize', layout);
  track.addEventListener('transitionend', handleEdgeLoop);
  viewport.addEventListener('mouseenter', stopAuto);
  viewport.addEventListener('mouseleave', startAuto);

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', function(){ layout(); jumpTo(index); startAuto(); });
  } else {
    layout(); jumpTo(index); startAuto();
  }

  // 선택: 좌우 버튼(data-cards-prev/next)이 있으면 연결
  var btnPrev = viewport.querySelector('[data-cards-prev]');
  var btnNext = viewport.querySelector('[data-cards-next]');
  if (btnPrev) btnPrev.addEventListener('click', function(){ stopAuto(); prev(); });
  if (btnNext) btnNext.addEventListener('click', function(){ stopAuto(); next(); });

  // ===== 유틸: 클론 작업 =====
  function addClones() {
    // 안전: 기존 클론 제거
    Array.prototype.slice.call(track.querySelectorAll('[data-clone="1"]')).forEach(function (n) { n.remove(); });

    // 앞쪽: 마지막 VISIBLE개 → 역순으로 prepend
    var tail = originals.slice(-VISIBLE).map(function (node) {
      var c = node.cloneNode(true);
      c.setAttribute('data-clone','1');
      c.classList.add('shrink-0','bg-gray-50','rounded-xl','p-4','shadow-sm');
      return c;
    });
    tail.forEach(function (c) { track.insertBefore(c, track.firstChild); });

    // 뒤쪽: 처음 VISIBLE개 → append
    var head = originals.slice(0, VISIBLE).map(function (node) {
      var c = node.cloneNode(true);
      c.setAttribute('data-clone','1');
      c.classList.add('shrink-0','bg-gray-50','rounded-xl','p-4','shadow-sm');
      return c;
    });
    head.forEach(function (c) { track.appendChild(c); });
  }
})();
