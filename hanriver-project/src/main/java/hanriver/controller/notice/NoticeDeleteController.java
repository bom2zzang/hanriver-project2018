package hanriver.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hanriver.annotation.RequestMapping;
import hanriver.dao.NoticeDao;

@Controller("/notice/delete")
public class NoticeDeleteController {
    
    @Autowired
    NoticeDao noticeDao;
    
    public NoticeDeleteController() {}

    public NoticeDeleteController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    

    @RequestMapping
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        noticeDao.delete(request.getParameter("no"));
        return "redirect:list";
    }
}
