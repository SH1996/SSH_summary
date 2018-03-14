package cn.itcast.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class ValueStackAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public String execute() throws Exception {
		//通过ActionContext获取valueStack对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//debug查看变量
		System.out.println(valueStack);
		return SUCCESS;
	}
	
}
