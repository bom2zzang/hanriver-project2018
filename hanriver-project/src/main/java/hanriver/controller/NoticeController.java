package hanriver.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    
    @Autowired NoticeDao noticeDao;

    @GetMapping("form")
    public void form() {
    }
    
    @PostMapping("add")
    public String add(Notice notice) {
        noticeDao.insert(notice);
        return "redirect:list";
    }
    
    @RequestMapping("view/{no}")
    public String view(@PathVariable String no, Model model) throws Exception {
        Notice notice = noticeDao.selectOne(no);
        model.addAttribute("notice", notice);
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
    public String list(
            @RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="10")int size, Model model) throws Exception {
        if (page < 1) page = 1;
        if (size < 1 || size > 20) page = 10;
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
        model.addAttribute("list", noticeDao.selectList(params));
        return "notice/list";
    }
    
    @RequestMapping("delete")
    public String delete(String no) throws Exception {
        noticeDao.delete(no);
        return "redirect:list";
    }
}
