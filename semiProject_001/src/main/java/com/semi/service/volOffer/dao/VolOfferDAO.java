package com.semi.service.volOffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.semi.common.util.DBUtil;
import com.semi.domain.VolOffer;

public class VolOfferDAO {

    public List<VolOffer> getVolOfferList() throws Exception {
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // SQL 쿼리: vol_offer 테이블의 모든 데이터를 조회합니다. (테이블 이름은 실제 DB에 맞게 수정해주세요)
        String sql = "SELECT POST_ID,TITLE,CONTENT,AUTHOR_ID FROM vol_offer ORDER BY POST_ID DESC";
//        CREATE TABLE VOL_OFFER (
//        	    POST_ID NUMBER NOT NULL PRIMARY KEY,
//        	    TITLE VARCHAR2(120) NOT NULL,
//        	    AUTHOR_ID VARCHAR2(40) NOT NULL,
//        	    PHONE VARCHAR2(20),
//        	    START_TIME DATE NOT NULL,
//        	    END_TIME DATE NOT NULL,
//        	    CONTENT CLOB NOT NULL,
//        	    REGION VARCHAR2(40) NOT NULL,
//        	    CATEGORY VARCHAR2(40) NOT NULL,
//        	    OFFER_DATE DATE NOT NULL,
//        	    PROCESS_STATUS VARCHAR2(20) DEFAULT 'REQUESTED' NOT NULL,
//        	    CREATED_AT DATE DEFAULT SYSDATE
//        	);        
        List<VolOffer> list = new ArrayList<>();
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                VolOffer volOffer = new VolOffer();
                volOffer.setOfferNo(rs.getInt("POST_ID"));
                volOffer.setTitle(rs.getString("TITLE"));
                volOffer.setContent(rs.getString("CONTENT"));
                volOffer.setWriter(rs.getString("AUTHOR_ID"));
                // 필요한 다른 컬럼들도 여기에 추가해주세요.

                list.add(volOffer);
            }
        } finally {
            DBUtil.close(con, pstmt, rs);
        }
        
        return list;
    }
}