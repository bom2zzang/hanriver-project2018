package hanriver.servlet.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;


@SuppressWarnings("serial")
@WebServlet("/notice/view")
public class NoticeViewServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Notice notice = ((NoticeDao)getServletContext().getAttribute("noticeDao")).selectOne(request.getParameter("no"));
            request.setAttribute("notice", notice);
            RequestDispatcher rd = request.getRequestDispatcher("/notice/view.jsp");
            rd.include(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }
}
