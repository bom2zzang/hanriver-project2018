package hanriver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    
    NoticeDao noticeDao;
    
    public NoticeController(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
    

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add() {
        return "notice/form";
    }
    
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Notice notice) {
        noticeDao.insert(notice);
        return "redirect:list";
    }
    
    @RequestMapping("view")
    public String view(String no, Map<String, Object> map) throws Exception {
        Notice notice = noticeDao.selectOne(no);
        map.put("notice", notice);
        return "notice/view";
    }
    
    @RequestMapping("update")
    public String update(Notice notice) throws Exception {
        if (noticeDao.update(notice) == 0) {
            return "notice/updatefail";
        } else {
            return "redirect:list";
        }
    }
    
    @RequestMapping("list")
    public String list(String page, String size, Map<String, Object> map) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (page != null && size != null) {
            params.put("startIndex", (Integer.parseInt(page) - 1) * Integer.parseInt(size));
            params.put("pageSize", Integer.parseInt(size));
        }
        map.put("list", noticeDao.selectList(params));
        return "notice/list";
    }
    
    @RequestMapping("delete")
    public String delete(String no) throws Exception {
        noticeDao.delete(no);
        return "redirect:list";
    }
}
