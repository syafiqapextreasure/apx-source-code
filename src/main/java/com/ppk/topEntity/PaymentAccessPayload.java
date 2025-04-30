package com.ppk.topEntity;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PaymentAccessPayload {
    @NotNull
    @Valid
    private PaymentAccessRequest payload;
} 