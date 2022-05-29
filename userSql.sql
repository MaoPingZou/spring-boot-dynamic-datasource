-- test08.`user` DDL
-- test09 表结构与test08一致

CREATE TABLE `user`
(
    `id`       int NOT NULL COMMENT 'id',
    `username` varchar(10) DEFAULT NULL,
    `age`      int         DEFAULT NULL
) ENGINE = InnoDB
  CHARSET = utf8 comment = '用户';

-- init data

INSERT INTO test08.`user` (id, username, age)
VALUES (1, 'billy', 25),
       (2, 'andy', 23);

INSERT INTO test09.`user` (id, username, age)
VALUES (1, 'bob', 28),
       (2, 'steve', 27),
       (3, 'gates', 27);


