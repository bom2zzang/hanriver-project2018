package hanriver.controller.notice;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hanriver.annotation.RequestMapping;
import hanriver.dao.NoticeDao;

@Controller("/notice/list")
public class NoticeListController {
    
    @Autowired
    NoticeDao noticeDao;
    
    
    
    public NoticeListController() {}

    public NoticeListController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    

    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
