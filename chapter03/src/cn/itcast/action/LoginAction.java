package cn.itcast.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	/**
	 * 默认传递数据
	 */
	@Override
	public User getModel() {
		return user;
	}
	@Override
	public String execute() throws Exception {
		//获得ActionContext对象
		ActionContext context = ActionContext.getContext();
		System.out.println(user.getUsername()+","+user.getPassword());
		if("tom".equals(user.getUsername()) && "123".equals(user.getPassword())) {
			//将用户储藏在Session中
			context.getSession().put("user", user);
			return SUCCESS;
		}else {
			context.put("msg", "用户或密码不正确");
			return INPUT;
		}
	}
	
	
}
