package hanriver.controller.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.controller.PageController;
import hanriver.dao.MemberDao;


public class MemberListController implements PageController {
    
    MemberDao memberDao;
    
    public MemberListController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        }
        request.setAttribute("list", memberDao.selectList(params));
        return "/member/list.jsp";
    }
}
