package com.semi.service.notice;

import java.util.Map;

import com.semi.common.Search;
import com.semi.domain.Notice;

/**
 * Notice 도메인 Service 인터페이스
 * - 목록 조회(페이징/검색)
 * - 단건 조회
 * - 등록
 */
public interface NoticeService {

    /**
     * 공지 목록 조회
     * @param search currentPage, pageSize, searchKeyword 사용
     * @return {"noticeList": java.util.List<Notice>, "totalCount": java.lang.Integer}
     * @throws Exception
     */
    Map<String, Object> listNotice(Search search) throws Exception;

    /**
     * 공지 단건 조회
     * @param noticeId 공지 PK
     * @return Notice or null
     * @throws Exception
     */
    Notice getNotice(long noticeId) throws Exception;

    /**
     * 공지 등록
     * @param notice authorId, title, content 필수
     * @return 생성된 noticeId
     * @throws Exception
     */
    long addNotice(Notice notice) throws Exception;
}
