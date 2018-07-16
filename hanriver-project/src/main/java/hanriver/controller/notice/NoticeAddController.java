package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller
public class NoticeAddController {
    
    @Autowired
    NoticeDao noticeDao;
    
    
    
    public NoticeAddController() {}

    public NoticeAddController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    

    @RequestMapping("/notice/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "notice/form";
        }
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("title"));
        notice.setContents(request.getParameter("contents"));
        noticeDao.insert(notice);
        return "redirect:list";
    }
}
