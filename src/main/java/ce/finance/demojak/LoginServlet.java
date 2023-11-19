package ce.finance.demojak;

import ce.finance.demojak.model.Error;
import ce.finance.demojak.model.LoginForm;
import ce.finance.demojak.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String cmd = getParam(req, "cmd", "");
        String resultJson = "";
        switch (cmd) {
            case "ping":
                resultJson = "pong";
                break;
            case "login":
                String username = getParam(req, "username", "guest");
                String password = getParam(req, "password", "qwerty");
                resultJson = username + " " + password;
                break;
            case "register":
                resultJson ="register";
                break;
            default:
                resultJson = "use cmd for command";
        }

        System.out.println("Finished cmd:" + cmd);

        PrintWriter writer = resp.getWriter();
        writer.write("{\"msg\": \"+ resultJson +\"}");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = getBody(req);
        System.out.println(body);

        String username = getParam(req, "username", "guest");
        String password = getParam(req, "password", "qwerty");

        System.out.println(username);
        System.out.println(password);
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginForm login = mapper.readValue(body, LoginForm.class);
        }   catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            Error error = new Error("Login form error", 404);
            String errorJson = mapper.writeValueAsString(error);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json"); // content type
            resp.setStatus(error.getStatus()); // status
            PrintWriter writer = resp.getWriter();
            writer.write(errorJson);
            return;
        }
        ObjectMapper mapper = new ObjectMapper();

        Player player = new Player(111, "Badruddin", 2344.1);
        String jsonPlayer = mapper.writeValueAsString(player);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonPlayer);
    }

    private String getParam(HttpServletRequest req, String paramName, String defVal) {
        String result = req.getParameter(paramName);
        return result != null ? result : defVal;
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
