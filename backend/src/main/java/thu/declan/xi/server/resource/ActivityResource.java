package thu.declan.xi.server.resource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import thu.declan.xi.server.exception.ApiException;
import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Activity;
import thu.declan.xi.server.model.Activity.ATarget;
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.model.QueryModel;
import thu.declan.xi.server.service.ActivityService;

/**
 * 活动接口
 * @author chency 2018-06-21
 *
 */
@Path("activities")
public class ActivityResource extends BaseResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityResource.class);
	
    @Autowired
    private ActivityService activityService;
    
	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Activity createActivity(@Valid Activity activity) throws ApiException {
		LOGGER.debug("==================== enter ActivityResource createActivity ====================");
		try {
			activity.setCreateUserId(currentAccountId());
			activityService.add(activity);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
			LOGGER.debug(devMsg);
			if (ex.getCode() == ServiceException.CODE_DUPLICATE_ELEMENT) {
				throw new ApiException(403, devMsg, "该活动已经存在。");
			}
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityResource createActivity ====================");
		return activity;
	}

	
    @GET
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public ListResponse<Activity> getActivities(@QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("keyword") String keyword) throws ApiException {
        LOGGER.debug("==================== enter ActivityResource getActivities ====================");
		Activity selector = new Activity();
		selector.setQueryParam(QueryModel.SEARCH_KEY, keyword);
        List<Activity> activitieslist = null;
        Pagination pagination = new Pagination(pageSize, pageIndex);
        try {
        	activitieslist = activityService.getList(selector, pagination);
        } catch (ServiceException ex) {
            String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            handleServiceException(ex);
        }
        LOGGER.debug("==================== leave ActivityResource getActivities ====================");
        return new ListResponse(activitieslist, pagination);
    }
    

    @GET
    @Path("/listforstudent")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public ListResponse<Activity> getActivitiesListForStudent(@QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize) throws ApiException {
        LOGGER.debug("==================== enter ActivityResource getActivitiesForStudentList ====================");
		Activity selector = new Activity();
		List<String> allowtarget = Arrays.asList("ISALLOWSTUDENT");
		selector.setAllowTarget(allowtarget);
        List<Activity> activitieslist = null;
        Pagination pagination = new Pagination(pageSize, pageIndex);
        try {
        	activitieslist = activityService.getList(selector, pagination);
        } catch (ServiceException ex) {
            String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            handleServiceException(ex);
        }
        LOGGER.debug("==================== leave ActivityResource getActivitiesForStudentList ====================");
        return new ListResponse(activitieslist, pagination);
    }

    
    @GET
    @Path("/listforcompany")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public ListResponse<Activity> getActivitiesListForCompany(@QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize) throws ApiException {
        LOGGER.debug("==================== enter ActivityResource getActivitiesForCompanyList ====================");
		Activity selector = new Activity();
		List<String> allowtarget = Arrays.asList("ISALLOWCOMPANY");
		selector.setAllowTarget(allowtarget);
        List<Activity> activitieslist = null;
        Pagination pagination = new Pagination(pageSize, pageIndex);
        try {
        	activitieslist = activityService.getList(selector, pagination);
        } catch (ServiceException ex) {
            String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            handleServiceException(ex);
        }
        LOGGER.debug("==================== leave ActivityResource getActivitiesForCompanyList ====================");
        return new ListResponse(activitieslist, pagination);
    }
    	
	@GET
	@Path("/{activityId}")
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Activity getActivity(@PathParam("activityId") int activityId) throws ApiException {
		LOGGER.debug("==================== enter ActivityResource getActivity ====================");
		LOGGER.debug("activityId: " + activityId);
		Activity act = null;
		try {
			act = activityService.get(activityId);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
			LOGGER.debug(devMsg);
			if (ex.getCode() == ServiceException.CODE_NO_SUCH_ELEMENT) {
				throw new ApiException(404, devMsg, "该活动不存在！");
			}
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityResource getActivity ====================");
		return act;
	}
	
	
    @PUT
    @Path("/{activityId}")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Activity editActivity(@PathParam("activityId") int activityId, Activity activity) throws ApiException {
        LOGGER.debug("==================== enter ActivityResource editActivity ====================");
        LOGGER.debug("activityId: " + activityId);
        try {
        	activity.setId(activityId);
        	activityService.update(activity);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
            LOGGER.debug(devMsg);
            if (ex.getCode() == ServiceException.CODE_NO_SUCH_ELEMENT) {
                throw new ApiException(404, devMsg, "该活动不存在！");
            }
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityResource editActivity ====================");
        return activity;
    }
    
    @GET
    @Path("/{activityId}/delete")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
  public void deleteActivity(@PathParam("activityId") int activityId) throws ApiException {
      LOGGER.debug("==================== enter ActivityResource deleteActivity ====================");
      LOGGER.debug("activityId: " + activityId);
      Activity activity = null;
      try {
    	activity = activityService.get(activityId);
      	activityService.delete(activityId);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
          LOGGER.debug(devMsg);
          if (ex.getCode() == ServiceException.CODE_NO_SUCH_ELEMENT) {
              throw new ApiException(404, devMsg, "该活动不存在！");
          }
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityResource deleteActivity ====================");
      //return activity;
  }	    
    
   
    @GET
    @Path("/publish")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
  public void publishActivity(@QueryParam("activityId") int activityId,@QueryParam("published") Activity.Published Published) throws ApiException {
      LOGGER.debug("==================== enter ActivityResource publishActivity ====================");
      LOGGER.debug("activityId: " + activityId);
      Activity activity = null;
      try {
    	activity = activityService.get(activityId);
    	activity.setPublished(Published);
      	activityService.update(activity);
		} catch (ServiceException ex) {
			String devMsg = "Service Exception [" + ex.getCode() + "] " + ex.getReason();
          LOGGER.debug(devMsg);
          if (ex.getCode() == ServiceException.CODE_NO_SUCH_ELEMENT) {
              throw new ApiException(404, devMsg, "该活动不存在！");
          }
			handleServiceException(ex);
		}
		LOGGER.debug("==================== leave ActivityResource publishActivity ====================");
      //return activity;
  }	
    
}
