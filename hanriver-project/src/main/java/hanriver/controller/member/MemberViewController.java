package hanriver.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.MemberDao;
import hanriver.domain.Member;


public class MemberViewController implements PageController {
    
    MemberDao memberDao;
    
    public MemberViewController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = memberDao.selectOne(request.getParameter("id"));
        request.setAttribute("member", member);
        return "/member/view.jsp";
    }
    
}
