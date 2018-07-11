package hanriver.servlet.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hanriver.dao.NoticeDao;


@SuppressWarnings("serial")
@WebServlet("/notice/delete")
public class NoticeDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ((NoticeDao)getServletContext().getAttribute("noticeDao")).delete(request.getParameter("no"));
            request.setAttribute("view", "redirect:list");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
}
