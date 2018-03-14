package cn.itcast.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 执行方法
	 */
	public String execute() throws Exception {
		
		return SUCCESS;
	}

}
