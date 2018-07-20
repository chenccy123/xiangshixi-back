package thu.declan.xi.server.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import thu.declan.xi.server.util.CustomJsonDateSerializer;

/**
 * 活动类
 * @author chency
 * @version 1.0 , 2018-06-19
 * 
 * @version 1.1,增加一个“报名对象”，去掉了付费方式和活动报名是否需要审核，修改published枚举值
 */
public class Activity  extends QueryModel{

    public enum Published {
    	NOTAPPLY,
        APPLYING,
        APPLYEND,
        ACTIVITYEND,
        OFFLINE;
        
        public static Published fromString(String str) {
            return Enum.valueOf(Published.class, str.toUpperCase());
        }
		
		public String toChsString() {
			switch (this) {
			case NOTAPPLY:
				return "未开始报名";
			case APPLYING:
				return "报名中";
			case APPLYEND:
				return "报名截止";
			case ACTIVITYEND:
				return "已结束";
			case OFFLINE:
				return "下线";	
		}
		return "";

		}
		
    }
 
  
    public enum Charge {
    	NOTCHARGE,
    	ISCHARGED;
        
        public static Charge fromString(String str) {
            return Enum.valueOf(Charge.class, str.toUpperCase());
        }
		
		public String toChsString() {
			return this == ISCHARGED ? "收费" : "不收费";
		}
		
    }
   
    
    public enum ACheck {
    	NOTCHECK,
    	ISCHECKED;
        
        public static ACheck fromString(String str) {
            return Enum.valueOf(ACheck.class, str.toUpperCase());
        }
		
		public String toChsString() {
			return this == ISCHECKED ? "需审核" : "不需审核";
		}
		
    }
    
    public enum ATarget {
        ISALLOWSTUDENT,
        ISALLOWCOMPANY,
        BOTH;
        
        public static ATarget fromString(String str) {
            return Enum.valueOf(ATarget.class, str.toUpperCase());
        }
		
		public String toChsString() {
			switch (this) {
				case ISALLOWSTUDENT:
					return "学生参加";
				case ISALLOWCOMPANY:
					return "企业参加";
				case BOTH:
					return "都可参加";

			}
			return "";
		}
    }
    
	public Activity() {}
	
	/**
	 * 活动编号
	 */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 活动正文
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * 活动标题
     */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 活动创建时间
     */
    private Date createTime;

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   
    /**
     * 活动时间，说明对活动举办时间做文字性说明
     */
    private String activityTime;

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }
    
    /**
     * 活动地点，对活动的举办地点做文字性说明
     */
    private String activityAddress;

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }
    
    
     /**
      * 创建活动记录的用户编号
      */
    private Integer createUserId;

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    
    /**
     * 活动开始时间
     */
    private Date beginTime;

    //@JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getbeginTime() {
        return beginTime;
    }

    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    
    /**
     * 活动结束时间
     */
    private Date endTime;

    //@JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
   

   /**
    * 活动发布状态
    */
    
    private Published published;

    public Published getPublished() {
        return published;
    }

    public void setPublished(Published published) {
        this.published = published;
    } 
  
    /**
     * 允许参加活动对象,学生，企业，两者都
     */
    private List<String> allowTarget;

    public List<String> getAllowTarget() {
        return allowTarget;
    }

    public void setAllowTarget(List<String> allowTarget) {
        this.allowTarget = allowTarget;
    }
    
    /**
     * 是否付费
     */
    private Charge isCharge;

    public Charge getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Charge isCharge) {
        this.isCharge = isCharge;
    }
    
    /**
     * 付费方式
     */
    /*
    private String chargeType;

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }
    */
    /**
     * 付费金额
     */
    private Double chargeFee;

    public Double getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(Double chargeFee) {
        this.chargeFee = chargeFee;
    }
    
 /**
  * 申请活动名单是否需要审核
  */
/*
    private ACheck isCheckForApplyList;

    public ACheck getIsCheckForApplyList() {
        return isCheckForApplyList;
    }

    public void setIsCheckForApplyList(ACheck isCheckForApplyList) {
        this.isCheckForApplyList = isCheckForApplyList;
    }
*/
    /**
     * 活动题图，
     */
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    /**
     * 报名对象
     */
    private List<String> applyTarget;
    
    public List<String> getApplyTarget() {
        return applyTarget;
    }
    
    public void setApplyTarget(List<String> applyTarget) {
        this.applyTarget = applyTarget;
    }
    
    /**
     * 登录帐号，和apply做关联查询时需要
     */
    
    
    
    
    private Integer accountID;
    
    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }
    
    private ActivityApply activityApply;
    
    public ActivityApply getActivityApply() {
        return activityApply;
    }

    public void setActivityApply(ActivityApply activityApply) {
        this.activityApply = activityApply;
    }
    
}
