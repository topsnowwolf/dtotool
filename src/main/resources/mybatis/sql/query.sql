-- 查询mysql所有库
show databases;
select * from information_schema.SCHEMATA;
-- 查询mysql一个库的所有表
show tables;
select table_name from information_schema.tables where table_schema='库名称';
-- 查询一个表的结构
desc tb_order;
select * from information_schema.columns a where  a.table_name = '表名' ;
