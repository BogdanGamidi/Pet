package servlets.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CRUDUsers;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
            if (CRUDUsers.getUsers().size() == 0) {
                getServletContext().getRequestDispatcher("/jspfiles/crudusers/notUsers.jsp").forward(req, resp);
            } else {
                utils.CRUDUsers.deleteAllUser();
                resp.sendRedirect(req.getContextPath() + "/getServlet");
                writer.println("<h1>All servlets.users deleted</h1>");
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/notUsers.jsp").forward(req, resp);
        } finally {
            writer.close();
        }
    }
}