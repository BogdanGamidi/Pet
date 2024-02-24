package servlets.users;

import entities.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CRUDUsers;

import java.io.IOException;
import java.io.PrintWriter;

public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Users user = CRUDUsers.getSomeUser(id);
            if(user != null) {
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/jspfiles/crudusers/updateUserPassword.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            resp.setContentType("text/html");
            int id = Integer.parseInt(req.getParameter("id"));
            String password = req.getParameter("password");
            CRUDUsers.updatePasswordUser(password, id);
            writer.println("<h1>User ID " + id + " password updated</h1>");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
        } finally {
            writer.close();
        }
    }
}

