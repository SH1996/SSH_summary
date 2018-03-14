package cn.itcast.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 1.在Struts2中，表单传值只需要通过get/set方法就可以默认获得
	 * 2.ActionContext对象获得域对象
	 */
	public String execute() throws Exception {
		//获得ActionContext
		ActionContext context = ActionContext.getContext();
		if("itcase".equals(username) && "123".equals(password)) {
			//将用户和密码放到Context对象之中
			context.put("username", username);
			context.put("password", password);
			context.put("success", "用户登录成功");
			return SUCCESS;
		}else {
			//定义登录失败的信息
			context.put("error", "用户登录失败");
			return ERROR;
		}
	}
}
