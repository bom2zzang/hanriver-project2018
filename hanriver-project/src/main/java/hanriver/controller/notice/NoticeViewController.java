package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hanriver.annotation.RequestMapping;
import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller("/notice/view")
public class NoticeViewController {
    
    @Autowired
    NoticeDao noticeDao;
    
    public NoticeViewController() {}

    public NoticeViewController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    
    @RequestMapping
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Notice notice = noticeDao.selectOne(request.getParameter("no"));
        request.setAttribute("notice", notice);
        return "/notice/view.jsp";
    }
}
