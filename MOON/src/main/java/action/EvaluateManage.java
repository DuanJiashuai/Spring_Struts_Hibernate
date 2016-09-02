package action;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import service.EvaluateService;
import service.OrderService;

public class EvaluateManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer evaluateId;
	private Integer orderId;
	private Integer logistics;
	private Integer service;
	private Integer quality;
	private HttpServletRequest request;

	private EvaluateService evaluateService;
	private OrderService orderService;

	public void setEvaluateService(EvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getLogistics() {
		return logistics;
	}

	public void setLogistics(Integer logistics) {
		this.logistics = logistics;
	}

	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
