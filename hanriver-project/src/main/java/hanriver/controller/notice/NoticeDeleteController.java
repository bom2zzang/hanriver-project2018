package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.NoticeDao;


public class NoticeDeleteController implements PageController {
    
    NoticeDao noticeDao;
    
    public NoticeDeleteController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        noticeDao.delete(request.getParameter("no"));
        return "redirect:list";
    }
}
