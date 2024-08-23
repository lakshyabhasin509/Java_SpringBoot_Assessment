package com.natwest.assessment.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reference {
    private String refkey1;
    private String refdata1;
    private String refkey2;
    private String refdata2;
    private String refdata3;
    private BigDecimal refdata4;
}
