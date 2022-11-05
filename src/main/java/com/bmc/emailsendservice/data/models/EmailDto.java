package com.bmc.emailsendservice.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDto {
    private String from;
    private String to;
    private String body;
    private String subject;
}
