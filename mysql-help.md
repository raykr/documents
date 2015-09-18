1. mysql登录（有密码的情况）：
```
mysql -h主机地址 -u用户名 －p用户密码
``` 
例如`mysql -u root -p`

2. 显示所有数据库：`show databases;`

3. 显示某个数据库所有表： 
```
use mysql;
show tables;
```