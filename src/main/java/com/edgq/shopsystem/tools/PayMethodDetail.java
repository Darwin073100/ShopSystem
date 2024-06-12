package com.edgq.shopsystem.tools;

import com.edgq.shopsystem.enums.PayMethodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author edwin
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayMethodDetail {
    private int id;
    private PayMethodType payMethod;
    private String icon;
    private String detail;
}
