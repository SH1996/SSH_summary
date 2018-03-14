package chapter06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class FileUploadAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private File file;//提交的文件
	private String fileFileName;//提交的文件名字
	private String fileContentType;//提交的文件的类型
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	/*
	 * 文件上传处理
	 */
	public String execute() throws Exception {
		//文件输出流
		InputStream is = new FileInputStream(file);
		//设置文件保存的目录
		String toUploadPath = ServletActionContext.getServletContext().getRealPath("/");
		//设置目标文件
		File toFile = new File(toUploadPath,this.getFileFileName());
		//文件输出流
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int lenght = 0;
		//读取file文件输出到tofile文件中
		while(-1 != (lenght = is.read(buffer,0,buffer.length))) {
			os.write(buffer);
		}
		//关闭输入流和输出流
		is.close();
		os.close();
		//通过ActionContext获取valueStack对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//debug查看变量（打个断点）
		System.out.println(valueStack);
		return SUCCESS;
	}

}
