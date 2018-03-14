package itcast.cn.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * invocation是拦截器在拦截中的信息对象
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		Object user = context.getSession().get("user");
		if(user != null) {
			//继续向下执行
			return invocation.invoke();
		}else {
			context.put("msg", "你还未登陆，请先登陆");
			//如果不成功，返回login值
			return Action.LOGIN;
		}
	}
	
}
