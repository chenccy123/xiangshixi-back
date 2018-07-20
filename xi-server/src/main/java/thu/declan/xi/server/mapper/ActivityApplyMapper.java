package thu.declan.xi.server.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import thu.declan.xi.server.model.ActivityApply;

/**
 * 
 * @author chency
 * @version 1.0 2018-06-20
 */
public interface ActivityApplyMapper extends BaseMapper<ActivityApply>{

	List<ActivityApply> selectListForStudent(ActivityApply act, RowBounds bounds);
	
	Integer selectCountForStudent(ActivityApply act);
	
	List<ActivityApply> selectListForCompany(ActivityApply act, RowBounds bounds);
	
	Integer selectCountForCompany(ActivityApply act);
}
