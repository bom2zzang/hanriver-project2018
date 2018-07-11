package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;


public class NoticeAddController implements PageController {
    
    NoticeDao noticeDao;
    
    public NoticeAddController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
