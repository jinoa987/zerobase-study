package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.wifilist.dao.WifiDAO;
import org.zerock.wifilist.domain.WifiVO;

import java.util.List;

public class WifiDAOTests {
    private WifiDAO wifiDAO;

    @BeforeEach
    public void ready() {
        wifiDAO = new WifiDAO();
    }

    @Test
    public void testLoadAll() throws Exception {
        List<WifiVO> list = wifiDAO.loadAll();
        list.forEach(vo -> System.out.println(vo));
    }
}
