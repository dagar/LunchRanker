package ca.dagar.lunchranker.backend;

import java.io.IOException;

import javax.servlet.http.*;

public class LunchRankerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/plain");
	//resp.getPathInfo(); - use this with wildcard
        if (name == null) {
            resp.getWriter().println("Please enter a name");
        }
        resp.getWriter().println("Howdy " + name);
    }
}
