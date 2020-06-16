package com.board.notice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;


/**
 * The type User service test.
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    /**
     * Gets user id.
     */
    @Test
    public void getUserId() {
        //test 유저
        Map<String, Object> expectedUser = new HashMap<>();
        expectedUser.put("USER_KEY", 1);
        expectedUser.put("USER_ID", "test");
        expectedUser.put("USER_PW", "$2a$10$5IgZ4pXYqr9xFwOqBvpVj.3upaZUag5tXSrGNpV5SNFFyhT/LnBZq");
        expectedUser.put("USER_NAME", "hong");
        expectedUser.put("ROLE", "USER");

        final Map<String, Object> userMap = userService.getUserById("test");
        //USER 확인
        Assertions.assertThat(userMap).isEqualTo(expectedUser);
        //ROLE 확인
        Assertions.assertThat(userMap.get("ROLE").toString()).isEqualTo("USER");

        //admin 유저
        Map<String, Object> expectedAdmin = new HashMap<>();
        expectedAdmin.put("USER_KEY", 2);
        expectedAdmin.put("USER_ID", "admin");
        expectedAdmin.put("USER_PW", "$2a$10$5IgZ4pXYqr9xFwOqBvpVj.3upaZUag5tXSrGNpV5SNFFyhT/LnBZq");
        expectedAdmin.put("USER_NAME", "kim");
        expectedAdmin.put("ROLE", "ADMIN");

        final Map<String, Object> adminMap = userService.getUserById("admin");
        //USER 확인
        Assertions.assertThat(adminMap).isEqualTo(expectedAdmin);
        //ROLE 확인
        Assertions.assertThat(adminMap.get("ROLE").toString()).isEqualTo("ADMIN");

        //존재 않은 USER
        final Map<String, Object> otherMap = userService.getUserById("other");
        Assertions.assertThat(otherMap).isNullOrEmpty();

    }
}
