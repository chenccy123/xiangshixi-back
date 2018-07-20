package thu.declan.xi.server.mapper;


import java.util.List;

import org.apache.ibatis.session.RowBounds;

import thu.declan.xi.server.model.Activity;


/**
 * 
 * @author chency
 * @version 1.0 2018-06-20
 * 
 */

public interface ActivityMapper extends BaseMapper<Activity> {

	List<Activity> selectListForCompany(Activity act, RowBounds bounds);
}
