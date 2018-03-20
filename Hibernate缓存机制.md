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
