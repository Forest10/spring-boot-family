# 源码自动生成模板 spring-boot

### 概述

* 模板: spring-boot
* 模板使用时间: 2019-02-25 11:33:25

### Docker
* Image: registry.cn-beijing.aliyuncs.com/rdc-template/spring-boot
* Tag: 3.0
* SHA256: 1f7d69c45529843a76ae849be2e4820e506e94637c1b084035748bc4abbb5bcb

### 用户输入参数
* repoUrl: "git@code.aliyun.com:210-forest10_yunxiao/spring-boot-jasypt.git" 
* needDockerfile: "N" 
* appName: "spring-boot-jasypt" 
* operator: "aliyun_012120" 
* appReleaseContent: "# 
* 请参考: 请参考 
* https://help.aliyun.com/document_detail/59293.html: https://help.aliyun.com/document_detail/59293.html 
* 了解更多关于release文件的编写方式: 了解更多关于release文件的编写方式 
* [NEWLINE][NEWLINE]#: [NEWLINE][NEWLINE]# 
* 构建源码语言类型[NEWLINE]code.language: oracle-jdk1.8[NEWLINE][NEWLINE]# 
* 构建打包使用的打包文件[NEWLINE]build.output: target/spring-boot-jasypt.jar[NEWLINE][NEWLINE]# 
* 应用部署脚本[NEWLINE]deploy.appctl.path: deploy.sh" 

### 上下文参数
* appName: spring-boot-jasypt
* operator: aliyun_012120
* gitUrl: git@code.aliyun.com:210-forest10_yunxiao/spring-boot-jasypt.git
* branch: master


### 命令行
	sudo docker run --rm -v `pwd`:/workspace -e repoUrl="git@code.aliyun.com:210-forest10_yunxiao/spring-boot-jasypt.git" -e needDockerfile="N" -e appName="spring-boot-jasypt" -e operator="aliyun_012120" -e appReleaseContent="# 请参考 https://help.aliyun.com/document_detail/59293.html 了解更多关于release文件的编写方式 [NEWLINE][NEWLINE]# 构建源码语言类型[NEWLINE]code.language=oracle-jdk1.8[NEWLINE][NEWLINE]# 构建打包使用的打包文件[NEWLINE]build.output=target/spring-boot-jasypt.jar[NEWLINE][NEWLINE]# 应用部署脚本[NEWLINE]deploy.appctl.path=deploy.sh"  registry.cn-beijing.aliyuncs.com/rdc-template/spring-boot:3.0

