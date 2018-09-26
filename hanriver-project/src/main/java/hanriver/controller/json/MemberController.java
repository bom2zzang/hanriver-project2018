package hanriver.controller.json;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object add(Member member) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        memberService.add(member);
        return result;
    }
    
    
    @RequestMapping("delete")
    public Object delete(String id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        if (memberService.delete(id) == 0) {
            result.put("status", "fail");
            result.put("error", "해당 아이디가 없습니다.");
        } else {
            result.put("status", "success");
        }
        
        return result;
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
    public Object update(Member member) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        if (memberService.update(member) == 0) {
            result.put("status", "fail");
            result.put("error", "해당 아이디가 없습니다.");
        } else {
            result.put("status", "success");
        }
        return result;
    }
    

  //로그인 
    @PostMapping("login")
    public Object login(Member member, HttpSession session) throws Exception{
    	HashMap<String, Object> result = new HashMap<>();
    	int memberNo = memberService.validMemberCheck(member);
    	if (memberNo == 0) {
    		result.put("status", "fail");
    		result.put("error", "해당 아이디가 없습니다.");
    	} else {
    	    session.setAttribute("loginUser", memberNo);
    		result.put("status", "success");
    	}
    	return result;
    }
}

