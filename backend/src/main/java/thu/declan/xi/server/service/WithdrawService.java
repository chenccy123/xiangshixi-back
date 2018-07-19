package thu.declan.xi.server.service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Account;
import thu.declan.xi.server.model.Withdraw;

/**
 *
 * @author declan
 */
public interface WithdrawService extends BaseTableService<Withdraw> {
	
	public void payWithdraw(int id) throws ServiceException;
	
	public void insert(Withdraw with) throws ServiceException;
	
	/**
	 *  二审通过
	 *  重新减去申请金额并支付
	 * @param wit
	 * @throws ServiceException
	 */
	public void modifyWithdraw(Withdraw wit) throws ServiceException;


	/**
	 *发放薪资
	 * @param id  account  openId money month
	 * @param month 
	 */
	public void payWages(Integer id, Account account, String openId, Double money, String month) throws ServiceException;


}
