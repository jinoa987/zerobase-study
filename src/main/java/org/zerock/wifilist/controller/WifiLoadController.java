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

@WebServlet(name = "wifiLoadController", value = "/load-wifi")
public class WifiLoadController extends HttpServlet {

    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // OpenAPI 공공데이터 불러오기
            List<WifiDTO> dtoList = wifiService.loadList();
            req.setAttribute("dtoList", dtoList);
            req.setAttribute("dtoCount", dtoList.size());

            req.getRequestDispatcher("/WEB-INF/load-wifi.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("load error");
        }
    }
}
