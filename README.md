用到session共享，学习下，发现和网上的资源有点不一样，进行适当修改
带图详情:http://note.youdao.com/noteshare?id=397c26598c02ba052460190b3b175ecd&sub=D6840E321B11458388BAA9F3EA8BACA7
地址：https://github.com/xzl1991/TestRedisSession.git
原文地址：http://www.cnblogs.com/chenpi/p/6347299.html
一。springboot 项目 注意事项
启动报错问题：

这一行要注释

二。springboot redis 配置
在 yml 文件 加入即可


三。设置 redis 配置
config set stop-writes-on-bgsave-error no 
四。通过 redis session共享
查看redis 上的 详细信息
 HGETALL spring:session:sessions:ffce8df0-d540-44b7-8dd0-c6c0d2c1d7f8
 
五.尝试生成ext 
 



































