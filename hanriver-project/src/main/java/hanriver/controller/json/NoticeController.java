package hanriver.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hanriver.domain.Notice;
import hanriver.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    
    @Autowired NoticeService noticeService;

    @GetMapping("form")
    public void form() {
    }
    
    @PostMapping("add")
    public String add(Notice notice) {
        noticeService.add(notice);
        return "redirect:list";
    }
    
    @RequestMapping("view/{no}")
    public Object view(@PathVariable String no) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("notice", noticeService.get(no));
        return data;
    }
    
    @RequestMapping("update")
    public String update(Notice notice) throws Exception {
        if (noticeService.update(notice) == 0) {
            return "notice/updatefail";
        } else {
            return "redirect:list";
        }
    }
    
    @RequestMapping("list")
    public Object list(
            @RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="10")int size) throws Exception {
        if (page < 1) page = 1; 
        if (size < 1 || size > 20) page = 10;
        HashMap<String, Object> data = new HashMap<>();
        data.put("list", noticeService.list(page, size));
        data.put("totalPage", noticeService.countAll(size));
        data.put("page", page);
        data.put("size", size);
        
        return data;
    }
    
    @RequestMapping("delete")
    public String delete(String no) throws Exception {
        noticeService.delete(no);
        return "redirect:list";
    }
}
