package thu.declan.xi.server.resource;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import thu.declan.xi.server.exception.ApiException;
import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Activity;
import thu.declan.xi.server.model.ActivityApply;
import thu.declan.xi.server.model.ActivityApply.ACTRole;
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.service.ActivityApplyService;
import thu.declan.xi.server.service.ActivityService;

@Path("activityApplies")
public class ActivityApplyResource extends BaseResource{

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityApplyResource.class);
	
    @Autowired
    private ActivityApplyService activityApplyService;
  
    @Autowired
    private ActivityService activityService;
    
	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ActivityApply createActivityApply(@Valid ActivityApply activityApply) throws ApiException {
		LOGGER.debug("==================== enter ActivityApplyResource createActivityApply ====================");
		try {
			
			Integer AccountID =currentAccountId();
			
			if(AccountID == null) {
				throw new ApiException(500, "没有登录 ", "没有登录。");
			}
			
			//防客户端伪造登录，直接注入
			if(AccountID != activityApply.getAccountId()) {
				throw new ApiException(500, "登录帐号有误 ", "登录帐号有误");
			}
			
			Activity act = null;
			act = activityService.get(activityApply.getActivityId());
		
			if(act == null) {
				throw new ApiException(500, "没有此活动 ", "没有此活动");
			}
			
		
			List<ActivityApply> applieslist = null;
			applieslist = activityApplyService.getList(activityApply);
			if(applieslist.size() > 0 ) {
				throw new ApiException(500, "重复申请", "重复申请");
			}
			
			activityApply.setAccountId(currentAccountId());
			activityApplyService.add(activityApply);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
			LOGGER.debug(devMsg);
			if (ex.getCode() == ServiceException.CODE_DUPLICATE_ELEMENT) {
				throw new ApiException(403, devMsg, "该活动申请已经存在。");
			}
			
			if (ex.getCode() == ServiceException.CODE_NO_SUCH_ELEMENT) {
				throw new ApiException(500, devMsg, "该活动不存在！");
			}
			
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityApplyResource createActivityApply ====================");
		return activityApply;
	}
	
	
    @GET
	@Path("/companies")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public ListResponse<ActivityApply> getCompanies(@QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("actid") Integer activityID) throws ApiException {
        LOGGER.debug("==================== enter ActivityApplyResource getCompanies ====================");
        LOGGER.debug("activityId: " + activityID);
        ActivityApply selector = new ActivityApply();
		selector.setActivityId(activityID);
		selector.setAllowType(ACTRole.COMPANY);
        List<ActivityApply> applieslist = null;
        Pagination pagination = new Pagination(pageSize, pageIndex);
        try {
        	applieslist = activityApplyService.getListforCompany(selector, pagination);
        } catch (ServiceException ex) {
            String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            handleServiceException(ex);
        }
        LOGGER.debug("==================== leave ActivityApplyResource getCompanies ====================");
        return new ListResponse(applieslist, pagination);
    }
    
    @GET
	@Path("/students")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public ListResponse<ActivityApply> getStudents(@QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("actid") Integer activityID) throws ApiException {
        LOGGER.debug("==================== enter ActivityApplyResource getStudents ====================");
        LOGGER.debug("activityId: " + activityID);
        ActivityApply selector = new ActivityApply();
		selector.setActivityId(activityID);
		selector.setAllowType(ACTRole.STUDENT);
        List<ActivityApply> applieslist = null;
        Pagination pagination = new Pagination(pageSize, pageIndex);
        try {
        	applieslist = activityApplyService.getListforStudent(selector, pagination);
        } catch (ServiceException ex) {
            String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            handleServiceException(ex);
        }
        LOGGER.debug("==================== leave ActivityApplyResource getStudents ====================");
        return new ListResponse(applieslist, pagination);
    }
    
}
