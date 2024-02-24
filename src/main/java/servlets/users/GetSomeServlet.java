package servlets.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GetSomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        try {

            if (utils.CRUDUsers.getSomeUser(Integer.parseInt(req.getParameter("id"))) == null) {
                getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
            }
            else {
                printWriter.println("<h1>" + utils.CRUDUsers.getSomeUser(Integer.parseInt(req.getParameter("id"))) + "</h1");
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/jspfiles/crudusers/notFoundUser.jsp").forward(req, resp);
        } finally {
            printWriter.close();
        }
    }
}
