package thu.declan.xi.server.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import thu.declan.xi.server.util.CustomJsonDateSerializer;
import thu.declan.xi.server.util.WorkingDaysUtils;

/**
 * 
 * @author Sublime
 *
 */
public class SalaryTransit extends QueryModel{

	public enum State {
		NEW, //新增数据
		EFFECTIVE, // 有效数据
		INVALID, // 无效数据
		EXCEPTION, //异常的数据
		DISCARDED, // 废弃数据
		SUCCESS, // 匹配成功 or 未发放工资
		SALARY_SUCCESS, // 工资发放成功
		SALARY_ERROR; //工资发放错误
//		ERROR; // 二次发放错误

		public static State formString(String str) {
			return Enum.valueOf(State.class, str.toUpperCase());
		}
	}

	private Integer id;

	private Integer auditId; // 操作员id
	
	private Integer comId; // 企业id

	private Integer payId; // 工资单id
	
	private Integer accId; // 用户id

	private Integer stuId; // 学生id
	
	private String batch; // 生成批次
	
	private String phone; // 手机号

	private Double workDays; // 工作天数

	private Double dailyWage; // 工作日薪
	
	private String month; // 生成月份
	
	private Double subTotal; // 薪资总额
	
	private State state; // 状态
	
	private String prompt; // 错误描述

	@JsonIgnore
	private List<State> queryStates;

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date importDate;
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date submitDate;
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date paymentDate; // 发放薪资时间  
	
	private Student student;
	
	private Company company;
	
	private Account account;

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

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	
	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Double workDays) {
		this.workDays = workDays;
	}

	public Double getDailyWage() {
		return dailyWage;
	}

	public void setDailyWage(Double dailyWage) {
		this.dailyWage = dailyWage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(/*Double subTotal*/) {
		this.subTotal = WorkingDaysUtils.getRealVaule(this.workDays * this.dailyWage, 2).doubleValue();
		//this.subTotal = subTotal;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public List<State> getQueryStates() {
		return queryStates;
	}

	public void setQueryStates(List<State> queryStates) {
		this.queryStates = queryStates;
	}

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getImportDate() {
		return importDate;
	}

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getSubmitDate() {
		return submitDate;
	}

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
