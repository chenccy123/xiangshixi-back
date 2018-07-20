package thu.declan.xi.server.service;

import java.util.List;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Activity;
import thu.declan.xi.server.model.Pagination;

/**
 * 活动服务类接口
 * @author chency
 * @version 1.0,2018-06-20
 * 
 */
public interface ActivityService extends BaseTableService<Activity>{

	public void preAdd(Activity act) throws ServiceException;
	
	public void delete(int id);
	
	public List<Activity> getListforCompany(Activity act, Pagination pagination) throws ServiceException;
}
