package thu.declan.xi.server.resource;

import java.util.Date;
import java.util.HashMap;
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
import thu.declan.xi.server.model.ListResponse;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.model.SalaryTransit;
import thu.declan.xi.server.model.SalaryTransit.State;
import thu.declan.xi.server.service.SalaryTransitService;
import thu.declan.xi.server.util.CommonUtils;

/**
 * @author Sublime
 */
@Path("salaryTransit")
@RolesAllowed({Constant.ROLE_ADMIN, Constant.ROLE_COMPANY})
public class SalaryTransitResource extends BaseResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryTransitResource.class);
	
	@Autowired
	private SalaryTransitService salaryTransitService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String importSalaryTransit(@Valid List<SalaryTransit> sTransitList) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource importSalaryTransit ====================");
		LOGGER.debug("sTransitList Size: " + sTransitList.size());
		String batch = CommonUtils.randomString(5);
		for (SalaryTransit salaryTransit : sTransitList) {
			if (salaryTransit.getDailyWage() < 1.00 || salaryTransit.getWorkDays() < 0.50 || salaryTransit.getDailyWage() > 300.00 ||  salaryTransit.getWorkDays() > 30.00) {
				salaryTransit.setPrompt("数据有误，薪资计算失败。");
			} else {
				salaryTransit.setSubTotal();
			}
			salaryTransit.setState(State.NEW);
			salaryTransit.setBatch(batch);
		}
		try {
			salaryTransitService.addList(sTransitList);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务错误");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource importSalaryTransit ====================");
		return batch;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updataSalaryTransit(@Valid SalaryTransit sTransit) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource updataSalaryTransit ====================");
		LOGGER.debug("companyId: " + sTransit.getComId());
		try {
			List<SalaryTransit> list = salaryTransitService.getList(sTransit);
			for (SalaryTransit salaryTransit : list) {
				salaryTransit.setState(State.DISCARDED);
				salaryTransitService.update(salaryTransit);
			}
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务错误");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource updataSalaryTransit ====================");
	}

	@PUT
	@Path("/matching")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<SalaryTransit> matchingStuData (@Valid SalaryTransit transit) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource matchingStuData ====================");
		Integer compayId = transit.getComId();
		LOGGER.debug("companyId: " + compayId + " batch: " + transit.getBatch());
		List<SalaryTransit> list = null;
		State state = State.EFFECTIVE;
    	try {
			list = salaryTransitService.getList(transit);
			LOGGER.debug("SalaryTransitList size: " + list.size());
			for (SalaryTransit salaryTransit : list) {
				if (salaryTransit.getStudent() != null) {
					salaryTransit.setStuId(salaryTransit.getStudent().getId());
					salaryTransit.setAccId(salaryTransit.getStudent().getAccountId());
					if (salaryTransit.getPrompt() != null) {
						state = State.EXCEPTION;
					}
				}else {
					state = State.INVALID;
					salaryTransit.setPrompt("未匹配成功，手机号有误，或该学生未在平台注册。");
				}
				salaryTransit.setState(state);
				salaryTransitService.update(salaryTransit);
				state = State.EFFECTIVE;
			}
		} catch (ServiceException e) {
//			throw new ApiException(e.getCode(), e.getMessage(), "服务错误");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource matchingStuData ====================");
		return list;
	}

	@POST
	@Path("/count")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Integer> countNumber(@Valid SalaryTransit transit) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource countNumber ====================");
		LOGGER.debug("companyId: " + transit.getComId());
		HashMap<String, Integer> cnts = new HashMap<>();
		cnts.put("TOTAL", salaryTransitService.getCount(transit));
		for (State s : SalaryTransit.State.values()) {
			transit.setState(s);
			cnts.put(s.toString(), salaryTransitService.getCount(transit));
		}
		LOGGER.debug("cnts : " + cnts.toString());
		LOGGER.debug("==================== leave SalaryTransitResource countNumber ====================");
		return cnts;
	}

	@POST
	@Path("/sum")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Double> getSum(@Valid SalaryTransit transit) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource getSum ====================");
		LOGGER.debug("companyId: " + transit.getComId());
		HashMap<String, Double> cnts = new HashMap<>();
		cnts.put("SUM", salaryTransitService.getSum(transit));
		LOGGER.debug("cnts : " + cnts.toString());
		LOGGER.debug("==================== leave SalaryTransitResource getSum ====================");
		return cnts;
	}

	@PUT
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalaryTransit> getSalaryTransits (@Valid SalaryTransit transit) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource getSalaryTransits ====================");
		Integer compayId = transit.getComId();
		Integer payrollId = transit.getPayId();
		LOGGER.debug("companyId: " + compayId + " payrollId: " + payrollId);
		List<SalaryTransit> list = null;
		Date subDate = new Date();
		transit.setPayId(null);
		try {
			list = salaryTransitService.getList(transit);
			for (SalaryTransit salaryTransit : list) {
				salaryTransit.setState(State.SUCCESS);
				salaryTransit.setSubmitDate(subDate);
				salaryTransit.setPayId(payrollId);
				salaryTransitService.update(salaryTransit);
			}
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource getSalaryTransits ====================");
		return list;
	}

	@GET
	@Path("/{payrollId}/salaryTransits")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse<SalaryTransit> querySalaryTransitByPayrollId(
			@QueryParam("payrollId") Integer payrollId,
			@QueryParam("pageIndex") Integer pageIndex,
			@QueryParam("pageSize") Integer pageSize) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource querySalaryTransitByInvoiceId ====================");
		LOGGER.debug("payrollId: " + payrollId);
		SalaryTransit sTransit = new SalaryTransit();
		Pagination pagination = new Pagination(pageSize, pageIndex);
		List<SalaryTransit> stList = null;
		sTransit.setPayId(payrollId);
		try {
			stList = salaryTransitService.getList(sTransit, pagination);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource querySalaryTransitByInvoiceId ====================");
		return new ListResponse(stList, pagination);
	}

	@GET
	@Path("/{compayId}/salaries")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse<SalaryTransit> querySalaryTransitList(@QueryParam("compayId") Integer compayId,
			@QueryParam("month") String month,
			@QueryParam("state") List<SalaryTransit.State> states,
			@QueryParam("pageIndex") Integer pageIndex,
			@QueryParam("pageSize") Integer pageSize) throws ApiException {
		LOGGER.debug("==================== enter SalaryTransitResource querySalaryTransitList ====================");
		LOGGER.debug("compayId: " + compayId);
		SalaryTransit sTransit = new SalaryTransit();
		Pagination pagination = new Pagination(pageSize, pageIndex);
		List<SalaryTransit> stList = null;
		sTransit.setComId(compayId);
		sTransit.setMonth(month);
		if (states != null && states.size() > 0) {
			sTransit.setQueryStates(states);
		}
		try {
			stList = salaryTransitService.getList(sTransit, pagination);
		} catch (ServiceException e) {
//			e.printStackTrace();
//			throw new ApiException(e.getCode(), e.getMessage(), "服务异常");
			String devMsg = "Service Exception [" + e.getCode() + "] " + e.getReason();
            LOGGER.debug(devMsg);
			handleServiceException(e);
		}
		LOGGER.debug("==================== leave SalaryTransitResource querySalaryTransitList ====================");
		return new ListResponse(stList, pagination);
	}

}
