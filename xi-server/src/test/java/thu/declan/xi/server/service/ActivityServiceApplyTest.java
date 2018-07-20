package thu.declan.xi.server.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.ActivityApply;
import thu.declan.xi.server.model.Pagination;
import thu.declan.xi.server.model.ActivityApply.ACTRole;
import thu.declan.xi.server.model.ActivityApply.APCheck;
import thu.declan.xi.server.model.ActivityApply.ApplyStatus;
import thu.declan.xi.server.model.ActivityApply.PayStatus;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations = "classpath:spring.xml") // 加载配置
public class ActivityServiceApplyTest {

    @Autowired
    private ActivityApplyService activityApplyService;
    
    @Test
    public void testActivityApplyService() {
        System.out.println("-------------testActivityApplyService begin------------");

        java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
		ActivityApply act = new ActivityApply();
		ActivityApply act1 = new ActivityApply();
		List<ActivityApply> list = null;
		act.setAccountId(4);
		act.setActivityId(3);
		act.setAllowType(ACTRole.COMPANY);
		act.setApplyStatus(ApplyStatus.APPLYING);
		act.setApplyTime(utilDate);
		act.setCheckStatus(APCheck.NOTCHECK);
        act.setCheckTime(utilDate);
        act.setCheckUserId(1);
        act.setPayFee(1000.0);
        act.setPayStatus(PayStatus.PAYING);
        act.setPayTime(utilDate);
        act.setPayType("支付宝");
        
		//act1.setAccountId(5);
		act1.setActivityId(3);
		act1.setAllowType(ACTRole.COMPANY);
		//act1.setApplyStatus(ApplyStatus.APPLYING);
		//act1.setApplyTime(utilDate);
		//act1.setCheckStatus(APCheck.NOTCHECK);
        //act1.setCheckTime(utilDate);
        //act1.setCheckUserId(1);
       // act1.setPayFee(1000.0);
       // act1.setPayStatus(PayStatus.PAYING);
       // act1.setPayTime(utilDate);
        //act1.setPayType("支付宝");
        Pagination pagination = new Pagination(20, 1);
        try {
        	//activityApplyService.add(act);
        	//activityApplyService.add(act1);
        	//act1 = activityApplyService.get(1);
        	list = activityApplyService.getListforCompany(act1, pagination);
//        	list = activityApplyService.getList(act1, pagination);
        	//activityApplyService.update(act1);
        }
        catch (ServiceException ex) {
			fail("Add account failed: " + ex.getReason());
			return;
		}
        

        System.out.println("-------------testActivityApplyService end------------");
    	
    }
}
