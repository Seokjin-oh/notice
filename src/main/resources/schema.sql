DROP TABLE IF EXISTS USER_TB;

CREATE TABLE USER_TB (USER_KEY INT PRIMARY KEY AUTO_INCREMENT, USER_ID VARCHAR, USER_PW VARCHAR, USER_NAME VARCHAR, ROLE VARCHAR);

INSERT INTO USER_TB (USER_ID, USER_PW, USER_NAME, ROLE) VALUES ('test', '$2a$10$5IgZ4pXYqr9xFwOqBvpVj.3upaZUag5tXSrGNpV5SNFFyhT/LnBZq', 'hong', 'USER');
INSERT INTO USER_TB (USER_ID, USER_PW, USER_NAME, ROLE) VALUES ('admin', '$2a$10$5IgZ4pXYqr9xFwOqBvpVj.3upaZUag5tXSrGNpV5SNFFyhT/LnBZq', 'kim', 'ADMIN');


DROP TABLE IF EXISTS NOTICE_TB;

CREATE TABLE NOTICE_TB (NOTICE_KEY INT PRIMARY KEY AUTO_INCREMENT, TITLE VARCHAR, CONTENTS VARCHAR, USER_KEY INT, REG_DATE TIMESTAMP, UPDATE_DATE TIMESTAMP);

INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title1', 'content1', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title2', 'content2', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title3', 'content3', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title4', 'content4', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title5', 'content5', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title6', 'content6', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title7', 'content7', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title8', 'content8', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title9', 'content9', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title10', 'content10', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title11', 'content11', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title12', 'content12', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title13', 'content13', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title14', 'content14', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title15', 'content15', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title16', 'content16', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title17', 'content17', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title18', 'content18', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title19', 'content19', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO NOTICE_TB (TITLE, CONTENTS, USER_KEY, REG_DATE, UPDATE_DATE) VALUES ('title20', 'content20', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


DROP TABLE IF EXISTS NOTICE_FILE_TB;
CREATE TABLE NOTICE_FILE_TB (NOTICE_FILE_KEY INT PRIMARY KEY AUTO_INCREMENT, NOTICE_KEY INT, FILE_NAME VARCHAR, PATH VARCHAR, REG_DATE TIMESTAMP);

