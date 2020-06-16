package com.board.notice.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 회원 정보 Mapper
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@Mapper
public interface UserMapper {

    /**
     * 회원 ID로 정보 조회
     *
     * @param id : 회원 ID
     * @return map
     */
    Map<String, Object> findById(String id);
}
