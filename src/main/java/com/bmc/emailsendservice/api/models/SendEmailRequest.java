package com.bmc.emailsendservice.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendEmailRequest {
//    @NotBlank
    private Integer employeeId;
    private String customerEmail;
    private String emailText;
    private String subject;

}
