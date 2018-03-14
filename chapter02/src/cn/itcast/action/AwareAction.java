package cn.itcast.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class AwareAction extends ActionSupport implements ServletRequestAware{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//Struts2调用ServLet-API方法
	HttpServletRequest request;
	@Override
	public String execute() throws Exception {
		request.setAttribute("message", "通过了ServletAware接口实现了访问Servlet-API");
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;	
	}
	
}
