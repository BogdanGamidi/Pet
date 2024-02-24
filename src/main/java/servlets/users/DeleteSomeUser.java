package servlets.users;

import entities.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CRUDUsers;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteSomeUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Users user = CRUDUsers.getSomeUser(id);
            if(user != null) {
                req.setAttribute("user", user);
                utils.CRUDUsers.deleteSomeUser(id);
                writer.println("<h1>User ID " + id + " deleted</h1>");
            } else {
                getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
        } finally {
            writer.close();
        }
    }
}
