package thu.declan.xi.server.resource;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import thu.declan.xi.server.Constant;
import thu.declan.xi.server.exception.ApiException;
import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Bill;
import thu.declan.xi.server.model.Company;
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.service.BillService;
import thu.declan.xi.server.service.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sublime
 */
@Path("bill")
@RolesAllowed({Constant.ROLE_COMPANY,Constant.ROLE_ADMIN})
public class BillResource extends BaseResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BillResource.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private CompanyService companyService;
	
	@GET
	@Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
	public ListResponse<Bill> getBillByCompayId(@QueryParam("comId")Integer compayId) throws ApiException {
		LOGGER.debug("==================== enter BillResource getBillByCompayId ====================");
		LOGGER.debug("compayId: " + compayId);
		List<Bill> list = null;
		Bill bill = new Bill();
		bill.setComId(compayId);
		try {
			list = billService.getList(bill);
		} catch (ServiceException e) {
//			e.printStackTrace();
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave BillResource getBillByCompayId ====================");
		return new ListResponse<Bill>(list);
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createBill(@Valid Bill bill) throws ApiException {
		LOGGER.debug("==================== enter BillResource createBill ====================");
		LOGGER.debug("compayId: " + bill.getComId());
		try {
			billService.add(bill);
		} catch (ServiceException e) {
//			e.printStackTrace();
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave BillResource createBill ====================");
	}
	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBill(@Valid Bill bill) throws ApiException {
		LOGGER.debug("==================== enter BillResource deleteBill ====================");
		LOGGER.debug("billId : " + bill.getId() + "compayId : " + bill.getComId());
		Company company = null;
		try {
			company = companyService.get(bill.getComId());
			if (company.getBillId() == bill.getId()) {
				company.setBillId(0);
				companyService.update(company);
			}
			billService.delete(bill.getId());
		} catch (ServiceException e) {
//			e.printStackTrace();
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave BillResource updataBill ====================");
	}

}
