package thu.declan.xi.server.service;

import java.util.List;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.SalaryTransit;
/**
 * 
 * @author Sublime
 *
 */
public interface SalaryTransitService extends BaseTableService<SalaryTransit> {

	public void addList(List<SalaryTransit> sTransits) throws ServiceException;

	public Double getSum(SalaryTransit transit);

}
