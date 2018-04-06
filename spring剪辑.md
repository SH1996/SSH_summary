spring(纯)
=========

### 1.website（ https://repo.spring.io )

### 2.spring-tool-suite(sts)

### 3.hello-spring:

1).架包：core.context.beans.expression.commons-logging

2).sts插件applicationContent.xml(掩饰案例只导入了beans命名空间，也就是约，说明：beans约束是约束beans配置，context是约束容器包扫面等，aop则有aop的使用声明)

### 4.beans配置:

1).ioc和benas关系： 

      a.ioc是一个beans容器-创建容器有两个接口：beanfactory & applicationContent（子接口）
      b.区别：前者是底层的，一般不用，后者子接口具有启动，刷新，关闭上下文的能力，初始实例化
      singleton的bean，还有一个webApplicationContent专门为web开发准备的
      c.factoryBean:是spring的bean工厂bean，实现类不用加载application.xml配置文件
      d.getBean()方法：一个是.class,这种方法只允许bean只有一个，所以不实用，一般使用“”
      e.set／get方法注入：默认spring匹配对象根据名字或类型，也可以指定，construtor注入，
      使用参数类型和下标混和使用，区分重载构造器，来识别

2).属性配置：

      a.ref
      b.value
      c.与ref相比，还有一个内部bean，不允许被引用
      d.<null/>值
      e.级联属性配置：name=“use.id” value=123,注意use需要先初始化
      f.集合属性
      g.配置util单例bean，供其他bean引用（导入命名空间）
      h.p命名空间DI（导入命名空间）
      i.外部文件：properties文件
      l.spel：spring表达式语言-应用bean，属性，方法
      
      Value:DI时需要注意特殊字符：这时可以使用转义字符，或者<![CDATA[ ]]>
  
  3).自动装配：

      a. autowired(type／name)
      b. 注解方式：DI意思相同，自动识别依赖注入，默认type，同时使用qualifier识别容器中的bean，关于这个含义在c中解释，如下
      c. 在DI中，spring注解自动装配中，一部分会自动识别，一部分不能自动识别，可能存在多种结果或明确的结果，所以，需要指定，那么相对上面
      qualifier的使用，也可以不使用，在依赖的那个bean的注解@repository（“声明”），这个声明和quailifer效果一样，目的是为了容器中注解
      的bean存在名字，从而实现注解的方式实现，另外注解中还有找不到报异常，可以在注解中导入参数让其不报错而为null。
      
  4).bean之间的关系：
      
      a.抽象bean，没有class，不能实例化
      b.继承bean，继承其他bean或抽象 bean
      c.依赖bean，就是这个bean被创建时，必须被依赖的bean必须有前置依赖的bean实例化
      
  5).作用域：
  
      a.singleton
      b.property 调用bean时实例化，不被容器管理生命周期
      
      
## 5.AOP


      
  
