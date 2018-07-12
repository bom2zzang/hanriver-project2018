package hanriver.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.annotation.Autowired;
import hanriver.annotation.Controller;
import hanriver.annotation.RequestMapping;
import hanriver.dao.MemberDao;
import hanriver.domain.Member;

@Controller("/member/view")
public class MemberViewController {
    
    MemberDao memberDao;
    
    
    
    public MemberViewController() {}

    public MemberViewController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @RequestMapping
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = memberDao.selectOne(request.getParameter("id"));
        request.setAttribute("member", member);
        return "/member/view.jsp";
    }
    
}
