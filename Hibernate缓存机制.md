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
