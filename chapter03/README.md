## 请求流程
1.部署TOMCAT首先访问http://localhost:8080/chapter03/main.jsp
2.Struts2接收web.xml控制请求,找到对一个的action 的name
3.比如main发送一个add的一个请求，对应struts.xml文件先执行过滤器，判断是否在session作用域是否存在user的对象
4.如果不存在发送Input的action，如果存在执行原来action的class类进行判断
5.发送Input返回login页面，request请求参数也一起传入
6.如果密码账号在login的action类验证通过，向session作用存于user对象，然后发送main请求
7.又访问main的action的，循环上面先通过过滤器访问session对象，有对象则直接跳过，浏览器关闭，session对象销毁