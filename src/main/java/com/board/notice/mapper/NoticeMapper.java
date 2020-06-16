package com.board.notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

/**
 * 공지사항 및 첨부파일 Mapper
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@Mapper
public interface NoticeMapper {

    /**
     * 공지사항 목록 총 갯수
     *
     * @return int
     */
    int findCount();

    /**
     * 공지사항 목록 조회
     *
     * @param offset   : row 시작 수
     * @param rowCount : row 갯수
     * @return list
     */
    List<Map<String, Object>> findAll(@Param("offset") int offset, @Param("rowCount") int rowCount);

    /**
     * 공지사항 상세
     *
     * @param noticeKey : 공지사항 key
     * @return map
     */
    Map<String, Object> findOne(@Param("noticeKey") int noticeKey);

    /**
     * 공지사항에 등록된 첨부파일 목록
     *
     * @param noticeKey : 공지사항 key
     * @return list
     */
    List<Map<String, Object>> findAllByFile(@Param("noticeKey") int noticeKey);

    /**
     * 공지사항 등록
     *
     * @param param : userKey - 회원 key, title - 제목, contents - 내용
     */
    void saveNotice(Map<String, Object> param);

    /**
     * 공지사항 첨부파일 등록
     *
     * @param noticeKey : 공지사항 key
     * @param fileName  : 첨부파일 이름
     * @param path      : 첨부파일 경로
     */
    void saveNoticeFilePath(@Param("noticeKey") int noticeKey, @Param("fileName") String fileName, @Param("path") String path);

    /**
     * 공지사항 수정
     *
     * @param param : noticeKey - 공지사항 key, userKey - 회원 key, title - 제목, contents - 내용
     */
    void updateNotice(Map<String, Object> param);

    /**
     * 공지사항 첨부파일 삭제
     *
     * @param noticeFileKeys : noticeDocKey 목록
     */
    void deleteNoticeFilePath(@Param("noticeFileKeys") int[] noticeFileKeys);

    /**
     * 공지사항 삭제
     *
     * @param noticeKey : 공지사항 key
     * @param userKey : 회원 key
     */
    void deleteNotice(@Param("noticeKey") int noticeKey, int userKey);

    /**
     * 공지사항 첨부파일 삭제
     *
     * @param noticeKey : 공지사항 key
     */
    void deleteAllNoticeFile(@Param("noticeKey") int noticeKey);
}
