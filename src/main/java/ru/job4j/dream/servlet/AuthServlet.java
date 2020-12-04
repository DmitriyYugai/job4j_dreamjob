package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> user = PsqlStore.instOf().findUserByEmail(email);
        if (!user.isPresent()) {
            resp.sendRedirect(req.getContextPath() + "/reg.jsp");
            return;
        }
        if (!password.equals(user.get().getPassword())) {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        HttpSession sc = req.getSession();
        sc.setAttribute("user", user.get());
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
