CREATE USER lemon PASSWORD 'lemon';
CREATE TABLESPACE lemonspace OWNER lemon LOCATION 'E:\\pg\\lemonspace';
CREATE DATABASE lemondb ENCODING 'UTF8' OWNER lemon TABLESPACE lemonspace;
-- 以下脚本需要登录到lemondb后执行
CREATE SCHEMA lemonapp AUTHORIZATION lemon;

GRANT ALL PRIVILEGES ON DATABASE lemondb TO lemon;
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA lemonapp TO lemon;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA lemonapp TO lemon;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA lemonapp TO lemon;
GRANT ALL PRIVILEGES ON SCHEMA lemonapp TO lemon;


-- SHOW search_path;
SELECT current_schema;
SET search_path TO lemonapp;
SELECT current_schema;

CREATE TABLE LEMON_USERS (

)