package vbx.com.ua.controller;

import vbx.com.ua.service.AuthenticateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author: Viacheslav Bondarchuk
 * date: 5/4/20
 * time: 10:50 PM
 **/
public class LoginController extends HttpServlet {

    private AuthenticateService authenticateService;

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authenticateService.login(req, resp);
    }
}
