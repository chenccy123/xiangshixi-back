package thu.declan.xi.server.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import thu.declan.xi.server.util.CustomJsonDateSerializer;

/**
 * 活动申请类
 * @author chency
 * @version 1.0 ,2018-06-20
 * 
 */
public class ActivityApply extends QueryModel{

	public ActivityApply() {}
	
    public enum ACTRole {
        COMPANY,    //企业
        STUDENT;    //学生
        
        public static ACTRole fromString(String str) {
            return Enum.valueOf(ACTRole.class, str.toUpperCase());
        }
        
		public String toChsString() {
			switch (this) {
				case COMPANY:
					return "企业";
				case STUDENT:
					return "学生";
			}
			return "";
		}
		
    }
   
    public enum ApplyStatus{
    	APPLYING,
    	CONFIRM,
    	CANCEL;
    	
        public static ApplyStatus fromString(String str) {
            return Enum.valueOf(ApplyStatus.class, str.toUpperCase());
        }
        
		public String toChsString() {
			switch (this) {
				case APPLYING:
					return "申请中";
				case CONFIRM:
					return "确认";
				case CANCEL:
					return "取消";	
			}
			return "";
		}
		
    }
    
    public enum PayStatus{
    	PAYING,
    	SUCCESS,
    	CANCEL;
    	
        public static PayStatus fromString(String str) {
            return Enum.valueOf(PayStatus.class, str.toUpperCase());
        }
        
		public String toChsString() {
			switch (this) {
				case PAYING:
					return "支付中";
				case SUCCESS:
					return "成功";
				case CANCEL:
					return "取消";	
			}
			return "";
		}
		
    } 
    
    public enum APCheck {
    	NOTCHECK,
    	ISCHECKED;
        
        public static APCheck fromString(String str) {
            return Enum.valueOf(APCheck.class, str.toUpperCase());
        }
		
		public String toChsString() {
			return this == ISCHECKED ? "已审核" : "未审核";
		}
		
    }
    

	
	/**
	 * 活动申请表编号
	 */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 申请会员编号
     */
    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
 
    /**
     * 公司对象
     */
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

/**
 * 学生对象
 */
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
    /**
     * 申请活动编号
     */
    private Integer activityId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
    /**
     * 用户类型
     */
    private ACTRole allowType;

    public ACTRole getAllowType() {
        return allowType;
    }

    public void setAllowType(ACTRole allowType) {
        this.allowType = allowType;
    }
    
    
    /**
     * 申请时间
     */
    private Date applyTime;

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    
    /**
     * 申请状态，申请中，确认，取消
     */
    
    private ApplyStatus applyStatus;

    public ApplyStatus getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(ApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }
    
    
    /**
     * 付费渠道
     */
    
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    } 
    
    /**
     * 付费金额
     */
    private Double payFee;

    public Double getPayFee() {
        return payFee;
    }

    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }
    
    
    /**
     * 付费时间
     */
    private Date payTime;

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
   
    /**
     * 付费状态
     */
    private PayStatus payStatus;

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }
   
    /**
     * 审核状态
     */
    private APCheck checkStatus;

    public APCheck getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(APCheck checkStatus) {
        this.checkStatus = checkStatus;
    }
    
    /**
     * 审核时间
     */
    private Date checkTime;

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    
    /**
     * 审核人
     */
    private Integer checkUserId;

    public Integer getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Integer checkUserId) {
        this.checkUserId = checkUserId;
    }
    
    /**
     * 
     * 
     */
}
