- [x] hibernate.cfg.xml配置

```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 指定Hibernate配置文件的DTD信息 -->
<!DOCTYPE hibernate-configuration PUBLIC
	"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- hibernate- configuration是连接配置文件的根元素 -->
<hibernate-configuration>
	<session-factory>
		<!-- 指定连接数据库所用的驱动 -->
		<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
		<!-- 指定连接数据库的url，hibernate连接的数据库名 -->
		<property name="connection.url">jdbc:mysql://localhost/数据库名</property>
		<!-- 指定连接数据库的用户名 -->
		<property name="connection.username">root</property>
		<!-- 指定连接数据库的密码 -->
		<property name="connection.password">32147</property>
		<!-- 指定连接池里最大连接数 -->
		<property name="hibernate.c3p0.max_size">20</property>
		<!-- 指定连接池里最小连接数 -->
		<property name="hibernate.c3p0.min_size">1</property>
		<!-- 指定连接池里连接的超时时长 -->
		<property name="hibernate.c3p0.timeout">5000</property>
		<!-- 指定连接池里最大缓存多少个Statement对象 -->
		<property name="hibernate.c3p0.max_statements">100</property>
		<property name="hibernate.c3p0.idle_test_period">3000</property>
		<property name="hibernate.c3p0.acquire_increment">2</property>
		<property name="hibernate.c3p0.validate">true</property>
		<!-- 指定数据库方言 -->
		<property name="dialect">org.hibernate.dialect.MySQLInnoDBDialect</property>
		<!-- 根据需要自动创建数据表 -->
		<property name="hbm2ddl.auto">update</property>
		<!-- 显示Hibernate持久化操作所生成的SQL -->
		<property name="show_sql">true</property>
		<!-- 将SQL脚本进行格式化后再输出 -->
		<property name="hibernate.format_sql">true</property>
		<!-- 罗列所有的映射文件 -->
		<mapping resource="映射文件路径/News.hbm.xml"/>
	</session-factory>
</hibernate-configuration>
```

- [ ] hibernate.properties文件

```
## MySQL
#方言
hibernate.dialect org.hibernate.dialect.MySQLDialect
hibernate.dialect org.hibernate.dialect.MySQLInnoDBDialect
hibernate.dialect org.hibernate.dialect.MySQLMyISAMDialect
#驱动
hibernate.connection.driver_class com.mysql.jdbc.Driver
#数据库地址
hibernate.connection.url jdbc:mysql://127.0.0.1/datdabseName
#用户名
hibernate.connection.username root
#密码
hibernate.connection.password 12345
#是否在控制台输出sql语句
hibernate.show_sql true/false
#设置当创建sessionfactory时，是否根据映射文件自动建立数据库表。 create-drop:表示关闭sessionFactory时，将drop刚建的数据库表。该属性可以是update/create-drop/create
hibernate.hbm2ddl.auto update/create-drop/create

###########################
### C3P0 Connection Pool C3P0连接池###
###########################
#连接池最大链接数
hibernate.c3p0.max_size 2
#连接池最小连接数
hibernate.c3p0.min_size 2
#连接池连接的超时时长
hibernate.c3p0.timeout 5000
#缓存statements 的数量
hibernate.c3p0.max_statements 100
hibernate.c3p0.idle_test_period 3000
hibernate.c3p0.acquire_increment 2
hibernate.c3p0.validate true/false


############
### JNDI (java naming directory interface)Java命名目录接口###
###当无需hibernate自己管理数据源而是直接访问容器管理数据源 使用JNDI
############
#指定数据源JNDI名字
hibernate.connection.datasource dddd
#文件系统下
hibernate.jndi.class com.sun.jndi.fscontext.RefFSContextFactory
hibernate.jndi.url file:/

#网络
#指定JND InitialContextFactory 的实现类，该属性也是可选的。如果JNDI与Hibernate持久化访问的代码处于同一个应用，无需指定该属性
hibernate.jndi.class com.ibm.websphere.naming.WsnInitialContextFactory
#指定JNDI提供者的URL，该属性可选 如果JNDI与Hibernate持久化访问的代码处于同一个应用，无需指定该属性
hibernate.jndi.url iiop://localhost:900/

#指定链接数据库用户名
hibernate.connection.username  root
#指定密码
hibernate.connection.password  1111
#指定方言
hibernate.dialect org.hibernate.dialect.MySQLDialect

#######################
### Transaction API   事务属性说明###
#######################

#指定是否在事务结束后自动关闭session 
hibernate.transaction.auto_close_session true/false
#指定session是否在事务完成后自动将数据刷新到底层数据库
hibernate.transaction.flush_before_completion true/false

## 指定hibernate所有的事务工厂的类型，该属性必须是TransactionFactory的直接或间接子类

hibernate.transaction.factory_class org.hibernate.transaction.JTATransactionFactory
hibernate.transaction.factory_class org.hibernate.transaction.JDBCTransactionFactory

## 该属性值是一个JNDI名，hibernate将使用JTATTransactionFactory从应用服务器中取出JTAYserTransaction

jta.UserTransaction jta/usertransaction
jta.UserTransaction javax.transaction.UserTransaction
jta.UserTransaction UserTransaction

## 该属性值为一个transactionManagerLookup类名，当使用JVM级别的缓存时，或在JTA环境中使用hilo生成器策略时，需要该类

hibernate.transaction.manager_lookup_class org.hibernate.transaction.JBossTransactionManagerLookup
hibernate.transaction.manager_lookup_class org.hibernate.transaction.WeblogicTransactionManagerLookup
hibernate.transaction.manager_lookup_class org.hibernate.transaction.WebSphereTransactionManagerLookup
hibernate.transaction.manager_lookup_class org.hibernate.transaction.OrionTransactionManagerLookup
hibernate.transaction.manager_lookup_class org.hibernate.transaction.ResinTransactionManagerLookup
```

- [x] hibernate.hbm.xml配置

```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC 
	"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">	
<!-- 
	<hibernate-mapping>一般不去配置，采用默认即可。
	schema:指定映射数据库的schema(模式/数据库)，如果指定该属性，则表名会自动添加该schema前缀
	package:指定包前缀 指定持久化类所在的包名 这样之后calss子元素中就不必使用全限定性的类名
	default-cascade="none"：默认的级联风格，表与表联动。
	default-lazy="true"：默认延迟加载
 -->
<hibernate-mapping>
	<!-- 
		<class>：使用class元素定义一个持久化类。
		name="cn.javass.user.vo.UserModel"：持久化类的java全限定名；
		table="tbl_user"：对应数据库表名，默认持久化类名作为表名；
		proxy:指定一个接口，在延迟装载时作为代理使用，也可在这里指定该类自己的名字。
		mutable="true"：默认为true，设置为false时则不可以被应用程序更新或删除，等价于所有<property>元素的update属性为false，表示整个实例不能被更新。
		dynamic-insert="false"：默认为false，动态修改那些有改变过的字段，而不用修改所有字段；
		dynamic-update="false"：默认为false，动态插入非空值字段；
		select-before-update="false"：默认为false，在修改之前先做一次查询，与用户的值进行对比，有变化都会真正更新；
		optimistic-lock="version"：默认为version(检查version/timestamp字段)，取值：all(检查全部字段)、dirty(只检查修改过的字段)；
		                           none(不使用乐观锁定)，此参数主要用来处理并发，每条值都有固定且唯一的版本，版本为最新时才能执行操作；
		如果需要采用继承映射，则class元素下还会增加<subclass.../>元素等用于定义子类。
	 -->
    <class name="cn.javass.user.vo.UserModel" table="tbl_user" >
    	
    	<!-- 
    		<id>：定义了该属性到数据库表主键字段的映射。
			type  指定该标识属性的数据类型，该类型可以是Hibernate的内建类型，也可以是java类型，如果是java类型则需要使用全限定类名（带包名）。该属性可选，如果没有指定类型， 则hibernate自行判断该标识属性数据类型。通常建议设定。
    		name="userId"：标识属性的名字；
    		column="userId"：表主键字段的名字，如果不填写与name一样；
			
    	 -->
    	<id name="userId">
    		<!-- <generator>：指定主键由什么生成，推荐使用uuid，assigned指用户手工填入。设定标识符生成器
			适应代理主键的有：
			    increment：有Hibernat自动以递增的方式生成标识符，每次增量1；
				identity：由底层数据库生成标识符，前提条件是底层数据库支持自动增长字段类型。（DB2,MYSQL）
                uuid:用128位的UUID算法生成字符串类型标识符。
			适应自然主键：
		        assigned:由java程序负责生成标识符，为了能让java应用程序设置OID,不能把setId（）方法设置成private类型。
				让应用程序在save()之前为对象分配一个标识符。相当于不指定<generator.../>元素时所采用的默认策略。
				应当尽量避免自然主键
			-->
    		<generator class="uuid"/>
    	</id>
    	
    	<!-- 
    		<version/>：使用版本控制来处理并发，要开启optimistic-lock="version"和dynamic-update="true"。
			name="version"：持久化类的属性名，column="version"：指定持有版本号的字段名；
    	 -->
    	<version name="version" column="version"/>
    	
    	<!-- 
    		<property>：为类定义一个持久化的javaBean风格的属性。
    		name="name"：标识属性的名字，以小写字母开头；
    		column="name"：表主键字段的名字，如果不填写与name一样；
    		update="true"/insert="true"：默认为true，表示可以被更新或插入；
			access="property/field"：指定Hibernate访问持久化类属性的方式。默认property。property表示使用setter/getter方式。field表示运用java反射机制直接访问类的属性。
			formula="{select。。。。。}"：该属性指定一个SLQ表达式，指定该属性的值将根据表达式类计算，计算属性没有和它对应的数据列。
			formula属性允许包含表达式：sum,average,max函数求值的结果。
			例如：formula="(select avg(p.price) from Product P)"
    	 -->
    	<property name="name" column="name" />
    	<property name="sex" column="sex"/>
		<property name="age" column="age"/>
		
		<!-- 
			组件映射：把多个属性打包在一起当一个属性使用，用来把类的粒度变小。
			<component name="属性，这里指对象">
			 	<property name="name1"></property>
			 	<property name="name2"></property>
		 	</component>
		 -->
		 
		 <!-- 
		 	<join>:一个对象映射多个表，该元素必须放在所有<property>之后。
		 	<join table="tbl_test：子表名">
		 		<key column="uuid：子表主键"></key>
		 	<property name="name1：对象属性" column="name：子表字段"></property>
		 </join>
		  -->
		 
	</class>	
</hibernate-mapping>
```
- [ ] log4j.properties
```
### direct log messages to stdout ###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Threshold=trace
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n


### direct messages to file hibernate.log ###
#log4j.appender.file=org.apache.log4j.FileAppender
#log4j.appender.file.File=D:\hibernate.log
#log4j.appender.file.layout=org.apache.log4j.PatternLayout
#log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n


### set log levels - for more verbose logging change 'info' to 'debug' ###


log4j.rootLogger=error, stdout


#log4j.logger.org.hibernate=info
log4j.logger.org.hibernate=debug


### log HQL query parser activity
#log4j.logger.org.hibernate.hql.ast.AST=debug


### log just the SQL
log4j.logger.org.hibernate.SQL=debug


### log JDBC bind parameters ###
#log4j.logger.org.hibernate.type=info
log4j.logger.org.hibernate.type=debug


### log schema export/update ###
log4j.logger.org.hibernate.tool.hbm2ddl=debug


### log HQL parse trees
#log4j.logger.org.hibernate.hql=debug


### log cache activity ###
log4j.logger.org.hibernate.cache=debug


### log transaction activity
#log4j.logger.org.hibernate.transaction=debug


### log JDBC resource acquisition
log4j.logger.org.hibernate.jdbc=debug


### enable the following line if you want to track down connection ###
### leakages when using DriverManagerConnectionProvider ###
#log4j.logger.org.hibernate.connection.DriverManagerConnectionProvider=trace
```
- [ ] hibernate.cfg.xml(版本2)
```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 指定Hibernate配置文件的DTD信息 -->
<!DOCTYPE hibernate-configuration PUBLIC
	"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- hibernate- configuration是连接配置文件的根元素 -->
<hibernate-configuration>
	<session-factory>
		<!-- 指定连接数据库所用的驱动 -->
		<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
		<!-- 指定连接数据库的url，hibernate连接的数据库名 -->
		<property name="connection.url">jdbc:mysql://127.0.0.1:3306/chapter20?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=true</property>
		<!-- 指定连接数据库的用户名 -->
		<property name="connection.username">root</property>
		<!-- 指定连接数据库的密码 -->
		<property name="connection.password">sh</property>
		<!-- 整合c3p0 -->
		<property name="hibernate.connection.provider_class">org.hibernate.connection.C3P0ConnectionProvider</property>
		<!-- 指定连接池里最大连接数 -->
		<property name="hibernate.c3p0.max_size">20</property>
		<!-- 指定连接池里最小连接数 -->
		<property name="hibernate.c3p0.min_size">1</property>
		<!-- 指定连接池里连接的超时时长 -->
		<property name="hibernate.c3p0.timeout">5000</property>
		<!-- 指定连接池里最大缓存多少个Statement对象 -->
		<property name="hibernate.c3p0.max_statements">100</property>
		<property name="hibernate.c3p0.idle_test_period">3000</property>
		<property name="hibernate.c3p0.acquire_increment">2</property>
		<property name="hibernate.c3p0.validate">true</property>
		<!--取消Bean校验 -->
		<property name="javax.persistence.validation.mode">none</property>
		<!-- 指定数据库方言 -->
		<!-- <property name="dialect">org.hibernate.dialect.MySQLInnoDBDialect</property> -->
		<property name="dialect">is.hibernate.MyDialect</property>
		<!-- 根据需要自动创建数据表 -->
		<property name="hbm2ddl.auto">update</property>
		<!-- 显示Hibernate持久化操作所生成的SQL -->
		<property name="show_sql">true</property>
		<!-- 将SQL脚本进行格式化后再输出 -->
		<property name="hibernate.format_sql">true</property>
		<!-- 罗列所有的映射文件 -->
		<mapping resource=""/>
	</session-factory>
</hibernate-configuration>

```
