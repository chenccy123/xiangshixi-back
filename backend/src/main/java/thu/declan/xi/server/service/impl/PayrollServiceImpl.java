package thu.declan.xi.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.mapper.PayrollMapper;
import thu.declan.xi.server.model.Payroll;
import thu.declan.xi.server.service.PayrollService;
/**
 * @author Sublime
 */
@Service("payrollService")
public class PayrollServiceImpl extends BaseTableServiceImpl<Payroll> implements PayrollService {

	@Autowired
	private PayrollMapper payrollMapper;

	@Override
	protected BaseMapper<Payroll> getMapper() {
		return payrollMapper;
	}


}
