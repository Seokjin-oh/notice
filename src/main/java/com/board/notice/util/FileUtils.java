package com.board.notice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * File 저장 유틸
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-14
 */
@Component
public class FileUtils {

    // application.properties 값
    @Value("${notice.file.path}")
    private String uploadDir;

    /**
     * 파일을 uploadDir에 생성
     *
     * @param file the file
     * @return map
     * @throws Exception the exception
     */
    public Map<String, Object> fileUpload(MultipartFile file) throws Exception {
        Map<String, Object> fileMap = new HashMap<>();

        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
        try {
            //Dir 생성
            if(!Files.exists(Paths.get(uploadDir))) {
                Files.createDirectory(Paths.get(uploadDir));
            }
            // 파일 copy
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("file error"); // TODO - 좀 더 상세한 Exception 처리 해야됨
        }

        fileMap.put("fileName", file.getOriginalFilename()); //파일 이름
        fileMap.put("path", copyOfLocation.toString()); // 파일 경로

        return fileMap;
    }
}
