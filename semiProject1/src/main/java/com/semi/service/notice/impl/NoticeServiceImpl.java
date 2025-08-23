package com.semi.service.notice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.semi.common.Search;
import com.semi.domain.Notice;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.dao.NoticeDao;

/**
 * Notice Service 구현체
 * - 비즈니스 유효성 체크 + DAO 호출
 */
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDao noticeDao = new NoticeDao();

    @Override
    public Map<String, Object> listNotice(Search search) throws Exception {
        System.out.println("[NoticeServiceImpl] listNotice() :: in = " + search);

        // 디폴트/정규화
        int currentPage = search.getCurrentPage() <= 0 ? 1 : search.getCurrentPage();
        int pageSize    = search.getPageSize()    <= 0 ? 10 : search.getPageSize();
        String keyword  = search.getSearchKeyword() == null ? "" : search.getSearchKeyword().trim();

        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow   = currentPage * pageSize;

        int totalCount = noticeDao.count(keyword);
        List<Notice> noticeList = noticeDao.findList(startRow, endRow, keyword);

        Map<String, Object> result = new HashMap<>();
        result.put("noticeList", noticeList);
        result.put("totalCount", totalCount);

        System.out.println("[NoticeServiceImpl] listNotice() :: out size=" + noticeList.size() + ", total=" + totalCount);
        return result;
    }

    @Override
    public Notice getNotice(long noticeId) throws Exception {
        System.out.println("[NoticeServiceImpl] getNotice(" + noticeId + ")");
        if (noticeId <= 0) {
            return null;
        }
        return noticeDao.findById(noticeId);
    }

    @Override
    public long addNotice(Notice notice) throws Exception {
        System.out.println("[NoticeServiceImpl] addNotice() :: in = " + notice);
        if (notice == null) {
            throw new IllegalArgumentException("notice is null");
        }
        if (isBlank(notice.getAuthorId()) || isBlank(notice.getTitle()) || isBlank(notice.getContent())) {
            throw new IllegalArgumentException("필수값(authorId, title, content)이 누락되었습니다.");
        }
        long id = noticeDao.insert(notice);
        System.out.println("[NoticeServiceImpl] addNotice() :: newId = " + id);
        return id;
    }

    private boolean isBlank(String s){
        return s == null || s.trim().isEmpty();
    }
}
