package hanriver.controller.notice;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.NoticeDao;


public class NoticeListController implements PageController {
    
    NoticeDao noticeDao;
    
    public NoticeListController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }


    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        }
            request.setAttribute("list", noticeDao.selectList(params));
            return "/notice/list.jsp";
    }
}
