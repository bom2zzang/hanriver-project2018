package hanriver.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hanriver.context.ApplicationContext;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ApplicationContext iocContainer = new ApplicationContext("hanriver");
            
            String resource = "hanriver/config/mybatis-config.xml";
            InputStream inputStream;
                inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            
            iocContainer.addBean("sqlSessionFactory", sqlSessionFactory);
            iocContainer.refresh();
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

