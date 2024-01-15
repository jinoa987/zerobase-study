package org.zerock.wifilist.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WifiVO {
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
