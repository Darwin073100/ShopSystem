package com.edgq.shopsystem.tools;

import com.edgq.shopsystem.enums.PayMethod;
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
    private PayMethod payMethod;
    private String icon;
    private String detail;
}
