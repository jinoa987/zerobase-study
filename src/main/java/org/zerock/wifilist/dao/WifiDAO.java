package org.zerock.wifilist.dao;

import lombok.Cleanup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zerock.wifilist.domain.WifiVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {

    private List<WifiVO> list = new ArrayList<>();

    public List<WifiVO> loadAll() throws IOException, ParseException {

        String api = "http://openapi.seoul.go.kr:8088/4f454e446f6a696e35375162775543/json/TbPublicWifiInfo/1/100/";

        URL url = new URL(api);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        connection.disconnect();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) ((JSONObject) jsonParser.parse(response.toString())).get("TbPublicWifiInfo");
        JSONArray jsonArray = (JSONArray) jsonObject.get("row");

        // 새로 데이터 저장하기 전에 기존 데이터 클리어
        list.clear();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            WifiVO vo = WifiVO.builder()
                    .mgrNo(obj.get("X_SWIFI_MGR_NO").toString())
                    .section(obj.get("X_SWIFI_WRDOFC").toString())
                    .wifiName(obj.get("X_SWIFI_MAIN_NM").toString())
                    .roadAddress(obj.get("X_SWIFI_ADRES1").toString())
                    .detailAddress(obj.get("X_SWIFI_ADRES2").toString())
                    .instlFloor(obj.get("X_SWIFI_INSTL_FLOOR").toString())
                    .instlType(obj.get("X_SWIFI_INSTL_TY").toString())
                    .instlAgency(obj.get("X_SWIFI_INSTL_MBY").toString())
                    .serviceType(obj.get("X_SWIFI_SVC_SE").toString())
                    .netType(obj.get("X_SWIFI_CMCWR").toString())
                    .instlYear(Integer.parseInt(obj.get("X_SWIFI_CNSTC_YEAR").toString()))
                    .inoutDoor(obj.get("X_SWIFI_INOUT_DOOR").toString())
                    .connEnv(obj.get("X_SWIFI_REMARS3").toString())
                    .lat(Double.parseDouble(obj.get("LAT").toString()))
                    .lnt(Double.parseDouble(obj.get("LNT").toString()))
                    .workDate(obj.get("WORK_DTTM").toString())
                    .build();
            list.add(vo);
        }

        return list;
    }

    public List<WifiVO> getList() {
        return list;
    }

    public void insert(WifiVO vo) throws Exception {

        String sql = "insert into wifi_history (coorX, coorY, date) values(?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1, vo.getLat());
        preparedStatement.setDouble(2, vo.getLnt());
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

        preparedStatement.executeUpdate();
    }

    public void deleteHistory(Long id) throws Exception {

        String sql = "delete from wifi_history where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        preparedStatement.executeUpdate();
    }
}
