package servlets.users;

import entities.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CRUDUsers;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insertNewUser")
public class InsertUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jspfiles/crudusers/insertUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            Users user = new Users(name, surname, login, password);
            CRUDUsers.addUser(user);
            resp.sendRedirect(req.getContextPath() + "/getServlet");

        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/insertUser.jsp").forward(req, resp);
        }
        finally {
            printWriter.close();
        }
    }
}
