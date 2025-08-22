package com.semi.domain;

import java.io.Serializable;

/**
 * 봉사등록(Offer) 게시글
 * - Post 공통 속성 + 오퍼 전용 플래그
 */
public class VolOffer_org extends Post implements Serializable {

    /** 오퍼 전용 플래그(다이어그램의 offerFlag) */
    private boolean offerFlag;

    public boolean isOfferFlag() { return offerFlag; }
    public void setOfferFlag(boolean offerFlag) { this.offerFlag = offerFlag; }
    
    @Override
    public String toString() {
        return "VolOffer{" +
                "offerFlag=" + offerFlag +
                ", " + super.toString() +  // Post.toString() 결과 붙이기
                '}';
    }
}
