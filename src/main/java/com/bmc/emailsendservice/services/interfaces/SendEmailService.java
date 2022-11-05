package com.bmc.emailsendservice.services.interfaces;

import com.bmc.emailsendservice.api.models.ApiResponse;
import com.bmc.emailsendservice.api.models.SendEmailRequest;

public interface SendEmailService {
    ApiResponse sendEmailToCustomer(SendEmailRequest sendEmailRequest);
}
