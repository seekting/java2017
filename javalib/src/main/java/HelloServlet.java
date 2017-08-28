import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by seekting on 17-8-3.
 */

public class HelloServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.sendError(401,"hahaha");

//        resp.getWriter().print("Hello");
    }
    public static void main(String args[]){
            System.out.println("aaaa");
    }
}
