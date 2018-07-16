package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller
public class NoticeViewController {
    
    @Autowired
    NoticeDao noticeDao;
    
    public NoticeViewController() {}

    public NoticeViewController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    
    @RequestMapping("/notice/view")
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Notice notice = noticeDao.selectOne(request.getParameter("no"));
        request.setAttribute("notice", notice);
        return "notice/view";
    }
}
