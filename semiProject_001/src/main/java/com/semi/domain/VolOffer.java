package com.semi.domain;

import java.sql.Date;

public class VolOffer {

    // 멤버변수 (Field)
    private int offerNo;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private int viewCnt;
    private String fileName;
    
    // 기본 생성자
    public VolOffer() {
    }

    // Getter/Setter 메소드
    public int getOfferNo() {
        return offerNo;
    }

    public void setOfferNo(int offerNo) {
        this.offerNo = offerNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
    
    public String getFileName() {
		return fileName;
	}
    
    public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    // toString() 메소드 (디버깅용)
    @Override
    public String toString() {
        return "VolOffer [offerNo=" + offerNo + ", title=" + title + ", content=" + content + ", writer=" + writer
                + ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", fileName=" + fileName + "]";
    }
}