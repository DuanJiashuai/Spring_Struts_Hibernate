package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Collection;
import entity.Commidity;
import entity.CommidityImages;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CollectionService;
import service.CommidityImagesService;
import service.CommidityService;
import service.UserService;

public class CollectionManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer collectionId;
	private Integer commidityId;
	private Integer amount;

	private CollectionService collectionService;
	private UserService userService;
	private CommidityService commidityService;
	private CommidityImagesService cimageService;

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCommidityService(CommidityService commidityService) {
		this.commidityService = commidityService;
	}

	public void setCimageService(CommidityImagesService cimageService) {
		this.cimageService = cimageService;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getCommidityId() {
		return commidityId;
	}

	public void setCommidityId(Integer commidityId) {
		this.commidityId = commidityId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String addToCollection() {
		try {
			String username = (String) ActionContext.getContext().getSession().get("username");
			User user = userService.getUserByUsername(username);
			Commidity commidity = commidityService.getCommidityById(getCommidityId());
			Collection collection = new Collection(commidity, user);
			collectionService.updateCollection(collection);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getCollectionListByUserId() throws IOException {
		String username = (String) ActionContext.getContext().getSession().get("username");
		Integer userid = userService.getUserByUsername(username).getUserId();
		List<Collection> list = collectionService.getCollectionListByUserId(userid);
		JSONArray ja = new JSONArray();
		for (Collection c : list) {
			JSONObject jo = new JSONObject();
			jo.put("collectionId", c.getCollectionId());
			jo.put("commidityId", c.getCommidity().getCommidityId());
			jo.put("commidityName", c.getCommidity().getCommidityName());
			List<CommidityImages> cimages=cimageService.getCimagesByCommidityId(c.getCommidity().getCommidityId());
			jo.put("pic", cimages.get(0).getCimagePath());
			jo.put("price", c.getCommidity().getPrice());
			jo.put("stock", c.getCommidity().getStock());
			jo.put("salesVol", c.getCommidity().getSalesVol());
			ja.add(jo);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ja);
		return null;
	}

	public String deleteCollection(){
		Collection collection=collectionService.getCollectionById(getCollectionId());
		collectionService.deleteCollection(collection);
		return SUCCESS;
	}
	
}
