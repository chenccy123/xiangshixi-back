package thu.declan.xi.server.service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Activity;

/**
 * 活动服务类接口
 * @author chency
 * @version 1.0,2018-06-20
 * 
 */
public interface ActivityService extends BaseTableService<Activity>{

	public void preAdd(Activity act) throws ServiceException;
	
	public void delete(int id);
	
}
