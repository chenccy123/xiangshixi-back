package thu.declan.xi.server.resource;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import thu.declan.xi.server.Constant;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import thu.declan.xi.server.exception.ApiException;
import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Company;
import thu.declan.xi.server.model.Payroll;
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.model.Payroll.ApState;
import thu.declan.xi.server.model.Payroll.PaState;
import thu.declan.xi.server.model.SalaryTransit;
import thu.declan.xi.server.service.CompanyService;
import thu.declan.xi.server.service.PayrollService;
import thu.declan.xi.server.service.SalaryTransitService;

/**
 * @author Sublime
 */
@Path("payroll")
@RolesAllowed({Constant.ROLE_ADMIN, Constant.ROLE_COMPANY})
public class PayrollResource extends BaseResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryResource.class);
	
	@Autowired
	private SalaryTransitService salaryTransitService;
	
	@Autowired
	private PayrollService payrollService;
	
	@Autowired
	private CompanyService companyService;


	/*@PUT
	@Path("/{payrollId}/apply")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Payroll editPayroll(@Valid Payroll payroll) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource editPayroll ====================");
		LOGGER.debug("payrollId: " + payroll.getId());
		Payroll pay = null;
		try {
//			Company company = companyService.get(payroll.getCompayId());
//			company.setEin(payroll.getComEin());
//			companyService.update(company);
			payrollService.update(payroll);
			pay = payrollService.get(payroll.getId());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
		}
		LOGGER.debug("==================== leave PayrollResource editPayroll ====================");
		return pay;
	}

	@PUT
	@Path("/{payrollId}/updata")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updataPayroll(@Valid Payroll payroll) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource updataPayroll ====================");
		LOGGER.debug("payrollId: " + payroll.getId());
		try {
			payrollService.update(payroll);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
		}
		LOGGER.debug("==================== leave PayrollResource updataPayroll ====================");
	}

	@POST
	@Path("/{payrollId}/payment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void paymentPayroll(@Valid Payroll pay) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource paymentPayroll ====================");
		LOGGER.debug("payrollId: " + pay.getId());
		
		
		
		
		LOGGER.debug("==================== leave PayrollResource paymentPayroll ====================");
	}*/	

	// TODO
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer createPayroll(@Valid SalaryTransit transit) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource createPayroll ====================");
		Integer compayId = transit.getComId();
		LOGGER.debug("companyId: " + compayId);
		Payroll pay = new Payroll();
		Date generateDate = new Date();
		Integer id = null;
		try {
			Company company = companyService.get(compayId);
			Double total = salaryTransitService.getSum(transit);
			Integer numbre = salaryTransitService.getCount(transit);
			pay.setComId(compayId);
			pay.setComName(company.getName());
			pay.setMonth(transit.getMonth());
			pay.setNumber(numbre);
			pay.setTotal(total);
			pay.setApplyState(ApState.NOT_APPLY);
			pay.setPayState(PaState.NOT_PAYING);
			pay.setGenerateDate(generateDate);
			payrollService.add(pay);
			id = pay.getId();
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave PayrollResource createPayroll ====================");
		return id;
	}
	
/*	@POST
	@Path("/{payrollId}/payment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({Constant.ROLE_ADMIN})
	public void paymentPayroll(@Valid Payroll pay) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource paymentPayroll ====================");
		LOGGER.debug("payrollId: " + pay.getId());
		SalaryTransit sTransit = new SalaryTransit();
		Withdraw with = new Withdraw();
		Date createTime = new Date();
		List<SalaryTransit> list = null;
		sTransit.setPayId(pay.getId());
		try {
			list = salaryTransitService.getList(sTransit);
			for (SalaryTransit st : list) {
				if (st.getAccount() != null && st.getAccount().getOpenId() != null) {
					// 已绑定微信 可付款至零钱
					with.setAccountId(st.getAccId());
					with.setState(WState.PASSED); //  设置状态通过
					with.setChannel(Channel.WECHAT); // 设置付款渠道 
					with.setValue(st.getSubTotal());
					with.setCreateTime(createTime);
//					with.setAccount(st.getAccount());
					
				}
				//未绑定微信
				st.setState(State.SALARY_ERROR); // 设置发放状态错误
				st.setPrompt("该用户未绑定微信，无法发放工资。"); // 设置 错误描述 
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ApiException(e.getCode(), e.getMessage(), "数据异常");
		}
		
		
		
		
		LOGGER.debug("==================== leave PayrollResource paymentPayroll ====================");
	}*/

	@GET
	@Path("/{payrollId}/query")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Payroll queryPayroll (@PathParam("payrollId")Integer payrollId) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource queryPayroll ====================");
		LOGGER.debug("payrollId: " + payrollId);
		Payroll payroll = null;
		try {
			payroll = payrollService.get(payrollId);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave PayrollResource queryPayroll ====================");
		return payroll;
	}
	
	@GET
	@Path("/payrolls")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse<Payroll> queryPayrollList(@QueryParam("pageIndex") Integer pageIndex,@QueryParam("pageSize") Integer pageSize) throws ApiException {
		LOGGER.debug("==================== enter PayrollResource queryPayrollList ====================");
		Pagination pagination = new Pagination(pageSize, pageIndex);
		List<Payroll> invs = null;
		try {
			invs = payrollService.getList(null,pagination);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave PayrollResource queryPayrollList ====================");
		return new ListResponse(invs, pagination);
	}
	

}
