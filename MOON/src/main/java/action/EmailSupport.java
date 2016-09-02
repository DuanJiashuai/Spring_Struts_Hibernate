package action;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class EmailSupport extends ActionSupport {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String sendPasswordToUser() throws MessagingException{
		request=ServletActionContext.getRequest();
		String username=request.getParameter("username");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		User user=userService.getUserByUsername(username);
		if(user!=null&&user.getTel().equals(tel)&&user.getEmail().equals(email)){
			Message message=this.getEmailMessage("E-Shop用户密码找回",email);
			message.setText("尊敬的用户，您在本站进行找回密码操作成功，您的原密码为：" +user.getPassword()+" 请及时修改密码并妥善保管您的用户信息");
			Transport.send(message);
			return SUCCESS;
		}
		return ERROR;
	}
	
	private Session getEmailSession(){
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "smtp.126.com");
		prop.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port", "25");
		prop.setProperty("mail.smtp.socketFactory.port", "25");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zpf12345678910@126.com",
						"zpf123456789");
			}
		});
		return session;
	}
	
	private Message getEmailMessage(String subject,String mailto){
		Session session=this.getEmailSession();
		Message message = new MimeMessage(session);
		try {
			message.setRecipient(Message.RecipientType.TO,
					new InternetAddress(mailto));
			message.setFrom(new InternetAddress("zpf12345678910@126.com"));
			message.setSubject(subject);
			message.setSentDate(new Date());
			return message;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
