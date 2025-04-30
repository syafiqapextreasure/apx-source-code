package com.ppk.topEntity;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PaymentAccessRequest {
    @NotBlank
    @Size(max = 255)
    private String subsysId;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 30)
    private String orderNo;

    @NotBlank
    @Size(max = 30)
    private String description;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    private String txnTime;

    @NotNull
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999999.99")
    private BigDecimal amount;

    @Email
    @Size(max = 50)
    private String userEmail;

    @Pattern(regexp = "^[0-9]{10,11}$")
    private String userMobile;

    @Size(max = 255)
    private String feeCode;

    private Boolean adhoc;

    @Size(max = 255)
    private String details;
} 