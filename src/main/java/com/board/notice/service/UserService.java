package com.board.notice.service;

import com.board.notice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 회원 관련 서비스
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserMapper userMapper;

    /**
     * ID로 회원 검색
     *
     * @param id : 회원 ID
     * @return user by id
     */
    public Map<String, Object> getUserById(String id) {
        return userMapper.findById(id);
    }
}
