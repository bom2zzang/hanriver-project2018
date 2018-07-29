package hanriver.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hanriver.domain.Member;
import hanriver.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    
    @Autowired MemberService memberService;
    
    @GetMapping("form")
    public void add() {
    }
    @PostMapping("add")
    public String add(Member member) throws Exception {
        memberService.add(member);
        return "redirect:list";
    }
    
    @RequestMapping("delete")
    public String delete(String id) throws Exception {
        memberService.delete(id);
        return "redirect:list";
    }
    
    @RequestMapping("list")
    public Object list(
            @RequestParam(defaultValue="1")int page,
            @RequestParam(defaultValue="10")int size) throws Exception {
        if (page < 1) page = 1;
        if (size < 1 || size > 20) size = 10;
        HashMap<String, Object> data = new HashMap<>();
        data.put("list", memberService.list(page, size));
        data.put("size", size);
        data.put("page", page);
        data.put("totalPage", memberService.countAll(size));
        return data;
    }
    
    
    @RequestMapping("view/{id}")
    public Object view(
    		@PathVariable String id) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        Member member = memberService.get(id);
        data.put("member", member);
        
        return data;
    }
    
    
    
    @RequestMapping("update")
    public String update(Member member) throws Exception {
        
        if (memberService.update(member) == 0) {
            return "member/updatefail";
        } else {
            return "redirect:list";
        }
    }
}
