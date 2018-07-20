package thu.declan.xi.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.mapper.BillMapper;
import thu.declan.xi.server.model.Bill;
import thu.declan.xi.server.service.BillService;
/**
 * @author Sublime
 */
@Service("billService")
public class BillServiceImpl extends BaseTableServiceImpl<Bill> implements BillService {

	@Autowired
	private BillMapper billMapper;

	@Override
	protected BaseMapper<Bill> getMapper() {
		return billMapper;
	}


}
