package com.natwest.assessment.Models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Output{
    private String outfield1;
    private String outfield2;
    private String outfield3;
    private String outfield4;
    private BigDecimal outfield5;
}
