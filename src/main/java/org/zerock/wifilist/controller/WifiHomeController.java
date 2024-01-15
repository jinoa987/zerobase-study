package org.zerock.wifilist.controller;

import org.zerock.wifilist.dto.WifiDTO;
import org.zerock.wifilist.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "wifiHomeController", value = "/home")
public class WifiHomeController extends HttpServlet {
    WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<WifiDTO> dtoList = wifiService.getList();
            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("home error");
        }

    }
}
