package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hanriver.dao.NoticeDao;

@Controller
public class NoticeDeleteController {
    
    @Autowired
    NoticeDao noticeDao;
    
    public NoticeDeleteController() {}

    public NoticeDeleteController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    

    @RequestMapping("/notice/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        noticeDao.delete(request.getParameter("no"));
        return "redirect:list";
    }
}
