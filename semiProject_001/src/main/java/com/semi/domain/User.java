package com.semi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 사용자 엔티티
 * - 로그인/식별에 쓰이는 userId 중심
 * - seqNo는 DB 시퀀스/PK로 사용 가능
 */
public class User implements Serializable {

    // ====== 식별/계정 기본 ======
    /** 내부 PK(시퀀스) */
    private Long seqNo;
    /** 로그인/표시용 사용자 아이디 */
    private String userId;
    /** 이름 */
    private String name;

    // ====== 프로필/연락 ======
    /** 지역 */
    private String region;
    /** 전화번호 */
    private String phone;
    /** 성별 */
    private String gender;
    /** 생년월일 */
    private LocalDate birthDate;

    // ====== 분류/상태 ======
    /** 카테고리(사용자 분류가 필요하면 사용, 없으면 공란) */
    private String category;
    /** 사용자 상태(예: ACTIVE, SUSPENDED 등) */
    private String status;

    public User() {}

    // 동등성: seqNo 또는 userId 기반
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        if (seqNo != null && other.seqNo != null) {
            return Objects.equals(seqNo, other.seqNo);
        }
        return Objects.equals(userId, other.userId);
    }
    @Override
    public int hashCode() { return Objects.hash(seqNo, userId); }

    // Getter/Setter
    public Long getSeqNo() { return seqNo; }
    public void setSeqNo(Long seqNo) { this.seqNo = seqNo; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

	@Override
	public String toString() {
		return "User [seqNo=" + seqNo + ", userId=" + userId + ", name=" + name + ", region=" + region + ", phone="
				+ phone + ", gender=" + gender + ", birthDate=" + birthDate + ", category=" + category + ", status="
				+ status + "]";
	}
    
}
