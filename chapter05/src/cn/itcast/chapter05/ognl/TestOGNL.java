package cn.itcast.chapter05.ognl;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

public class TestOGNL {

	/**
	 * DEMO-OGNL
     *
	 * @param args
	 * @throws OgnlException
	 */
	public static void main(String[] args) throws OgnlException {
		/*
		 * 使用OGNL访问变量
		 */
		Branch branch = new Branch();
		Group group = new Group();
		User user = new User();
		branch.setBranchId("BRANCHID");
		group.setBranch(branch);
		user.setGroup(group);
		//用java来导航访问
		System.out.println("java方式-"+user.getGroup().getBranch().getBranchId());
		//利用OGNL表达式访问
		System.out.println("OGNL方法-"+(String)Ognl.getValue("group.branch.branchId",user));
		//设置user对象的username为itcast
		user.setUsername("itcast");
		//创建context对象
		Map<String, User> context = new HashMap<String, User>();
		//将user对象添加到context对象之中
		context.put("u",user);
		//输出OGNL对象获得user对象的username值
		System.out.println("获取context对象的输出结果为-"+(String)Ognl.getValue("#u.username",context,user));
		
		/*
		 * 使用OGNl访问方法
		 */
		//创建User对象
		User user1 = new User();
		user1.setUsername("itcast");
		//使用OGNL访问username的值
		System.out.println("username="+(String)Ognl.getValue("getUsername()", user1));
		//创建Group对象
		Group p = new Group();
		//设置name的值
		p.setName("Object-Graph-Navigation-Language");
		//将Group添加user对象
		user1.setGroup(p);
		//输出group对象的name值
		System.out.println("groupname="+(String)Ognl.getValue("getGroup().getName()",user1));
		
		/*
		 * OGNL访问静态方法和属性
		 * 高版本需要在struts。xml中开启设置
		 * 在index。jsp中demo
		 */
		
		
	}
}
