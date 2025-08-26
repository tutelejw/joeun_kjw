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


public class VolOfferDao {
	
	///Field 
	
	///Constructor
	public VolOfferDao() {
	}

	///Method
	public void insertVolOffer(VolOffer volOffer) throws Exception {
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		System.out.println("VolOfferDao - insertVolOffer 메서드 authorId는 세션에서 가져오게 수정 해야함.");
		Connection con = DBUtil.getConnection();
		
	    String sql = "INSERT INTO volunteer (volunteerid, authorid, title, content, phone, region, category, starttime, endtime, status, flag, createdat) "
                + "VALUES (seq_volunteer.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("insertVolOffer : " + sql);
		PreparedStatement pStmt = con.prepareStatement(sql);
        // 파라미터 설정
		 // volunteerid 는 seq_volunteer.NEXTVAL 쿼리에서 자동 순차 입력됨
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

	public VolOffer findVolOffer(Long postId) throws Exception {    //DetailVolOffer - 
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
			volOffer.setPostId(rs.getLong("volunteerid"));
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

	public Map<String , Object> getVolOfferList(Search search,String region) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT VOLUNTEERID, AUTHORID, '(' || CATEGORY || ')' || TITLE AS CAT_TITLE, \r\n"
				+ "	CREATEDAT, STARTTIME, ENDTIME,\r\n"
				+ " REGION, STATUS, FLAG \r\n"
				+ " FROM VOLUNTEER "
				+ " where flag = 'o' ";
		

		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += "  or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
//				sql += " WHERE flag = 'o' or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
//		        hasWhereClause = true; // ✅ 이거 추가
//		        System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
//		        System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += "  or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
//				sql += " WHERE flag = 'o' or 1010='" + search.getSearchKeyword()+"'";//where 1010은 임시값-조건없어서 조회되게 만들어놓음
//		        hasWhereClause = true; // ✅ 이거 추가
//		        System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
//		        System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
			}
		}
		boolean hasWhereClause = false;
		
		// region 조건 추가
		System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
		System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);
		System.out.println("hasWhereClause 값 확인 : " + hasWhereClause);		
		if (region != null && !region.isEmpty()) {
			if (hasWhereClause) {
				sql += " AND REGION = '" + region + "'";
			} else {
				sql += " AND REGION = '" + region + "'";
//				sql += " WHERE REGION = '" + region + "'";
				hasWhereClause = true;
			}
			System.out.println("Region 조건 추가됨: " + region);
		}
		
		sql += " order by VOLUNTEERID desc";
		System.out.println("VolOfferDAO::Original SQL :: " + sql);

		System.out.println("searchCondition: " + search.getSearchCondition());
		System.out.println("searchKeyword: " + search.getSearchKeyword());
		System.out.println("region: " + region);
		System.out.println("최종 SQL (페이징 전): " + sql);
		
		System.out.println("=== 페이징 디버깅 ===");
		System.out.println("currentPage: " + search.getCurrentPage());
		System.out.println("pageSize: " + search.getPageSize());
		System.out.println("makeCurrentPageSql :: " + sql);
		
		//==> TotalCount GET
		System.out.println("getTotalCount 이전 로그 디버깅합니다.");
		System.out.println("getTotalCount 이전 로그 디버깅합니다.");
		System.out.println("getTotalCount 이전 로그 디버깅합니다.");
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
		System.out.println("VolOfferDao - updateVolOffer 메서드 진행중.");
		System.out.println("VolOfferDao - updateVolOffer 메서드 진행중.");
		System.out.println("VolOfferDao - updateVolOffer 메서드 진행중.");
		System.out.println("VolOfferDao - updateVolOffer 메서드 진행중.");

		Connection con = DBUtil.getConnection();

		String sql = 	"update volunteer set \r\n"
				+ "  title           = ? \r\n"
				+ ", content    = ? \r\n"
				+ ", phone      = ? \r\n"
				+ ", category  = ? \r\n"
				+ ", starttime  = ? \r\n"
				+ ", endtime   = ? \r \n"
				+ ", status      = ? \r\n"
				+ ", flag           = ? \r\n"
				+ " where volunteerid = ? and authorid=?  and flag = 'o' "; // 물음표 10개
	
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, vo.getTitle());      // title
		pStmt.setString(2, vo.getContent());    // content
		pStmt.setString(3, vo.getPhone());      // phone
		pStmt.setString(4, vo.getCategory());   // category
		pStmt.setTimestamp(5, Timestamp.valueOf(vo.getStartTime()));  // starttime
		pStmt.setTimestamp(6, Timestamp.valueOf(vo.getEndTime()));    // endtime
		pStmt.setString(7, vo.getStatus());     // status
		pStmt.setString(8, vo.getOfferFlag());       // flag
		pStmt.setLong(9, vo.getPostId());  // volunteerid
		pStmt.setString(10, vo.getAuthorId());  // authorid

		// 쿼리 실행
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
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