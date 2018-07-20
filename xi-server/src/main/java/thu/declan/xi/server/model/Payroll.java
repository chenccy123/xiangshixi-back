package thu.declan.xi.server.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import thu.declan.xi.server.util.CustomJsonDateSerializer;

/**
 * @author Sublime
 */
public class Payroll extends QueryModel {
	
	public enum ApState {
		NOT_APPLY, //未申请
		CHECK_PENDING, // 已申请 待确认
		REJECT, // 已拒绝
		PENDING_PAYMENT, // 待确认转账
		ALREADY_PAID; // 发票已邮寄

		public static ApState formString(String str) {
			return Enum.valueOf(ApState.class, str.toUpperCase());
		}
	}

	public enum PaState {
		NOT_PAYING, // 未发放
		SUCCESS_ALL, // 工资全部发放成功
		SUCCESS_PART, //有失败记录
		ERROR; //工资发放失败

		public static PaState formString(String str) {
			return Enum.valueOf(PaState.class, str.toUpperCase());
		}
	}

	private Integer id;

	private Integer invId; // 发票id
	
	private Integer comId; // 企业id
	
	private String comName;
	
	private String month; // 月份
	
	private Double total; // 工资单总额
	
	private Integer number; // 包含人数
	
	private PaState payState; // 工资单状态
	
	private ApState applyState; // 申请状态
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date generateDate; // 生成时间
	
	@JsonIgnore
	private List<PaState> queryStates;
	
	private List<SalaryTransit> salaryTransits;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInvId() {
		return invId;
	}

	public void setInvId(Integer invId) {
		this.invId = invId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public PaState getPayState() {
		return payState;
	}

	public void setPayState(PaState payState) {
		this.payState = payState;
	}

	public ApState getApplyState() {
		return applyState;
	}

	public void setApplyState(ApState applyState) {
		this.applyState = applyState;
	}

	public List<PaState> getQueryStates() {
		return queryStates;
	}

	public void setQueryStates(List<PaState> queryStates) {
		this.queryStates = queryStates;
	}

	public List<SalaryTransit> getSalaryTransits() {
		return salaryTransits;
	}

	public void setSalaryTransits(List<SalaryTransit> salaryTransits) {
		this.salaryTransits = salaryTransits;
	}

	public Date getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

}
