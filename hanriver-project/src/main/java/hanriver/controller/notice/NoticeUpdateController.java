package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;


public class NoticeUpdateController implements PageController {
    
    NoticeDao noticeDao;
    
    public NoticeUpdateController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("title"));
        notice.setContents(request.getParameter("contents"));
        notice.setNo(Integer.parseInt(request.getParameter("no")));
        
        if (noticeDao.update(notice) == 0) {
            return "/notice/updatefail.jsp";
        } else {
            return "redirect:list";
        }
    }
}
