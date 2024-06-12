package com.edgq.shopsystem.tools;

import com.edgq.shopsystem.enums.TicketType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetail {
    private int id;
    private TicketType ticket;
    private String icon;
    private String detail;
}
