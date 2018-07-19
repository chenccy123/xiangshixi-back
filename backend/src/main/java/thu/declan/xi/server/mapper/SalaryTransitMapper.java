package thu.declan.xi.server.mapper;


import java.util.List;

import thu.declan.xi.server.model.SalaryTransit;

/**
 * 
 * @author Sublime
 *
 */
public interface SalaryTransitMapper extends BaseMapper<SalaryTransit>{

	public void insertBatch(List<SalaryTransit> list);

	public Double getSum(SalaryTransit transit);

}
