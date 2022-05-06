package servlets;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.Dao;
import org.example.dao.DaoImpl;
import org.example.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private Dao userDao=new DaoImpl();
    /*
    This will take a simple GET request and respond with "Pong!" and status 202, indicating the request was accepted.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Using the UserDao class instance(object) to call(use) the methods to communicate with the database:
            //List<User> users = userDao.getAll();
            //ObjectMapper mapper = new ObjectMapper();
            //String json = mapper.writeValueAsString(users);

            resp.setStatus(200);
            resp.getWriter().print("Hello World!");
            //resp.getWriter().print(json);
        }catch(IOException ex) {
            resp.setStatus(500);
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Get user input from the request object and create a json (string) payload object of User type:
            User payload = mapper.readValue(req.getInputStream(), User.class);
            // Tell userDao implementation to insert the user in db:
            userDao.create(payload);
            // Setting response status code
            resp.setStatus(203);
            resp.getWriter().print("User successfully added");
        }catch (IOException ex) {
            resp.setStatus(500);
            resp.getWriter().print("Something went wrong creating your profile!");
            System.out.println(ex.getLocalizedMessage());
        }
    }
}