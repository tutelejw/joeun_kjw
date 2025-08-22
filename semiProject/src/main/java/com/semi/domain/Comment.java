package com.semi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 댓글 엔티티
 * - 특정 게시글(postId)에 속한 사용자 댓글
 * - 양방향을 피하고 단방향(댓글 -> 게시글id)로 단순화
 */
public class Comment implements Serializable {

    // ====== 식별/연결 정보 ======
    /** 댓글 식별자 */
    private Long commentId;
    /** 원글(Post) 식별자(FK 역할) */
    private Long volunteerid;
    /** 작성자 식별(사용자 userId) */
    private String authorSeqNo;

    // ====== 본문/이력 ======
    /** 내용 */
    private String content;
    /** 생성/수정 시각 */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment() {}

    // id 기반 동등성
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment other = (Comment) o;
        return Objects.equals(commentId, other.commentId);
    }
    @Override
    public int hashCode() { return Objects.hash(commentId); }

    // Getter/Setter
    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }

    public Long getPostId() { return volunteerid; }
    public void setPostId(Long postId) { this.volunteerid = postId; }

    public String getAuthorId() { return authorSeqNo; }
    public void setAuthorId(String authorId) { this.authorSeqNo = authorId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", volunteerid=" + volunteerid + ", authorId=" + authorSeqNo + ", content="
				+ content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
