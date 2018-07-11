package hanriver.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.MemberDao;
import hanriver.domain.Member;


public class MemberUpdateController implements PageController {
    
    MemberDao memberDao;
    
    public MemberUpdateController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = new Member();
        
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));
        member.setId(request.getParameter("id"));
        member.setTel(request.getParameter("tel"));
        
        if (memberDao.update(member) == 0) {
            return "/member/updatefail.jsp";
        } else {
            return "redirect:list";
        }
    }
    
}
