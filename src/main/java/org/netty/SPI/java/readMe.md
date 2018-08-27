针对同一个接口做出不同的实现，Java的SPI机制为某个接口寻找服务实现<br>
1.在classPath下的META-INF/services/目录下创建一个以服务接口命名的文件<br>
2.JDK查找服务的实现的工具类:java.util.ServiceLoader<br>
3.Service的具体实现类必须有一个不带参数的构造方法<br>

https://blog.csdn.net/top_code/article/details/51934459

