[TOC]
# javaweb
记录javaweb学习过程中的代码
## 说明
该代码暂时从day14开始

## day14_customer
一个关于客户管理的页面，实现使用mysql实现客户的CURD。  
实现了查看客户时的分页功能，并使用自定义EL函数实现字符串超出指定范围时省略号替代。
## day12_upload
文件上传代码，但是不太完善
## day15
内容包括:  
- 向mysql数据库中插入大文本、二进制(图片，视频等)数据。  
    - mysql的大文本数据类型**text**，二进制类型**blob**。(也有别的类型，即类型存储的大，可查API)  
    - 遇到的问题:当对大文本和二进制进行设置时，需要调用`setCharacterStream(int parameterIndex,Reader reader,int length)`方法，还有一个`setCharacterStream(int parameterIndex,Reader reader,long length)`。只不过后者的方法需要在jdk1.6之后才能够使用，但是我在编写时调用了后者，确报出 `java.lang.AbstractMethodError:com.mysql.jdbc.PreparedStatement.setCharacterStream(ILjava/io/Reader;`的错误，进行修改后还是调用该方法，并将第三个参数强转成int类型就好通过了，具体代码可看[Demo1]，Demo2.该问题原因还不清楚
- 批量处理sql语句
    - statement批处理:
        - 优点:可以向数据库中发送多条不同的sql语句
        - 缺点:
           1. sql语句没有编译
           2. 当向数据库发送多条语句相同，但参数不同的sql语句时需要重复写很多条相同的sql语句
    - preparedstatement批处理:
        - 优点:发送的是预编译后的sql语句，执行效率高
        - 缺点:只能应用在sql语句相同，但参数不同的批处理中。因此此种形式的批处理经常用于在同一个表中批量插入数据或批量更新表的数据。
- 获得数据库自动生成的主键
    - 调用`rs = stmt.getGeneratedKeys()`方法就行，具体看Demo4.
## day16
- 事务:在jdbc中使用事务
    - 当JDBC程序向数据库获得一个`Connection`对象时，默认情况下这个`Connection`对象会自动向数据库提交在它上面发送的SQL语句，若想关闭这种默认提交方式，让多条SQL在一个事务中执行，可使用下列语句:
    - `Connection.setAutoCommit(false);`**相当于数据库中的`start transaction`语句，开启事务**
    - `Connection.rollback();`**相当于数据库中的`rollback`语句，回滚操作，通常设置在`catch`块中**
    - `Connection.commit();`**相当于数据库中的`commit`语句，提交操作**
- 事务实例:比如银行转账，当a向b转账时，如果a转的时候，程序出错，导致a的钱已经扣除，而b的钱还未到帐。使用事务就可以阻止这种事情发生，将两条sql语句编写在一个事务中，如果发生错误，将会回滚，使a的钱也没有扣除  
**详细代码请查看day16 Demo1、Demo2**  
- 事务的特性:
    - **原子性**:原子性是指事物是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
    - **一致性**:事务必须使数据库从一个一致性状态变换到另一个一致性状态。
    - **隔离性**:事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
    - **持久性**:持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响。  
- 事务的隔离级别:多个线程开启各自事务操作数据库中数据时，数据库系统要负责隔离操作，以保证各个线程在获取数据时的准确性。不考虑隔离性，可能引发的问题:**脏读**、**不可重复读**、**虚读**。
- mysql共定义了四种隔离级别:
    - **Serializable**:可避免脏读、不可重复读、虚读情况发生。设置该种级别性能会降低，在做统计时就可以使用该级别。
    - **Repeatable read**:可以避免脏读、不可重复读情况的发生。
    - **Read committed**:可避免脏读情况发生(读已提交)。
    - **Read uncommitted**:最低级别，以上情况均无法保证。(读未提交)
    - **注意**:Oracle数据库仅支持 **Serializable**、 **Read committed**这两种级别。
- `set transaction isolation level`设置事务隔离级别。(数据库语句)
- `select @@tx_isolation`查询当前事务隔离级别(数据库语句)
- 演示不同隔离级别下的并发问题

1.当把事务的隔离级别设置为read uncommitted时，会引发脏读、不可重复读和虚读


A窗口
```
set transaction isolation level  read uncommitted;
start transaction;
select * from account;
```
-----发现a帐户是1000元，转到b窗口
```
select * from account
```
-----发现a多了100元，这时候a读到了b未提交的数据（脏读）


B窗口
```
start transaction;
update account set money=money+100 where name='aaa';
```
-----不要提交，转到a窗口查询


2.当把事务的隔离级别设置为read committed时，会引发不可重复读和虚读，但避免了脏读

A窗口
```
set transaction isolation level  read committed;
start transaction;
select * from account;
```
-----发现a帐户是1000元，转到b窗口
```
select * from account;
```
-----发现a帐户多了100,这时候，a读到了别的事务提交的数据，两次读取a帐户读到的是不同的结果（不可重复读）


B窗口
```
start transaction;
update account set money=money+100 where name='aaa';
commit;
```
-----转到a窗口


3.当把事务的隔离级别设置为repeatable read(mysql默认级别)时，会引发虚读，但避免了脏读、不可重复读

A窗口
```
set transaction isolation level repeatable read;
start transaction;
select * from account;
```
----发现表有4个记录，转到b窗口
```
select * from account;
```
----可能发现表有5条记如，这时候发生了a读取到另外一个事务插入的数据（虚读）


B窗口
```
start transaction;
insert into account(name,money) values('ggg',1000);
commit;
```
-----转到 a窗口

4.当把事务的隔离级别设置为Serializable时，会避免所有问题
A窗口
```
set transaction isolation level Serializable;
start transaction;
select * from account;
```
-----转到b窗口

B窗口
```
start transaction;
insert into account(name,money) values('ggg',1000);
```
-----发现不能插入，只能等待a结束事务才能插入

- **连接池**:使用数据库连接池优化程序性能。
    - 应用程序直接获取数据库链接的缺点:用户每次请求都需要向数据库获得链接，而数据库创建链接通常需要消耗相对较大的资源，创建时间也较长。假设网站一天10万访问量，数据库服务器就需要创建10万次连接，极大的浪费数据库资源，并极易造成数据库服务器内存溢出。
    - 使用连接池一次性从数据库中获得多个连接，放到连接池中，当用户需要连接时，从连接池中获取链接，并删除连接池中的当前连接，当用户`conn.close()`时，又将该链接放回至连接池中。
    - 编写连接池需实现`java.sql.DataSource`接口。`DataSorce`接口中定义了两个重载的getConnection方法:
        - `Connection getConnection()`
        - `Connection getConnection(String username,String password)`
    - 由于自己编写连接池很麻烦，所以现在很多WEB服务器都提供了`DataSource`的实现，即连接池的实现，也有一些开源组织提供了数据源的独立实现:
        - **DBCP数据库连接池**:应用程序需要导入相关jar包，**注意**dbcp2和dbcp1对jdk版本要求不一样。如果导入的是dbcp2，那么还需要comms-logging包和commons-pool2包，同时mysql的包jar包需要在mysql-connector5.1以上版本，否则运行时会报错。**如果mysql的jar包是5.0.4版本，那么对应的dbcp和pool版本应该为1.4和1.6版本，并且不需要导入logging包**。还需要注意的是，当使用的mysql版本是5.1.44时，**需要在配置文件中的url写成`url=jdbc:mysql://localhost:3306/day16?useSSL=false`**，否则也会出错。
        - 使用配置文件方法，使用dbcp连接池:  
	
                ```
                 static{
		          try{
			         InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");	//读取DBCP的配置文件
			         Properties prop = new Properties();
			         prop.load(in);
			
			         BasicDataSourceFactory factory = new BasicDataSourceFactory();		//创建工厂
			         Datasource ds = factory.createDataSource(prop);
			
		          }catch(Exception e){
			         throw new ExceptionInInitializerError(e);
		              }
	               }
	               //从连接池中获取链接
	               for(int i = 0;i < 20;i++){
	                   Connection conn = ds.getConnection();
	                   System.out.println(conn.hashCode());
	                    if(i % 2){
	                       conn.close();
	                   }
	                }
                ```
               
**DBCP的代码请参照jdbcUtils_DBCP**
