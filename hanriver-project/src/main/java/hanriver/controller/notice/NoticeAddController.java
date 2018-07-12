package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.annotation.Autowired;
import hanriver.annotation.Controller;
import hanriver.annotation.RequestMapping;
import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller("/notice/add")
public class NoticeAddController {
    
    NoticeDao noticeDao;
    
    
    
    public NoticeAddController() {}

    public NoticeAddController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    
    @Autowired
    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @RequestMapping
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/notice/form.jsp";
        }
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("title"));
        notice.setContents(request.getParameter("contents"));
        noticeDao.insert(notice);
        return "redirect:list";
    }
}
