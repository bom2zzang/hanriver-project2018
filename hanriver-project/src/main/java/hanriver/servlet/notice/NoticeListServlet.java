package hanriver.servlet.notice;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.dao.NoticeDao;


@SuppressWarnings("serial")
@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> params = new HashMap<>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        }
        try {
            NoticeDao noticeDao = (NoticeDao)getServletContext().getAttribute("noticeDao");
            request.setAttribute("list", noticeDao.selectList(params));
            request.setAttribute("view", "/notice/list.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
}
