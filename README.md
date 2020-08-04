

### mock sql plus

客户这边的生产环境访问要求非常严格，没有权限是没有通过SQLPLUS访问数据库的，但是有很多时候需要直接查生产的数据，所以自己用JDBC直接手写了一个非常简单的SQL PLUS ， 可以直接通过oracle 驱动在生产的任意一台网络OK的主机上访问数据库（因为客户那边默认主机连数据库是进程发起的行为，也不会被审计）。支持：

* DDL
* DML



### 使用方法

*  将SQLPlus_For_Linux_jar.rar发布至生产主机（需要有JDK，环境变量也要设置好）。

* 解压，直接执行以下命令

  ```
  java -jar SQLPlus_For_Linux.jar
  ```

* 然后输入 tns连接串  用户名 密码 ， 就可以在不通过plsql授权的情况下访问生产数据了。

