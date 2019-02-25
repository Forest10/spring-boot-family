  
本仓库于 2019-02-25 11:33:25 使用了源码自动生成模板 spring-boot 。详情见template_info.md文件。

------
部署时配置salt(盐)值
为了防止salt(盐)泄露,反解出密码.可以在项目部署的时候使用命令传入salt(盐)值
java -jar -Djasypt.encryptor.password=hahaha xxx.jar

或者在服务器的环境变量里配置,进一步提高安全性
打开/etc/profile文件
vim /etc/profile

文件末尾插入
export JASYPT_PASSWORD=hahaha

编译 
source /etc/profile

运行 
java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
