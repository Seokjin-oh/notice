package com.board.notice.service;

import com.board.notice.mapper.NoticeMapper;
import com.board.notice.util.FileUtils;
import com.board.notice.util.GlobalSetUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 공지사항 및 첨부파일 관련 서비스
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@Service
@AllArgsConstructor
public class NoticeService {

    private NoticeMapper noticeMapper;
    private FileUtils fileUtils;

    /**
     * 공지사항 목록 총 갯수
     *
     * @return notice count
     */
    public int getNoticeCount() {
        return noticeMapper.findCount();
    }

    /**
     * 공지사항 목록
     *
     * @param pageNo : page 번호
     * @return notices
     */
    public List<Map<String, Object>> getNotices(int pageNo) {
        int rowCount = GlobalSetUtils.NOTICE_ROW_COUNT;
        int offset = (pageNo-1) * rowCount;
        return noticeMapper.findAll(offset, rowCount);
    }

    /**
     * 공지사항 상세
     *
     * @param noticeKey : key 값
     * @return notice
     */
    public Map<String, Object> getNotice(int noticeKey) {
        Map<String, Object> noticeMap = noticeMapper.findOne(noticeKey);
        if(noticeMap != null) {
            //첨부파일 목록
            noticeMap.put("files", noticeMapper.findAllByFile(noticeKey));
        }
        return noticeMap;
    }

    /**
     * 공지사항 등록
     *
     * @param userKey  : 작성자 key
     * @param title    : 제목
     * @param contents : 내용
     * @param files    : 첨부 파일
     * @return the int
     * @throws Exception the exception
     */
    @Transactional
    public int addNotice(int userKey, String title, String contents, MultipartFile[] files) throws Exception {
        List<Map<String, Object>> fileMapList = new ArrayList<>();

        //첨부파일 저장
        for(MultipartFile file : files) {
            fileMapList.add(fileUtils.fileUpload(file));
        }

        //파라미터 map 생성
        Map<String, Object> param = new HashMap<>();
        param.put("userKey", userKey);
        param.put("title", title);
        param.put("contents", contents);

        noticeMapper.saveNotice(param); //공지사항 저장

        int noticeKey = (int)param.get("NOTICE_KEY");
        for (Map<String, Object> fileMap : fileMapList) {
            //첨부파일 저장
            noticeMapper.saveNoticeFilePath(noticeKey, fileMap.get("fileName").toString(), fileMap.get("path").toString());
        }

        return noticeKey;
    }

    /**
     * 공지사항 수정
     *
     * @param noticeKey       : 공지사항 key
     * @param userKey         : 작성자 key
     * @param title           : 제목
     * @param contents        : 내용
     * @param removeFileKey : 첨부파일 삭제 key 목록 - NoticeDocKeys
     * @param files           : 신규 첨부파일 목록
     * @return int
     * @throws Exception the exception
     */
    @Transactional
    public int modifyNotice(int noticeKey, int userKey, String title, String contents, int[] removeFileKey, MultipartFile[] files) throws Exception {
        List<Map<String, Object>> fileMapList = new ArrayList<>();

        //첨부파일 저장
        for(MultipartFile file : files) {
            fileMapList.add(fileUtils.fileUpload(file));
        }

        //파리미터 map
        Map<String, Object> param = new HashMap<>();
        param.put("noticeKey", noticeKey);
        param.put("userKey", userKey);
        param.put("title", title);
        param.put("contents", contents);

        noticeMapper.updateNotice(param);

        //기존 첨부 파일 삭제 처리
        noticeMapper.deleteNoticeFilePath(removeFileKey);

        //새로운 첨부 파일 저장
        for (Map<String, Object> fileMap : fileMapList) {
            noticeMapper.saveNoticeFilePath(noticeKey, fileMap.get("fileName").toString(), fileMap.get("path").toString());
        }

        return noticeKey;
    }

    /**
     * 공지사항 삭제 및 첨부파일 목록 삭제
     *
     * @param noticeKey : 공지사항 key
     * @param userKey : 회원 key
     */
    @Transactional
    public int removeNotice(int noticeKey, int userKey) {
        noticeMapper.deleteNotice(noticeKey, userKey);
        noticeMapper.deleteAllNoticeFile(noticeKey);

        return noticeKey;
    }
}
