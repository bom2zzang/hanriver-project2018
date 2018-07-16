package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller
public class NoticeUpdateController {
    
    @Autowired
    NoticeDao noticeDao;
    
    public NoticeUpdateController() {}

    public NoticeUpdateController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    
    @RequestMapping("/notice/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("title"));
        notice.setContents(request.getParameter("contents"));
        notice.setNo(Integer.parseInt(request.getParameter("no")));
        
        if (noticeDao.update(notice) == 0) {
            return "notice/updatefail";
        } else {
            return "redirect:list";
        }
    }
}
