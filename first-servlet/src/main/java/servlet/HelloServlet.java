package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "hello", urlPatterns = "/index.do")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        try(PrintWriter out = resp.getWriter()) {
            new Printer(out).print("<b>").print("刘宇辉").print("</b>").print("<br />").print("<br />")
                    .print("<i>").print(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())).print("</i>");
        }
    }

    public static class Printer {
        private PrintWriter out;
        public Printer(PrintWriter out) {
            this.out = out;
        }

        public Printer print(String s) {
            out.print(s);
            return this;
        }
    }

    public interface Hello {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
