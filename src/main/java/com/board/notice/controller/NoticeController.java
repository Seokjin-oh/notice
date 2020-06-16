package com.board.notice.controller;

import com.board.notice.security.CustomUser;
import com.board.notice.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 공지사항 관련 API
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    private NoticeService noticeService;

    /**
     * 공지사항 목록
     *
     * @param pageNo : 페이지 번호 - default: 1
     * @return notices
     */
    @GetMapping("/list.json")
    public Map<String, Object> getNotices(@RequestParam(defaultValue = "1") int pageNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("notiTotalCount", noticeService.getNoticeCount());
        resultMap.put("notiList", noticeService.getNotices(pageNo));
        resultMap.put("code", "1");
        resultMap.put("msg", "");
        return resultMap;
    }

    /**
     * 공지사항 상세
     *
     * @param noticeKey : 공지사항 key
     * @return notice
     */
    @GetMapping("/{noticeKey}/detail.json")
    public Map<String, Object> getNotice(@PathVariable int noticeKey) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("notice", noticeService.getNotice(noticeKey));
        resultMap.put("code", "1");
        resultMap.put("msg", "");
        return resultMap;
    }

    /**
     * 공지 사항 등록
     *
     * @param title    : 제목
     * @param contents : 내용
     * @param files    : 첨부파일
     * @return map
     */
    @PostMapping("/reg.json")
    public Map<String, Object> addNotice(
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam("files") MultipartFile[] files) {

        Map<String, Object> resultMap = new HashMap<>();

        //로그인 사용자의 정보
        CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            int noticeKey = noticeService.addNotice(user.getUserKey(), title, contents, files);
            resultMap.put("code", "1");
            resultMap.put("noticeKey", noticeKey);
            resultMap.put("msg", "");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", "99");
            resultMap.put("msg", "file upload error!");
        }

        return resultMap;
    }

    /**
     * 공지사항 수정
     * 첨부파일 저장된 것은 삭제할 때 removeFileKey 에 저장하여 DB 삭제
     * 새로운 파일은 files에 담아 저장
     *
     * @param noticeKey       : 공지사항 key
     * @param title           : 제목
     * @param contents        : 내용
     * @param removeFileKey : 첨부파일 NoticeFileKey list
     * @param files           : 새로운 첨부파일
     * @return map
     */
    @PostMapping("/{noticeKey}/update.json")
    public Map<String, Object> modifyNotice(
            @PathVariable("noticeKey") int noticeKey,
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam("removeFileKey") int[] removeFileKey,
            @RequestParam("files") MultipartFile[] files) {

        Map<String, Object> resultMap = new HashMap<>();

        //로그인 사용자의 정보
        CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            noticeService.modifyNotice(noticeKey, user.getUserKey(), title, contents, removeFileKey, files);
            resultMap.put("code", "1");
            resultMap.put("msg", "");
            resultMap.put("noticeKey", noticeKey);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", "99");
            resultMap.put("msg", "file upload error!");
        }

        return resultMap;
    }

    /**
     * 공지사항 삭제 및 첨부파일 목록 삭제
     *
     * @param noticeKey : 공지사항 key
     */
    @DeleteMapping("/{noticeKey}")
    public Map<String, Object> deleteNotice(@PathVariable("noticeKey") int noticeKey) {
        Map<String, Object> resultMap = new HashMap<>();

        //로그인 사용자의 정보
        CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        noticeService.removeNotice(noticeKey, user.getUserKey());

        resultMap.put("code", "1");
        resultMap.put("noticeKey", noticeKey);

        return resultMap;
    }

}
