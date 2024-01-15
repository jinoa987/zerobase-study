package org.zerock.wifilist.service;

import org.modelmapper.ModelMapper;
import org.zerock.wifilist.dao.WifiDAO;
import org.zerock.wifilist.domain.HistoryVO;
import org.zerock.wifilist.domain.WifiVO;
import org.zerock.wifilist.dto.HistoryDTO;
import org.zerock.wifilist.dto.WifiDTO;
import org.zerock.wifilist.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

public enum WifiService {
    INSTANCE;

    private WifiDAO dao;
    private ModelMapper modelMapper;

    WifiService() {
        dao = new WifiDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<WifiDTO> loadList() throws Exception {
        List<WifiVO> voList = dao.loadAll();

        List<WifiDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, WifiDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public List<WifiDTO> getList() {
        List<WifiVO> voList = dao.getList();

        List<WifiDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, WifiDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public void insertHistory(WifiDTO wifiDTO) throws Exception {

        WifiVO wifiVO = modelMapper.map(wifiDTO, WifiVO.class);

        dao.insert(wifiVO);
    }

    public void removeHistory(Long id) throws Exception {
        dao.deleteHistory(id);
    }
}
