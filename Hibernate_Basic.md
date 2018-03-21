Hibernate——Grammer
=================
* getSession
```
public class HibernateUtils {

	public static Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	private static SessionFactory  factory = null;
	static {
		factory= configuration.buildSessionFactory();
	}
	public static Session getSession(){
		return factory.openSession();
	}
	
}
```
* select_by_id
```
User user = (User) session.get(User.class,1);
User user1 = (User) session.load(User.class,1);
```
* select_rows
```
Criteria criteria = session.createCriteria(User.class);
criteria.setProjection(Projections.rowCount());
java.util.List list = criteria.list();
```
* delete_by_id
```
User user = (User) session.get(User.class,1);
session.delete(user);
```
* save
```
User user3 = new User();
user3.setName("demo");
session.save(user3);
```
* update
```
User user3 = new User();
user3.setName("demo");
user3.setId(5);
session.update(user3);
```
* findAll
```
Session session = sessionFactory.getCurrentSession();  
String hql = "from Role";  
Query query = session.createQuery(hql);  
List<Role> roles = query.list();
```
* Criteria(tiaojian)
```
Criteria criteria2 = session.createCriteria(Customer.class);
criteria2.add(Restrictions.eq("name","wangwu"));
```
* 模糊查询
```
Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
session.beginTransaction();
List result=session.createQuery("from Classes as a where a.classno 
like " '%"+OId+"%'").list();

```
* 
```

```
