package hanriver.listener;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hanriver.dao.NoticeDao;
import hanriver.dao.MemberDao;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
        String resource = "hanriver/config/mybatis-config.xml";
        InputStream inputStream;
            inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
        
        MemberDao memberDao = new MemberDao(sqlSessionFactory);
        NoticeDao noticeDao= new NoticeDao(sqlSessionFactory);
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("memberDao", memberDao);
        sc.setAttribute("noticeDao", noticeDao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

