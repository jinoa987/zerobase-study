package org.zerock.wifilist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WifiDTO {
    private String mgrNo;
    private String section;
    private String wifiName;
    private String roadAddress;
    private String detailAddress;
    private String instlFloor;
    private String instlType;
    private String instlAgency;
    private String serviceType;
    private String netType;
    private int instlYear;
    private String inoutDoor;
    private String connEnv;
    private double lat;
    private double lnt;
    private String workDate;
}
