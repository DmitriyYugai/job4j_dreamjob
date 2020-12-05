package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = PsqlStore.instOf();
        req.setAttribute("candidates", store.findAllCandidates());
        req.setAttribute("cities", store.findAllCities());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Candidate can = new Candidate(
                Integer.valueOf(req.getParameter("id")),
                req.getParameter("name")
        );
        can.setCityId(Integer.valueOf(req.getParameter("city_id")));
        PsqlStore.instOf().save(can);
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
