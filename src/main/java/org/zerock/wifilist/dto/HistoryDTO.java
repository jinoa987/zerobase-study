package org.zerock.wifilist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    private String mngNo;
    private double coorX;
    private double coorY;
    private Date date;
}
