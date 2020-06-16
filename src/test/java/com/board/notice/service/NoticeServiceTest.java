package com.board.notice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * The type Notice service test.
 */
@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    /**
     * Gets notice count.
     */
    @Test
    public void getNoticeCount() {
        //기본적으로 H2 DB에 공지사항 20건 저장 되어있음
        final int noticeCount = noticeService.getNoticeCount();
        Assertions.assertThat(noticeCount).isEqualTo(20);
    }

    /**
     * Gets notices.
     */
    @Test
    public void getNotices() {
        //페이지당 10개씩 불렁롬
        final List<Map<String, Object>> noticesByRowCount10 = noticeService.getNotices(1);
        Assertions.assertThat(noticesByRowCount10.size()).isEqualTo(10);

        //공지사항 총 20개 중 3번째 페이지는 null
        final List<Map<String, Object>> noticesEmpty = noticeService.getNotices(3);
        Assertions.assertThat(noticesEmpty).isNullOrEmpty();
    }

    /**
     * Gets notice.
     */
    @Test
    public void getNotice() {        
        //공지 상세
        final Map<String, Object> notice = noticeService.getNotice(1);
        //notice key
        Assertions.assertThat((int)notice.get("NOTICE_KEY")).isEqualTo(1);
        //notice title
        Assertions.assertThat(notice.get("TITLE").toString()).isEqualTo("title1");
        //notice contents
        Assertions.assertThat(notice.get("CONTENTS").toString()).isEqualTo("content1");
        //user key
        Assertions.assertThat(notice.get("USER_ID").toString()).isEqualTo("admin");

        //없는 공지 상세
        final Map<String, Object> emptyNotice = noticeService.getNotice(99);
        Assertions.assertThat(emptyNotice).isNullOrEmpty();
    }

    /**
     * Add notice.
     *
     * @throws Exception the exception
     */
    @Test
    public void addNotice() throws Exception {
        //파일
        MockMultipartFile file = new MockMultipartFile("file1", "test1.pdf", MediaType.APPLICATION_PDF_VALUE,"test1".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file2 = new MockMultipartFile("file2", "test2.pdf", MediaType.APPLICATION_PDF_VALUE,"test2".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file3 = new MockMultipartFile("file3", "test3.pdf", MediaType.APPLICATION_PDF_VALUE,"test3".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile[] files = {file, file2, file3};

        //공지 저장
        final int noticeKey = noticeService.addNotice(2, "title21", "content21", files);
        //저장된 공지 조회
        final Map<String, Object> notice = noticeService.getNotice(noticeKey);
        //notice title
        Assertions.assertThat(notice.get("TITLE").toString()).isEqualTo("title21");
        //notice contents
        Assertions.assertThat(notice.get("CONTENTS").toString()).isEqualTo("content21");
        //user key
        Assertions.assertThat(notice.get("USER_ID").toString()).isEqualTo("admin");
        
        //공지에 포함된 파일 목록
        List<Map<String, Object>> fileList = (List<Map<String, Object>>)notice.get("files");
        //fileList 갯수
        Assertions.assertThat(fileList.size()).isEqualTo(3);
        //file notice key
        Assertions.assertThat((int)fileList.get(0).get("NOTICE_KEY")).isEqualTo(noticeKey);
        Assertions.assertThat(fileList.get(0).get("FILE_NAME").toString()).isEqualTo("test1.pdf");
        Assertions.assertThat(fileList.get(0).get("PATH").toString()).isEqualTo("\\temp\\notice\\test1.pdf");

    }

    /**
     * Modify notice.
     *
     * @throws Exception the exception
     */
    @Test
    public void modifyNotice() throws Exception {
        //기존 파일
        MockMultipartFile file = new MockMultipartFile("file1", "test1.pdf", MediaType.APPLICATION_PDF_VALUE,"test1".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file2 = new MockMultipartFile("file2", "test2.pdf", MediaType.APPLICATION_PDF_VALUE,"test2".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file3 = new MockMultipartFile("file3", "test3.pdf", MediaType.APPLICATION_PDF_VALUE,"test3".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile[] files = {file, file2, file3};

        //공지 저장
        final int noticeKey = noticeService.addNotice(2, "title21", "content21", files);
        //공지 상세
        final Map<String, Object> beforeNotice = noticeService.getNotice(noticeKey);
        //공지 파일
        final List<Map<String, Object>> beforeFiles = (List<Map<String, Object>>) beforeNotice.get("files");

        Assertions.assertThat(beforeFiles.size()).isEqualTo(3);
        //삭제 할 파일 notice doc key, file1, file3을 지움
        int[] beforeMoveFiles = {(int)beforeFiles.get(0).get("NOTICE_FILE_KEY"),(int)beforeFiles.get(2).get("NOTICE_FILE_KEY")};
        
        //새로운 파일
        MockMultipartFile newFile = new MockMultipartFile("newfile", "newfile.pdf", MediaType.APPLICATION_PDF_VALUE,"newfile".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile[] newFiles = {newFile};
        

        final int modifyNotice = noticeService.modifyNotice(noticeKey, 2, "title_update21", "content_update21", beforeMoveFiles, newFiles);

        final Map<String, Object> notice = noticeService.getNotice(modifyNotice);
        //notice title
        Assertions.assertThat(notice.get("TITLE").toString()).isEqualTo("title_update21");
        //notice contents
        Assertions.assertThat(notice.get("CONTENTS").toString()).isEqualTo("content_update21");
        //user key
        Assertions.assertThat(notice.get("USER_ID").toString()).isEqualTo("admin");

        //공지에 포함된 파일 목록
        List<Map<String, Object>> newfileList = (List<Map<String, Object>>)notice.get("files");
        //fileList 갯수
        Assertions.assertThat(newfileList.size()).isEqualTo(2);
        ////첫번째 파일
        Assertions.assertThat((int)newfileList.get(0).get("NOTICE_KEY")).isEqualTo(modifyNotice);
        Assertions.assertThat(newfileList.get(0).get("FILE_NAME").toString()).isEqualTo("test2.pdf");
        Assertions.assertThat(newfileList.get(0).get("PATH").toString()).isEqualTo("\\temp\\notice\\test2.pdf");
        //두번째 파일
        Assertions.assertThat((int)newfileList.get(1).get("NOTICE_KEY")).isEqualTo(modifyNotice);
        Assertions.assertThat(newfileList.get(1).get("FILE_NAME").toString()).isEqualTo("newfile.pdf");
        Assertions.assertThat(newfileList.get(1).get("PATH").toString()).isEqualTo("\\temp\\notice\\newfile.pdf");

    }

    /**
     * Delete notice.
     */
    @Test
    public void deleteNotice() throws Exception {

        //기존 파일
        MockMultipartFile file = new MockMultipartFile("file1", "base.pdf", MediaType.APPLICATION_PDF_VALUE,"base".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile[] files = {file};
        //공지사항 등록
        final int addNoticeKey = noticeService.addNotice(2, "removeTitle", "removeContent", files);
        //공지 총 갯수

        final Map<String, Object> notice = noticeService.getNotice(addNoticeKey);
        List<Map<String, Object>> fileList = (List<Map<String, Object>>)notice.get("files");

        //공지 확인
        Assertions.assertThat(notice.get("TITLE").toString()).isEqualTo("removeTitle");
        //파일 확인
        Assertions.assertThat(fileList.get(0).get("FILE_NAME").toString()).isEqualTo("base.pdf");

        //공지 삭제
        final int removeNoticeKey = noticeService.removeNotice(addNoticeKey, 2);

        final Map<String, Object> removeNotice = noticeService.getNotice(removeNoticeKey);

        //공지 삭제 확인
        Assertions.assertThat(removeNotice).isNullOrEmpty();
    }
}
