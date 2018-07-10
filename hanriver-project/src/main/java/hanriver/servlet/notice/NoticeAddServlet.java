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
@WebServlet("/notice/add")
public class NoticeAddServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {   
            Notice notice = new Notice();
            notice.setTitle(request.getParameter("title"));
            notice.setContents(request.getParameter("contents"));
            ((NoticeDao)getServletContext().getAttribute("noticeDao")).insert(notice);
            response.sendRedirect("list");
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }
}
