package thu.declan.xi.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thu.declan.xi.server.mapper.BaseMapper;
import thu.declan.xi.server.mapper.InvoiceMapper;
import thu.declan.xi.server.model.Invoice;
import thu.declan.xi.server.service.InvoiceService;
/**
 * @author Sublime
 */
@Service("invoiceService")
public class InvoiceServiceImpl extends BaseTableServiceImpl<Invoice> implements InvoiceService {

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	protected BaseMapper<Invoice> getMapper() {
		return invoiceMapper;
	}


}
