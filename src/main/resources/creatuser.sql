
 insert into mysql.user(Host,User,Password) values("localhost","mylove",password("123456"));
 flush privileges;
 create database mylove;
 grant all privileges on mylove.* to mylove@localhost identified by '123456';
 flush privileges;
 ---GRANT USAGE ON *.* TO 'appmonitor'@'localhost' IDENTIFIED BY '123456' WITH GRANT OPTION;