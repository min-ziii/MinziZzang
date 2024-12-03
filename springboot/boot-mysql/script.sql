create user 'springboot'@'localhost' identified by 'java1234';  

grant all privileges on *.* to 'springboot'@'localhost' with grant option;  

--갱신 >> 즉시 적용
flush privileges;

SELECT VERSION();
SELECT User, Host FROM mysql.user WHERE User = 'springboot';

DROP USER 'springboot'@'localhost';

use mysql;