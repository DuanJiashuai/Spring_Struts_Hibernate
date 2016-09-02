package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class FileUpload extends ActionSupport {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	private String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return (this.upload);
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return (this.uploadContentType);
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return (this.uploadFileName);
	}

	public String updateUserHeadicon() {
		try {
			String username = (String) ActionContext.getContext().getSession().get("username");
			User user = userService.getUserByUsername(username);
			this.deleteFile(getSavePath() + "\\" + user.getHeadicon());

			String fileType = getUploadFileName().substring(getUploadFileName().lastIndexOf(".") + 1);
			String newFilename = getTimeStampRand() + "." + fileType;
			this.uploadFile(newFilename);

			user.setHeadicon(newFilename);
			userService.updateUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private void uploadFile(String filename) {
		try {
			FileOutputStream fos;
			String filePath = getSavePath() + "\\" + filename;
			fos = new FileOutputStream(filePath);
			System.out.println(filePath);
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(String filePathAndName) {
		File file = new File(filePathAndName);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	public String getTimeStampRand() {
		StringBuffer buf = new StringBuffer();
		buf.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date()));
		Random rand = new Random();
		for (int i = 0; i < 3; i++) {
			buf.append(rand.nextInt(10));
		}
		return buf.toString();
	}
}
