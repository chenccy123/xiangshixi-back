package thu.declan.xi.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import thu.declan.xi.server.mapper.StudentMapper;
import thu.declan.xi.server.model.Notification;

/**
 *
 * @stuanyor declan
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations = "classpath:spring.xml") // 加载配置
public class StudentServiceTest {

    @Autowired
    private StudentService stuanyService;
	
	@Autowired
	private StudentMapper stuanyMapper;
	
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private NotificationService notiService;
    
    @Test
    public void testStudentService() {
        System.out.println("-------------testStudentService begin------------");
//		final String phone = CommonUtils.randomString(11);
//        Account acc = new Account();
//        acc.setPhone(phone);
//        acc.setPassword(phone);
//        try {
//            accountService.add(acc);
//        } catch (ServiceException ex) {
//            fail("Add account failed: " + ex.getReason());
//			return;
//        }
//		Student stu = new Student();
//		stu.setAccountId(acc.getId());
//		stu.setArea("string");
//        stu.setAvatar("string");
//        stu.setCerts(new ArrayList<String>());
//        stu.setEducation(Student.Education.BACHELOR);
//        stu.setEmail("string");
//        stu.setGender(Student.Gender.MALE);
//        stu.setGrade(Integer.SIZE);
//        stu.setLangLevel(Student.LangLevel.NORMAL);
//        stu.setLanguage("string");
//        stu.setMajor("string");
//		stu.setName("stuany");
//		stu.setPhone(phone);
//		stu.setSchool("string");
//        stu.setStuCard("string");
//		try {
//			stuanyService.add(stu);
//		} catch (ServiceException ex) {
//			fail("Add student failed: " + ex.getReason());
//			return;
//		}
//		stuanyMapper.delete(stu.getId());
        
        Notification noti = notiService.addNoti(313, Notification.NType.SALARY, 39, true,
				Notification.TPL_SALARY_OUT, "中国量子科技研究院", "2018-08");
        System.out.println("noti : " + noti.toString());
        System.out.println("-------------testStudentService end------------");
    }
    
}
