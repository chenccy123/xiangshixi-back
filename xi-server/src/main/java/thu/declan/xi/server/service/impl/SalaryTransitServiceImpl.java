package thu.declan.xi.server.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.mapper.SalaryTransitMapper;
import thu.declan.xi.server.model.SalaryTransit;
import thu.declan.xi.server.service.SalaryTransitService;
/**
 * @author Sublime
 */
@Service("salaryTransitService")
public class SalaryTransitServiceImpl extends BaseTableServiceImpl<SalaryTransit> implements SalaryTransitService {

	@Autowired
	private SalaryTransitMapper salaryTransitMapper;

	@Override
	protected BaseMapper<SalaryTransit> getMapper() {
		return salaryTransitMapper;
	}

	@Override
	public void addList(List<SalaryTransit> sTransits) throws ServiceException {
		salaryTransitMapper.insertBatch(sTransits);
	}

	@Override
	public Double getSum(SalaryTransit transit) {
		return salaryTransitMapper.getSum(transit);
	}
	
}
