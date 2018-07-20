package thu.declan.xi.server.model;

/**
 * @author Sublime
 */
public class Bill extends QueryModel {
	
//	public enum BiState {
//		DEFAULT, //默认
//		NOT_DEFAULT, // 非默认
//		DISCARDED; // 已废弃
//
//		public static BiState formString(String str) {
//			return Enum.valueOf(BiState.class, str.toUpperCase());
//		}
//	}

	private Integer id;
	
	private Integer comId; // 企业id
	
	private String invTitle; // 发票抬头
	
	private String comEin; // 企业税号
	
	private String comEmail; // 企业邮箱
	
//	private BiState billState; // 票据状态  默认 非默认 废弃

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getInvTitle() {
		return invTitle;
	}

	public void setInvTitle(String invTitle) {
		this.invTitle = invTitle;
	}

	public String getComEin() {
		return comEin;
	}

	public void setComEin(String comEin) {
		this.comEin = comEin;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

//	public BiState getBillState() {
//		return billState;
//	}
//
//	public void setBillState(BiState billState) {
//		this.billState = billState;
//	}

}
