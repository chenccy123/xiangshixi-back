package thu.declan.xi.server.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import thu.declan.xi.server.util.CustomJsonDateSerializer;

/**
 * @author Sublime
 */
public class Invoice extends QueryModel {
	
	public enum InState {
		NOT_APPLY, // 未申请
		CHECK_PENDING, // 已申请 待审核
		REJECT, // 已拒绝
		PENDING_PAYMENT, // 已通过 待转账
		ALREADY_PAID; // 发票已邮寄 已转账
		
		public static InState formString(String str) {
			return Enum.valueOf(InState.class, str.toUpperCase());
		}
	}

	public enum InType {
		USUAL, // 普通发票
		SPECIAL; // 专用发票
		
		public static InType formString(String str) {
			return Enum.valueOf(InType.class, str.toUpperCase());
		}
	}
	
	private Integer id;
	
	private Integer auditId; // 审核人id
	
	private Integer comId; // 企业id
	
	private Integer actId; // 活动id
	
	private Integer payId; // 工资单id
	
	private String invTitle; // 发票抬头
	
	private String comEin; // 企业税号
	
	private Double money; // 开票金额
	
	private String comEmail; // 企业邮箱
	
	private InType invType; // 发票类别   普通  专用
	
	private InState applyState; // 申请状态
	
	private Company company;
	
	private Payroll payroll;
	
//	private Bill bill;
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date applyDate; // 申请时间
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date auditDate; // 审核时间

	@JsonIgnore
	private List<InState> queryStates;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public InType getInvType() {
		return invType;
	}

	public void setInvType(InType invType) {
		this.invType = invType;
	}

	public InState getApplyState() {
		return applyState;
	}

	public void setApplyState(InState applyState) {
		this.applyState = applyState;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public List<InState> getQueryStates() {
		return queryStates;
	}

	public void setQueryStates(List<InState> queryStates) {
		this.queryStates = queryStates;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

//	public Bill getBill() {
//		return bill;
//	}
//
//	public void setBill(Bill bill) {
//		this.bill = bill;
//	}
}
