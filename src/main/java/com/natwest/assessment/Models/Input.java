package com.natwest.assessment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Input {
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private BigDecimal field5;
    private String refkey1;
    private String refkey2;
}
