package thu.declan.xi.server.service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Bill;
/**
 * 
 * @author Sublime
 */
public interface BillService extends BaseTableService<Bill> {

	public void delete(Integer id) throws ServiceException;

}
