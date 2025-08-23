package com.semi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 공통 게시글 추상 클래스
 * - 봉사요청/봉사등록의 공통 속성을 모아 재사용
 * - 댓글 연관(집약 관계: Post가 Comment 목록을 가짐)
 */
public abstract class Post_org implements Serializable {

    // ====== 식별/작성 정보 ======
    /** 게시글 식별자(숫자 시퀀스 권장) */
    private Long volunteerid;
    /** 작성자 식별(사용자 userId 저장) */
    private String authorId;
    /** 제목 */
    private String title;

    // ====== 본문/연락/지역 정보 ======
    /** 본문/내용 */
    private String content;
    /** 연락처(단순 문자열로 저장, 포맷 검증은 서비스/검증단에서 처리) */
    private String phone;
    /** 지역(시/군/구 등 텍스트) */
    private String region;
    /** 카테고리(자유 텍스트. 고정 분류가 필요하면 Enum으로 교체 가능) */
    private String category;

    // ====== 일정/시간 정보 ======
    /** 진행(예정) 날짜 */
    private LocalDate date;
    /** 시작 시간 */
    private LocalTime startTime;
    /** 종료 시간 */
    private LocalTime endTime;

    // ====== 감사/상태/이력 정보 ======
    /** 게시글 상태(예: OPEN, CLOSED 등) */
    private String status;
    /** 생성/수정 시각 */
    private LocalDateTime createdAt;

    // ====== 연관 컬렉션 ======
    /** 댓글 목록(0..*) — 집약(aggregation) */
    private List<Comment> comments = new ArrayList<>();

    // ====== 기본 생성자/전체 필드 접근자 ======
    public Post_org() {}

    // 편의: id 기반 equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post_org)) return false;
        Post_org other = (Post_org) o;
        return Objects.equals(volunteerid, other.volunteerid);
    }
    @Override
    public int hashCode() { return Objects.hash(volunteerid); }

    @Override
	public String toString() {
		return "Post [postId=" + volunteerid + ", authorId=" + authorId + ", title=" + title + ", content=" + content
				+ ", phone=" + phone + ", region=" + region + ", category=" + category + ", date=" + date
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", createdAt="
				+ createdAt + ", comments=" + comments + "]";
	}

    // ====== Getter/Setter (필요한 만큼 생성) ======
    public Long getPostId() { return volunteerid; }
    public void setPostId(Long postId) { this.volunteerid = postId; }

    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
    
}
