package thu.declan.xi.server.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import thu.declan.xi.server.Constant;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import thu.declan.xi.server.exception.ApiException;
import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Bill;
import thu.declan.xi.server.model.Invoice;
import thu.declan.xi.server.model.Invoice.InType;
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.model.Payroll;
import thu.declan.xi.server.model.Payroll.ApState;
import thu.declan.xi.server.service.BillService;
import thu.declan.xi.server.service.InvoiceService;
import thu.declan.xi.server.service.PayrollService;

/**
 * @author Sublime
 */
@Path("invoice")
@RolesAllowed({Constant.ROLE_ADMIN,Constant.ROLE_COMPANY})
public class InvoiceResource extends BaseResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryResource.class);
	
//	@Autowired
//	private SalaryTransitService salaryTransitService;
	
	@Autowired
	private InvoiceService invoiceService;
	
//	@Autowired
//	private CompanyService companyService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private PayrollService payrollService;

	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer createInvoice(@Valid SalaryTransit transit) throws ApiException{
		LOGGER.debug("==================== enter InvoiceResource createInvoice ====================");
		Integer compayId = transit.getComId();
		LOGGER.debug("companyId: " + compayId);
		Invoice inc = new Invoice();
		Integer id = null;
		try {
			Company company = companyService.get(compayId);
			Double money = salaryTransitService.getSum(transit);
			Integer numbre = salaryTransitService.getCount(transit);
			inc.setComEmail(company.getEmail());
			inc.setMoney(money);
			invoiceService.add(inc);
			id = inc.getId();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
		}
		LOGGER.debug("==================== leave InvoiceResource createInvoice ====================");
		return id;
	}

	@GET
	@Path("/{invoiceId}/query")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Invoice queryInvoice (@PathParam("invoiceId")Integer invoiceId) throws ApiException {
		LOGGER.debug("==================== enter InvoiceResource queryInvoice ====================");
		LOGGER.debug("invoiceId: " + invoiceId);
		Invoice invoice = null;
		try {
			invoice = invoiceService.get(invoiceId);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
		}
		LOGGER.debug("==================== leave InvoiceResource queryInvoice ====================");
		return invoice;
	}*/

	// TODO
	@GET
	@Path("bill/query")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Invoice queryBillById (@QueryParam("billId")Integer billId, @QueryParam("money")Double money) throws ApiException {
		LOGGER.debug("==================== enter InvoiceResource queryBillById ====================");
		LOGGER.debug("billId: " + billId);
		Bill bill = null;
		Invoice invoice = new Invoice();
		try {
			if (billId == null) {
				invoice.setMoney(money);
				invoice.setInvType(InType.USUAL);
			} else {
				bill = billService.get(billId);
				invoice.setInvTitle(bill.getInvTitle());
				invoice.setComEin(bill.getComEin());
				invoice.setComEmail(bill.getComEmail());
				invoice.setMoney(money);
				invoice.setInvType(InType.USUAL);
			}
		} catch (ServiceException e) {
//			e.printStackTrace();
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave InvoiceResource queryBillById ====================");
		return invoice;
	}
	
	@GET
	@Path("/invoices")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse<Invoice> queryInvoiceList(@QueryParam("applyState")List<Invoice.InState> applyState,
			@QueryParam("pageIndex") Integer pageIndex, @QueryParam("pageSize") Integer pageSize) throws ApiException {
		LOGGER.debug("==================== enter InvoiceResource queryInvoiceList ====================");
		Invoice inc = new Invoice();
		if (applyState != null && applyState.size() > 0) {
			inc.setQueryStates(applyState);
		}
		Pagination pagination = new Pagination(pageSize, pageIndex);
		List<Invoice> invs = null;
		try {
			invs = invoiceService.getList(inc, pagination);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave InvoiceResource queryInvoiceList ====================");
		return new ListResponse(invs, pagination);
	}
	
	@POST
	@Path("/{payrollId}/apply")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void applyInvoice(@Valid Invoice invoice) throws ApiException {
		LOGGER.debug("==================== enter InvoiceResource applyInvoice ====================");
		LOGGER.debug("payrollId: " + invoice.getPayId() + "compayId： " + invoice.getComId());
		Integer invId = null; 
		Integer comId = invoice.getComId();
		try {
			if (comId != null) {
				Payroll payroll = payrollService.get(invoice.getPayId());
				invoiceService.add(invoice);
				invId = invoice.getId();
				payroll.setApplyState(ApState.CHECK_PENDING);
				payroll.setInvId(invId);
				payrollService.update(payroll);
			} else {
				
			}
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave InvoiceResource applyInvoice ====================");
	}
	
	@PUT
	@Path("/{invoiceId}/updata")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updataInvoice(@Valid Invoice invoice) throws ApiException {
		LOGGER.debug("==================== enter InvoiceResource updataInvoice ====================");
		LOGGER.debug("invoiceId: " + invoice.getId());
		try {
			invoiceService.update(invoice);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave InvoiceResource updataInvoice ====================");
	}

}
