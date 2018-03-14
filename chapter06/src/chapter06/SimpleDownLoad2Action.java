package chapter06;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import sun.misc.BASE64Encoder;

public class SimpleDownLoad2Action extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String filename;
	private String contentType;
	/**
	 * 获得文件的名称
	 * @throws IOException 
	 */
	public String getFilename() throws IOException {
		//对于不同的浏览器传过来的文件名进行编码
		return encodeDownloadFilename(filename,ServletActionContext.getRequest().getHeader("User-Agent"));
	}
	public void setFilename(String filename) throws UnsupportedEncodingException {
		//对文件名称编码
		filename = new String(filename.getBytes("utf-8"),"utf-8");
		this.filename = filename;
	}
	/**
	 * 获取文件类型
	 * @return
	 */
	public String getContentType() {
		return ServletActionContext.getServletContext().getMimeType(filename);
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	//定义了返回InputStream的方法，该方法作为下载文件的入口
	public InputStream getDownloadFile2() throws IOException{
		
		//要下载的文件的路径
		String filepath = "/"+filename;
		
		return ServletActionContext.getServletContext().getResourceAsStream(filepath);
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 对于不同的浏览器传过来的文件名进行转码
	 * 
	 * @param filename2文件名称
	 * @param header浏览器
	 * @return 转码后的名称
	 * @throws IOException 
	 */
	private String encodeDownloadFilename(String name, String agent) throws IOException {
		if(agent.contains("Firefox")) {
			name ="=?UTF-8?B?"+
					new BASE64Encoder().encode(name.getBytes("utf-8"))+"?=";
		}else {
			name =URLEncoder.encode(name,"utf-8");
		}
		return name;
	}

}
