package com.semi.service.volOffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.semi.common.Search;
import com.semi.common.util.DBUtil;
import com.semi.domain.VolOffer;


public class VolOfferDao_ {
	
	///Field 
	
	///Constructor
	public VolOfferDao_() {
	}

	///Method
	public void insertVolOffer(VolOffer volOffer) throws Exception {
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		Connection con = DBUtil.getConnection();
		
	    String sql = "INSERT INTO volunteer (volunteerid, authorid, title, content, phone, region, category, starttime, endtime, status, flag, createdat) "
                + "VALUES (seq_volunteer.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
        // 파라미터 설정
		pStmt.setString(1, volOffer.getAuthorId());  // authorid
		pStmt.setString(2, volOffer.getTitle());     // title
		pStmt.setString(3, volOffer.getContent());   // content
		pStmt.setString(4, volOffer.getPhone());     // phone
		pStmt.setString(5, volOffer.getRegion());    // region
		pStmt.setString(6, volOffer.getCategory());  // category
		  // starttime과 endtime을 java.sql.Timestamp로 변환하여 바인딩
	    pStmt.setTimestamp(7, Timestamp.valueOf(volOffer.getStartTime())); // starttime
	    pStmt.setTimestamp(8, Timestamp.valueOf(volOffer.getEndTime()));   // endtime
		pStmt.setString(9, volOffer.getStatus());    // (모집 상태: 모집중/모집완료/봉사완료/만료)
		pStmt.setString(10, volOffer.getOfferFlag());     // flag -> offerFlag (상태 플래그: 'r' 또는 'o')
	    // createdAt을 현재 시간으로 설정(DB default로 입력되나. 가독성을 위헤 추가함..)
	    pStmt.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now())); // createdAt: 현재 시간
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}

	public VolOffer findVolOffer(Long postId) throws Exception {
		System.out.println("VolOfferDao - findVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함..");
		System.out.println("VolOfferDao - findVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함..");
		System.out.println("VolOfferDao - findVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함..");
		Connection con = DBUtil.getConnection();
			
		String sql = 	"SELECT "+
				"volunteerid ,  authorid ,  title , content ,  phone ,  region , category ,starttime, endtime, status, createdat " + 
				" FROM VOLUNTEER WHERE flag = 'o' and volunteerid= ?";
		
		System.out.println("VolOfferDao - findVolOffer : " + sql);
		PreparedStatement pStmt = con.prepareStatement(sql);
	    pStmt.setLong(1, postId);  // postId 는 Long 데이터 타입으로 넘어온다.

		ResultSet rs = pStmt.executeQuery();

		VolOffer volOffer = null;

		while (rs.next()) {
			volOffer = new VolOffer();
			volOffer.setAuthorId(rs.getString("authorid"));
			volOffer.setTitle(rs.getString("title"));
			volOffer.setContent(rs.getString("content"));
			volOffer.setPhone(rs.getString("phone"));
			volOffer.setRegion(rs.getString("region"));
			volOffer.setCategory(rs.getString("category"));
			volOffer.setStartTime(rs.getTimestamp("STARTTIME").toLocalDateTime());
			volOffer.setEndTime(rs.getTimestamp("ENDTIME").toLocalDateTime());
			volOffer.setStatus(rs.getString("status"));
			volOffer.setCreatedAt(rs.getTimestamp("CREATEDAT").toLocalDateTime());
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return volOffer;
	}

	public Map<String , Object> getVolOfferList(Search search, String region) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT VOLUNTEERID, AUTHORID, '(' || CATEGORY || ')' || TITLE AS CAT_TITLE, \r\n"
//				+ "	TO_CHAR(CREATEDAT, 'YYYYMMDDHH24MI') AS CREATEDAT,\r\n"
//				+ " TO_CHAR(STARTTIME, 'YYYYMMDDHH24MI') AS STARTTIME,\r\n"
//				+ " TO_CHAR(ENDTIME, 'YYYYMMDDHH24MI') AS ENDTIME,\r\n"
				+ "	CREATEDAT, STARTTIME, ENDTIME,\r\n"
				+ " REGION, STATUS, FLAG \r\n"
				+ " FROM VOLUNTEER ";
		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE flag = 'o' or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE flag = 'o' or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
			}
		}
		sql += " order by VOLUNTEERID desc";
		System.out.println("VolOfferDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("VolOfferDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<VolOffer> list = new ArrayList<VolOffer>();

		while(rs.next()){
			VolOffer volOffer = new VolOffer();
			volOffer.setPostId(rs.getLong("VOLUNTEERID"));
			volOffer.setAuthorId(rs.getString("AUTHORID"));
			volOffer.setTitle(rs.getString("CAT_TITLE"));
			volOffer.setCreatedAt(rs.getTimestamp("CREATEDAT").toLocalDateTime());
			volOffer.setStartTime(rs.getTimestamp("STARTTIME").toLocalDateTime());
			volOffer.setEndTime(rs.getTimestamp("ENDTIME").toLocalDateTime());
			volOffer.setRegion(rs.getString("REGION"));
			volOffer.setStatus(rs.getString("STATUS")); //모집중/모집완료/봉사완료/만료  *만료=시간 초과로 모집 종료
			volOffer.setOfferFlag(rs.getString("FLAG")); //flag -> offerFlag (상태 플래그: 'r' 또는 'o')
			list.add(volOffer);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	public void updateVolOffer(VolOffer vo) throws Exception {
		System.out.println("VolOfferDao - updateVolOffer 메서드 쿼리 작성 해야함.");
		System.out.println("VolOfferDao - updateVolOffer 메서드 쿼리 작성 해야함.");
		System.out.println("VolOfferDao - updateVolOffer 메서드 쿼리 작성 해야함.");

//		Connection con = DBUtil.getConnection();
//
//		String sql = 	"UPDATE volOffers "+
//								"SET volOffer_name = ?, cell_phone = ? , addr = ? , email = ? "+ 
//								"WHERE volOffer_id = ?";
//		
//		PreparedStatement pStmt = con.prepareStatement(sql);
//		pStmt.setString(1, vo.getVolOfferName());
//		pStmt.setString(2, vo.getPhone());
//		pStmt.setString(3, vo.getAddr());
//		pStmt.setString(4, vo.getEmail());
//		pStmt.setString(5, vo.getVolOfferId());
//		pStmt.executeUpdate();
//		
//		pStmt.close();
//		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("VolOfferDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}