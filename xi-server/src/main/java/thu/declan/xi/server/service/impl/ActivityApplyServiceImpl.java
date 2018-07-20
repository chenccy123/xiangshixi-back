package thu.declan.xi.server.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.mapper.ActivityApplyMapper;
import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.model.ActivityApply;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.service.ActivityApplyService;

/**
 * 
 * @author chency 2018-06-20
 *
 */
@Service("activityApplyService")
public class ActivityApplyServiceImpl extends BaseTableServiceImpl<ActivityApply> implements ActivityApplyService {

	@Autowired
	ActivityApplyMapper activityApplyMapper;
	
	@Override
	protected BaseMapper<ActivityApply> getMapper() {
		return activityApplyMapper;
	}
	
	
    @Override
    public List<ActivityApply> getListforStudent(ActivityApply act, Pagination pagination) throws ServiceException {
        int limit = pagination.getPageSize();
		int offset = (pagination.getPageIndex() - 1) * limit;
		List<ActivityApply> objects = activityApplyMapper.selectListForStudent(act, new RowBounds(offset, limit));
		int count = activityApplyMapper.selectCountForStudent(act);
		pagination.setRowCnt(count);
		pagination.setPageCnt((count - 1) / limit + 1);
        return objects;
    }
    
    @Override
    public List<ActivityApply> getListforCompany(ActivityApply act, Pagination pagination) throws ServiceException {
        int limit = pagination.getPageSize();
		int offset = (pagination.getPageIndex() - 1) * limit;
		List<ActivityApply> objects = activityApplyMapper.selectListForCompany(act, new RowBounds(offset, limit));
		int count = activityApplyMapper.selectCountForCompany(act);
		pagination.setRowCnt(count);
		pagination.setPageCnt((count - 1) / limit + 1);
        return objects;
    }
    
}
