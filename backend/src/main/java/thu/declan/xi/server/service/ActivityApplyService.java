package thu.declan.xi.server.service;

import java.util.List;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.ActivityApply;
import thu.declan.xi.server.model.Pagination;

/**
 * 活动申请服务类接口
 * @author chency
 *
 */
public interface ActivityApplyService extends BaseTableService<ActivityApply>{

	public List<ActivityApply> getListforStudent(ActivityApply act, Pagination pagination) throws ServiceException;
	
	public List<ActivityApply> getListforCompany(ActivityApply act, Pagination pagination) throws ServiceException;
	
	//public Integer getCountforCompany(ActivityApply act) ;
	
	//public Integer getCountforStudent(ActivityApply act) ;
}
