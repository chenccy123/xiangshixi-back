package thu.declan.xi.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import thu.declan.xi.server.mapper.ResumeMapper;
import thu.declan.xi.server.mapper.SalaryMapper;
import thu.declan.xi.server.model.Resume;
import thu.declan.xi.server.task.SalaryTask;
import thu.declan.xi.server.util.WorkingDaysUtils;

/**
 *
 * @author Sublime
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations = "classpath:spring.xml") // 加载配置
public class SalaryTaskTest {

    @Autowired
    private SalaryService salaryService;
	
	@Autowired
    private ResumeMapper resumeMapper;
	
	@Autowired
	private SalaryMapper salaryMapper;
	@Autowired
	private SalaryTask salaryTask;
	
	private static String firstDay;  
	private static String lastDay;
	
    @Test
    public void testAuthService() throws Exception {
        System.out.println("-------------testSalaryTask begin------------");
    }
    
    public static int getMonthLastDay(int year, int month){
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
    
    public Date ndaysBefore(int n) {
        return new Date((new Date()).getTime() - n * 24 * 60 * 60 * 1000);
    }
}
