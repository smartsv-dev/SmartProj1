

-- Table: t001_user
-- CREATE TABLE t001_user;

CREATE TABLE t001_user
(
  user_id varchar(10) NOT NULL,
  password varchar(100),
  name_kanji varchar(30),
  name_katakana varchar(30),
  gender varchar(1),
  birthday varchar(8),
  joined_date varchar(8),
  email varchar(100),
  address varchar(200),
  tel_no varchar(20),
  kyk_type varchar(1),
  job_stat varchar(1),
  user_role varchar(1) DEFAULT 1,
  del_flg varchar(1) DEFAULT 0,
  insert_date timestamp,
  update_date timestamp,
  CONSTRAINT t001_user_pk_1 PRIMARY KEY (user_id)
)
;

COMMIT;

