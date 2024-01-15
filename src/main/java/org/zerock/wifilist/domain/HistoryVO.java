package org.zerock.wifilist.domain;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO {
    private String mngNo;
    private double coorX;
    private double coorY;
    private Date date;
}
