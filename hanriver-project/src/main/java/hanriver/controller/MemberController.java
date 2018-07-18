package hanriver.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hanriver.dao.MemberDao;
import hanriver.domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
    
    MemberDao memberDao;
    
    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add() {
        return "member/form";
    }
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Member member) throws Exception {
        memberDao.insert(member);
        return "redirect:list";
    }
    
    @RequestMapping("delete")
    public String delete(String id) throws Exception {
        memberDao.delete(id);
        return "redirect:list";
    }
    
    @RequestMapping("list")
    public String list(String page, String size, Map<String, Object> map) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (page != null && size != null) {
            params.put("startIndex", (Integer.parseInt(page) - 1) * Integer.parseInt(size));
            params.put("pageSize", Integer.parseInt(size));
        }
        map.put("list", memberDao.selectList(params));
        return "member/list";
    }
    
    @RequestMapping("view")
    public String view(String id, Map<String, Object> map) throws Exception {
        Member member = memberDao.selectOne(id);
        map.put("member", member);
        return "member/view";
    }
    
    @RequestMapping("update")
    public String update(Member member) throws Exception {
        
        if (memberDao.update(member) == 0) {
            return "member/updatefail";
        } else {
            return "redirect:list";
        }
    }
    
}
