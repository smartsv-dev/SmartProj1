
-- Sequence: seq01_user_id
-- CREATE SEQUENCE seq01_user_id;

CREATE SEQUENCE seq01_user_id
  INCREMENT 1
  MINVALUE 100
  MAXVALUE 999999
  START 100
  CACHE 1;

COMMIT;