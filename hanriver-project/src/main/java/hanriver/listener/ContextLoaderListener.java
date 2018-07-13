package hanriver.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext("hanriver/config/application-context.xml");
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

