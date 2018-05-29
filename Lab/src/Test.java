import IDAO.IUserDAO;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    String xmlPath = "springmvc-config.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

    private void login() throws Exception {
        IUserDAO userDAO = (IUserDAO) applicationContext.getBean("userDAO");
        User user = new User();
        user.setUserName("test1");
        user.setUserPassword("test");
        user.setUserNick("test");
        userDAO.insert(user);
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.login();
    }
}
