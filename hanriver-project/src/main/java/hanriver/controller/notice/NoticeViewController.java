package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;


public class NoticeViewController implements PageController {
    NoticeDao noticeDao;
    
    public NoticeViewController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Notice notice = noticeDao.selectOne(request.getParameter("no"));
        request.setAttribute("notice", notice);
        return "/notice/view.jsp";
    }
}
