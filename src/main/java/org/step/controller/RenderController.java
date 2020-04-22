package org.step.controller;

import org.step.model.User;
import org.step.repository.AuthoritiesRepository;
import org.step.repository.UserRepository;
import org.step.repository.impl.UserRepositoryImpl;
import org.step.service.UserService;
import org.step.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/render")
public class RenderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository<User> userRepository = new UserRepositoryImpl();
        AuthoritiesRepository<User> authoritiesRepository = new UserRepositoryImpl();
        Random random = new Random();
        UserService<User> userService = new UserServiceImpl(userRepository, authoritiesRepository, random);

        User byId = userService.findById(5L);

        System.out.println(byId.getUsername());
        req.setAttribute("username", byId.getUsername());

        getServletContext().getRequestDispatcher("/first.jsp").forward(req, resp);
    }
}