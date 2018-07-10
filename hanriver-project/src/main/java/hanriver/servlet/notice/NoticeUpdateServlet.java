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
@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {   
            Notice notice = new Notice();
            notice.setTitle(request.getParameter("title"));
            notice.setContents(request.getParameter("contents"));
            notice.setNo(Integer.parseInt(request.getParameter("no")));
            
            if (((NoticeDao)getServletContext().getAttribute("noticeDao")).update(notice) == 0) {
                RequestDispatcher rd = request.getRequestDispatcher("/notice/updatefail.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }
}
