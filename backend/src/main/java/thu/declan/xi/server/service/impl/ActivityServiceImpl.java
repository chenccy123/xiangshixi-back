package thu.declan.xi.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.mapper.ActivityMapper;
import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.model.Activity;
import thu.declan.xi.server.service.ActivityService;

@Service("activityService")
public class ActivityServiceImpl extends BaseTableServiceImpl<Activity> implements ActivityService{

	@Autowired
	ActivityMapper activityMapper;
	
	@Override
	protected BaseMapper<Activity> getMapper() {
		return activityMapper;
	}
	
	@Override
	public void preAdd(Activity activity) throws ServiceException {
        if (activityMapper.selectCount(activity) > 0) {
            throw new ServiceException(ServiceException.CODE_DUPLICATE_ELEMENT, "Activity already exists.");
        }
	}
	
	@Override
	public void delete(int id) {
		activityMapper.delete(id);
	}
}
