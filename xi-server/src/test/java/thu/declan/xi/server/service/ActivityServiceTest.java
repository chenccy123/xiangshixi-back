package thu.declan.xi.server.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import thu.declan.xi.server.exception.ServiceException;
import thu.declan.xi.server.model.Activity;
import thu.declan.xi.server.model.Activity.ATarget;
import thu.declan.xi.server.model.Activity.Charge;
import thu.declan.xi.server.model.Activity.Published;
import thu.declan.xi.server.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations = "classpath:spring.xml") // 加载配置

/**
 * activity测试类
 * @author chency 2018-06-21
 *
 */
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;
    
    @Test
    public void testActivityService() {
        System.out.println("-------------testActivityService begin------------");
        
        /*
		final String title = CommonUtils.randomString(11);
        java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
		Activity act = new Activity();
		Activity act1 = new Activity();
		List<Activity> list = null;
		act.setTitle("qqqqq陈从sdgsdf" + title);
		act.setContent("ddddd");
		act.setCreateTime(utilDate);
		act.setActivityAddress("ddddd1");
		act.setActivityTime("ddddd1");
		act.setAllowTarget(AllowTarget.ISALLOWCOMPANY);
		act.setBeginTime(utilDate);
		act.setEndTime(utilDate);
		act.setChargeFee(2000.00);
		act.setChargeType("dddddd");
		act.setIsCharge(Charge.ISCHARGED);
		act.setPublished(Published.NOTPUBLISH);
		act.setCreateUserId(1);
		*/
       // activityService.delete(9);

        
        System.out.println("-------------testActivityService end------------");
    }
}
