package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Commidity;
import entity.CommidityImages;
import entity.SubClassification;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CommidityImagesService;
import service.CommidityService;

public class CommidityManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer commidityId;
	private String commidityCode;
	private String commidityName;
	private String commidityDescription;
	private double price;
	private Integer stock;
	private Integer salesVol;
	private SubClassification sclass;
	private String firstCimage;

	private CommidityService commidityService;
	private CommidityImagesService cimageService;

	public void setCommidityService(CommidityService commidityService) {
		this.commidityService = commidityService;
	}

	public void setCimageService(CommidityImagesService cimageService) {
		this.cimageService = cimageService;
	}

	public Integer getCommidityId() {
		return commidityId;
	}

	public void setCommidityId(Integer commidityId) {
		this.commidityId = commidityId;
	}

	public String getCommidityCode() {
		return commidityCode;
	}

	public void setCommidityCode(String commidityCode) {
		this.commidityCode = commidityCode;
	}

	public String getCommidityName() {
		return commidityName;
	}

	public void setCommidityName(String commidityName) {
		this.commidityName = commidityName;
	}

	public String getCommidityDescription() {
		return commidityDescription;
	}

	public void setCommidityDescription(String commidityDescription) {
		this.commidityDescription = commidityDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSalesVol() {
		return salesVol;
	}

	public void setSalesVol(Integer salesVol) {
		this.salesVol = salesVol;
	}

	public SubClassification getSclass() {
		return sclass;
	}

	public void setSclass(SubClassification sclass) {
		this.sclass = sclass;
	}

	public String getFirstCimage() {
		return firstCimage;
	}

	public void setFirstCimage(String firstCimage) {
		this.firstCimage = firstCimage;
	}

	public String getAllCommidities() throws IOException {
		List<Commidity> list = commidityService.getAllCommidities();
		JSONArray ja = new JSONArray();
		for (Commidity c : list) {
			JSONObject jo = new JSONObject();
			jo.put("commidityId", c.getCommidityId());
			jo.put("commidityCode", c.getCommidityCode());
			jo.put("commidityName", c.getCommidityName());
			jo.put("commidityDescription", c.getCommidityDescription());
			jo.put("price", c.getPrice());
			jo.put("stock", c.getStock());
			jo.put("salesVol", c.getSalesVol());
			jo.put("sclassId", c.getSclass().getSclassId());
			jo.put("sclassName", c.getSclass().getSclassName());
			List<CommidityImages> cimages = cimageService.getCimagesByCommidityId(c.getCommidityId());
			jo.put("pic", cimages.get(0).getCimagePath());
			ja.add(jo);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ja);
		return null;
	}

	public String getCommidityDetails() throws IOException {
		Commidity commidity = commidityService.getCommidityById(getCommidityId());
		JSONObject jo = new JSONObject();
		jo.put("commidityName", commidity.getCommidityName());
		jo.put("commidityDescription", commidity.getCommidityDescription());
		jo.put("price", commidity.getPrice());
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jo);
		return null;
	}

	public String getCommidityImages() throws IOException {
		List<CommidityImages> list = cimageService.getCimagesByCommidityId(getCommidityId());
		JSONArray ja = new JSONArray();
		for (CommidityImages c : list) {
			JSONObject jo = new JSONObject();
			jo.put("path", c.getCimagePath());
			ja.add(jo);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ja);
		return null;
	}

}
