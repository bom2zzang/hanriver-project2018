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

import hanriver.controller.member.MemberAddController;
import hanriver.controller.member.MemberDeleteController;
import hanriver.controller.member.MemberListController;
import hanriver.controller.member.MemberUpdateController;
import hanriver.controller.member.MemberViewController;
import hanriver.controller.notice.NoticeAddController;
import hanriver.controller.notice.NoticeDeleteController;
import hanriver.controller.notice.NoticeListController;
import hanriver.controller.notice.NoticeUpdateController;
import hanriver.controller.notice.NoticeViewController;
import hanriver.dao.MemberDao;
import hanriver.dao.NoticeDao;


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
        sc.setAttribute("/member/list", new MemberListController(memberDao));
        sc.setAttribute("/member/add", new MemberAddController(memberDao));
        sc.setAttribute("/member/update", new MemberUpdateController(memberDao));
        sc.setAttribute("/member/view", new MemberViewController(memberDao));
        sc.setAttribute("/member/delete", new MemberDeleteController(memberDao));
        sc.setAttribute("/notice/list", new NoticeListController(noticeDao));
        sc.setAttribute("/notice/add", new NoticeAddController(noticeDao));
        sc.setAttribute("/notice/update", new NoticeUpdateController(noticeDao));
        sc.setAttribute("/notice/view", new NoticeViewController(noticeDao));
        sc.setAttribute("/notice/delete", new NoticeDeleteController(noticeDao));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

