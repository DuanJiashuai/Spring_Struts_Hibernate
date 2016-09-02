package action;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class UserManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String nickname;
	private String tel;
	private String email;
	private String headicon;
	private String auto;
	private String oldPwd;
	private HttpServletRequest request;
	
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSavePath(String value) {
		this.savePath = value;
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

	public String register() {
		try {
			User user = new User(getUsername(), getPassword(), getNickname(), getTel(), getEmail());
			userService.updateUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String login() {
		User user = userService.getUserByUsername(getUsername());
		ActionContext ctx = ActionContext.getContext();
		if (user != null && user.getPassword().equals(getPassword())) {
			ctx.getSession().put("username", username);
			Integer counter = (Integer) ctx.getApplication().get("counter");
			if (counter == null) {
				counter = 1;
			} else {
				counter = counter + 1;
			}
			ctx.getApplication().put("counter", counter);
			if (auto != null && auto.equals("on")) {
				Cookie cookie = new Cookie("username", username);
				cookie.setMaxAge(5 * 60 * 60);
				ServletActionContext.getResponse().addCookie(cookie);
			}
			ctx.put("tips", "欢迎您，" + username);
			return SUCCESS;
		} else {
			ctx.put("tips", "用户名或密码错误，请核对后再登录!");
			return INPUT;
		}
	}

	public String drop() {
		String username = (String) ActionContext.getContext().getSession().get("username");
		User user = userService.getUserByUsername(username);
		if (user != null) {
			if (user.getTel().equals(getTel()) && user.getEmail().equals(getEmail())
					&& user.getPassword().equals(getPassword())) {
				userService.deleteUser(user);
				return SUCCESS;
			} else {
				return INPUT;
			}
		}
		return null;
	}

	public String update() {
		try {
			String username = (String) ActionContext.getContext().getSession().get("username");
			User user = userService.getUserByUsername(username);
			request=ServletActionContext.getRequest();
			user.setNickname(request.getParameter("nickname"));
			user.setTel(request.getParameter("tel"));
			user.setEmail(request.getParameter("email"));
			
			/*user.setNickname(getNickname());
			user.setTel(getTel());
			user.setEmail(getEmail());*/
			userService.updateUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String editPassword(){
		User user = userService.getUserByUsername(getUsername());
		if(!getOldPwd().equals(user.getPassword())){
			return ERROR;
		}else{
			user.setPassword(getPassword());
			userService.updateUser(user);
			return SUCCESS;
		}
	}

	public String getCookie() {
		User user = userService.getUserByUsername(username);
		setPassword(user.getPassword());
		return Action.SUCCESS;
	}

	public String getUserInfo() {
		String username = (String) ActionContext.getContext().getSession().get("username");
		User user = userService.getUserByUsername(username);
		this.setUsername(username);
		this.setNickname(user.getNickname());
		this.setTel(user.getTel());
		this.setEmail(user.getEmail());
		this.setHeadicon(user.getHeadicon());
		return Action.SUCCESS;
	}

	
}
