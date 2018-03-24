一级缓存
==============
### 什么是一级缓存？（一下几点）
* 缓存机制减少对数据库的繁琐的访问次数
* 从内存三种状态的数据对象探讨缓存机制，（瞬时，持久，脱离），持久态对象由session创建和管理，存于缓存区，其他状态对象则不是，会随时被GC回收
* 一级缓存又有三种缓存操作机制，在创建开始事务到提交之间（flush，clear（或evict），refresh），第一个是刷出缓存，减少同一对象的访问次数，
第二个是清空缓存，使缓存失效，第三个是刷新缓存，让缓存对象强制和数据库同步，三者只有第一个是缓存意义的存在的初心，其余不是
* 详细说明三者使用时数据库的访问过程：首先三者都是在创建事务到提交的这个过程之中存在的，flush是检查缓存区对象是否改变，如果改变将执行新的sql语句，
clear是清空缓存，所以提交时直接访问数据库，缓存可使用，refrsh是让缓存区对象和数据库对象保持一致（缓存变化），commit提交前会先flush操作，对比缓存区
是否有我们需要使用的对象，该对象是否和数据库保持一致（通过快照实现，不需要明白原理），还有缓存区没对象直接进行数据库操作的这几种判断来操作的集合，叫做一
个事务的开始到事务结束的commit（提交）
* 三种状态的转换关系图如图所示：
![缓存状态](http://img.my.csdn.net/uploads/201211/09/1352463633_2026.jpg)
* 演示说明（代码）：
```
@Test
public void test(){
   //1.得到session
   Session session = HibernateUtils.getConnection();
   //2.开启事务
   session.beginTransaction();
   //3.操作
   Book b = new Book();        //瞬时态对象
   b.setName("版本1.0");
   b.setPrice(100);
   session.save(b);            //持久态，存于缓存(打一个断点Debug)
   b.setBook("更新版本2.0");
   //4.提交事务
   session.getTransaction().commit();
   //5关闭资源
   session.close();
}
```
* debug结果为：一个insert操作，停止，单步跳入，一个update操作。

### 一级缓存的常见操作？
* get,load,find,iterate(),save()...
* 同上面的代码示例
* 如果将save（）改为get（），那么结果一个insert操作变为select操作，其他数据库操作一致。

### 代码解释
```
package is.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import is.pojo.User;

public class HibernateUtils {

	private static Configuration configuration = null;
	private static SessionFactory sessionFactory = null;
	static {
		configuration = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}
	//getSession-多例模式
	public static Session getSession() {
		
		return sessionFactory.openSession();
	}
	//测试
	public static void main(String[] args) throws Exception{

		   Session s1 = getSession();
			Session s2 = getSession();
			System.out.println(s1 == s2);
			//查询表单
			s1.beginTransaction();
			String hql = "from User";
			Query query = s1.createQuery(hql );
			List<User> list = query.list();
			for(User user : list) {
				System.out.println(user.getName());
			}
			//添加数据
			User user1 = new User();
			user1.setName("小hong");
			s1.save(user1);
			user1.setName("xiao明");
			s1.flush();
			s1.getTransaction().commit();
			//重置子增长主键不断开
			//alter table User auto_increment=8;
         //但是能力有限，找不到方法，所以办法就是不要使用自增长主键
         //Query查询方法(HQL查询.md文件分明)
			String hql1 = "select * from user ";
			query = s1.createSQLQuery(hql1);
			String hql2 = "select u.id, u.name from User u where u.name like :myname";
			List users = s1.createQuery(hql2).setParameter("myname", "%张%").list(); 
			//有效的利用缓存区中的持久数据
			s2.beginTransaction();
			User user2 = new User();
			user2.setId(8);
			user2.setName("88888");
//			s2.delete(user2); 
//			s2.flush();
			
			user2.setId(22);
			s2.update(user2);
			s2.getTransaction().commit();
			
			
			//close
			s1.close();
			s2.close();
			sessionFactory.close();
		
			
			/*
			 * 首先我们学习了，知道hibernate是基于一级缓存执行数据库基本操作，
			 * 在一级缓存中，不可以当缓存区中有一个对象时执行创建的另一个对象来修改它，
			 * 也就是缓存区中相同的对象不可以多例，当然一个session一个缓存区，
			 * 所有的session和缓存区都只有一个sesionFactory实例（实例代表new Object）
			 * 如果还是不明白（多余解释）：java代码中，hibernate操作数据是不是将数据分为，
			 * 瞬时数据，持久数据，脱离数据，只有持久数据存在缓存区中，当我们flush，
			 * refresh，clear时，分别代表将缓存区数据刷出到数据库，将数据库数据刷出
			 * 到缓存区，和将缓存数据清除，当然我们知道hibernate先通过缓存区再进入数据库，
			 * 所以这就是一个事务，再事务中，session的操作打开缓存区，同一个缓存区多个操作，
			 * 每一个操作都对数据库进行访问，持久数据再缓存区中发生改变，上面方法数据库与
			 * 缓存区之间的访问。
			 * 
			 */
	
}
}
```

事务的并发和隔离级别
================
其中一级缓存中，通过session.beginTransaction();开启了一个事务，session.getTransaction().commit();到事务提交，事务保证了一段数据访问的安全性，（一个事务是一个整体，事务提交后，所有数据状态保持一致，多线程中事务需要隔离，防止虚幻访问，事务的操作，对数据库是持久的），so，如何在多线程并发的大数据访问中确保事务机制不会带来虚幻访问现象呢？我们知道，一段事务的开始就意味着开始访问数据库（也可能只访问缓存，不考虑这一点），先读取（查操作）数据库，然后执行相应的写操作（写代表增删改操作），一个线程无论任何时刻都不会出现事务机制带来的虚幻访问问题，但是当多线程并发访问数据时就会，比如：A线程开始了事务，这时A线程的事务还没有结束时，B线程开始了事务，开始读取，所以这时A和B线程事务读到的都是一样的数据，但是A和B线程事务修改的内容不一样，因为事务具有整体性，所以，A线程事务提交先修改的数据会被B事务修改的数据覆盖，就出现了事务虚幻访问出现的覆盖问题，一般，一个线程事务开启到结束，都是先读，中间写，最后提交保存或回滚，但是并发问题事务整体性就会出现种种问题，所以需要在多线程下设置事务隔离，访问同时访问出现的问题，但是隔离性越好，访问性能越慢，所以不同数据不同隔离，hibernate有四个事务隔离级别，分别是：1.可读未提交的，2.可读已提交的，3.可重复读取（一个事务中重复读取数据前后一致），4.序列化级别（就是一个事务完成了提交结束了，另一个事务才能开始，这里是指对同一个对象，命名为序列化级别的意思就是将java对象持久保存到数据库，hibernate时面向对象的查询，然后通过映射来访问数据库的···）；；；
注意：
===
* 多个线程执行多个事务，由于事务是很多sql语句的集合，业务的安全性出发，基于事务的整体一致性，so，隔离级别针对并发时访问的同一条数据，其中，四个隔离级别都不允许，事务改操作同时发生，读未提交的允许读但是不允许任何‘写’操作，读已提交允许读已提交的，但是不允许读未提交的和任何‘写’操作，但是可重复读允许读插入成功的数据，不允许读修改成功的数据，不允许任何‘写’操作，序列化则是提交一个，开启另一个事务，这个事务是对同一条数据而言；





