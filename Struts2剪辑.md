
Struts2介绍：
==========

### 1.struts2一些规范和简介：

struts2是一个功能强大，简单易用，灵活🉐️支持用户的个性化，或自带的默认格式，使得发开更加随意，和开心，和很好的国际化和校验功能···
struts2架框大多配置文件有默认的名称和路径，大半参数和信息可以通过struts2-core.jar打开查看，下载的apps自带简单入门模版，
因为struts2内容简介明了，自带一套解决web技术的内置标签，个性配置，ognl导航语言，和一些列应用于web开发的技术（文件上传等），
其核心是拦截器配置，用来处理很多action拦截，还有优良的值栈空间，可以通过debug查看与空间variables的数据；

### 2.struts2一些配置问题：


- [x] 从struts.xml开始，从配置constant，package，including属性来认识，分别作为struts2的常量配置，映射配置，和引入其他struts.xml文件

- [x] constant常量配置一般是：在src之下建立一个默认被识别的struts.propertes文件，一般有编码，扩展名，开发模式，动态方法关闭，打开ognl对静态的访问，
所有的配置参数都有默认的多套配置，在struts的core包之下，有覆盖的加载顺序之分

- [x] package配置是action的映射配置，里面可以配置action和interceptions，其中每一个标签和子标签都代表不同的含义，简单百度，注意，
拦截器配置需要在action中引入才能使用，以上所有struts.xml文件配置尽量效仿，比如extends=default-struts应该添加，因为struts是简单易学的框架，
这是因为它在内置已经配置过了一套默认的参数，大多数情况下简单继承即可实现相应的业务逻辑，同时action控制类也很简单，实现方式也是继承，满足javaBean规范，
和action控制类访问servlet的API这样的问题，对于这样的问题，就是一个属性（字段）驱动，和模型驱动，还有页面标签和ognl合并使用访问值栈的一些小问题，
最后struts.xml虽然和servlet是脱离的，ActionContent对象访问域空间耦合度很低，但是依然和jsp内置对象内容相关···

- [ ] including是引入其他的struts.xml的文件，一般可别struts2框架默认识别的struts.xml文件只写including来引入多个其他的配置，分而治之     



- Struts2官网：[sruts](http://struts.apache.org)

- 学习官网：[yibai](https://www.yiibai.com/struts_2/)

![动漫](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522326939146&di=faac63ea8ee993563006692e1a640177&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201708%2F02%2F162015g8shss8wbwb8w193.jpg)


App中showcase的配置模版文件：
========================

```

<?xml version="1.0" encoding="UTF-8" ?>
<!--
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<!DOCTYPE struts PUBLIC
	"-//Apache Software Foundation//DTD Struts Configuration 2.5//EN"
	"http://struts.apache.org/dtds/struts-2.5.dtd">

<!-- START SNIPPET: xworkSample -->
<struts>

    <!-- Some or all of these can be flipped to true for debugging -->
    <constant name="struts.i18n.reload" value="false" />
    <constant name="struts.enable.DynamicMethodInvocation" value="true" />
    <constant name="struts.devMode" value="false" />
    <constant name="struts.configuration.xml.reload" value="false" />
    <constant name="struts.custom.i18n.resources" value="globalMessages" />
    <constant name="struts.action.extension" value="action,," />

    <constant name="struts.convention.package.locators.basePackage" value="org.apache.struts2.showcase" />
    <constant name="struts.convention.result.path" value="/WEB-INF" />

    <!-- Necessary for Showcase because default includes org.apache.struts2.* -->
    <constant name="struts.convention.exclude.packages" value="org.apache.struts.*,org.springframework.web.struts.*,org.springframework.web.struts2.*,org.hibernate.*"/>

    <constant name="struts.freemarker.manager.classname" value="customFreemarkerManager" />
    <constant name="struts.serve.static" value="true" />
    <constant name="struts.serve.static.browserCache" value="false" />

    <include file="struts-interactive.xml" />

    <include file="struts-hangman.xml" />

    <include file="struts-tags.xml"/>

    <include file="struts-validation.xml" />

    <include file="struts-actionchaining.xml" />

    <include file="struts-fileupload.xml" />

    <include file="struts-person.xml" />

    <include file="struts-wait.xml" />

    <include file="struts-token.xml" />

    <include file="struts-model-driven.xml" />

    <include file="struts-filedownload.xml" />

    <include file="struts-conversion.xml" />

    <include file="struts-freemarker.xml" />

    <include file="struts-tiles.xml" />

    <include file="struts-xslt.xml" />

    <package name="default" extends="struts-default">
        <interceptors>
            <interceptor-stack name="crudStack">
                <interceptor-ref name="checkbox" />
                <interceptor-ref name="params" />
                <interceptor-ref name="staticParams" />
                <interceptor-ref name="defaultStack" />
            </interceptor-stack>
        </interceptors>

        <default-action-ref name="showcase" />

        <action name="showcase">
            <result>/WEB-INF/showcase.jsp</result>
        </action>

        <action name="help">
            <result>/WEB-INF/help.jsp</result>
        </action>

        <action name="viewSource" class="org.apache.struts2.showcase.source.ViewSourceAction">
            <result>/WEB-INF/viewSource.jsp</result>
        </action>

        <action name="date" class="org.apache.struts2.showcase.DateAction" method="browse">
            <result name="success">/WEB-INF/date.jsp</result>
        </action>

    </package>

    <package name="skill" extends="default" namespace="/skill">
        <default-interceptor-ref name="crudStack"/>

        <action name="list" class="org.apache.struts2.showcase.action.SkillAction" method="list">
            <result>/WEB-INF/empmanager/listSkills.jsp</result>
            <interceptor-ref name="basicStack"/>
        </action>
        <action name="edit" class="org.apache.struts2.showcase.action.SkillAction">
            <result>/WEB-INF/empmanager/editSkill.jsp</result>
            <interceptor-ref name="params" />
            <interceptor-ref name="basicStack"/>
        </action>
        <action name="save" class="org.apache.struts2.showcase.action.SkillAction" method="save">
            <result name="input">/WEB-INF/empmanager/editSkill.jsp</result>
            <result type="redirect">list.action</result>
        </action>
        <action name="delete" class="org.apache.struts2.showcase.action.SkillAction" method="delete">
            <result name="error">/WEB-INF/empmanager/editSkill.jsp</result>
            <result type="redirect">list.action</result>
        </action>
    </package>

    <package name="employee" extends="default" namespace="/employee">
        <default-interceptor-ref name="crudStack"/>

        <action name="list" class="org.apache.struts2.showcase.action.EmployeeAction" method="list">
            <result>/WEB-INF/empmanager/listEmployees.jsp</result>
            <interceptor-ref name="basicStack"/>
        </action>
        <action name="edit-*" class="org.apache.struts2.showcase.action.EmployeeAction">
      		<param name="empId">{1}</param>
            <result>/WEB-INF/empmanager/editEmployee.jsp</result>
            <interceptor-ref name="crudStack"><param name="validation.excludeMethods">execute</param></interceptor-ref>
        </action>
        <action name="save" class="org.apache.struts2.showcase.action.EmployeeAction" method="save">
            <result name="input">/WEB-INF/empmanager/editEmployee.jsp</result>
            <result type="redirect">list.action</result>
        </action>
        <action name="delete" class="org.apache.struts2.showcase.action.EmployeeAction" method="delete">
            <result name="error">/WEB-INF/empmanager/editEmployee.jsp</result>
            <result type="redirect">list.action</result>
        </action>
    </package>

</struts>

<!-- END SNIPPET: xworkSample -->

```
