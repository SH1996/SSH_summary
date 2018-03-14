package chapter06;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleDownLoadAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//定义了返回InputStream的方法，该方法作为被下载文件的入口
	public InputStream getDownloadFile() {
		System.out.println(ServletActionContext.getServletContext().getResourceAsStream("/Struts2.txt"));
		return ServletActionContext.getServletContext().getResourceAsStream("/Struts2.txt");
		
	}
	public String execute() throws Exception {
		
		return SUCCESS;
	}

}
