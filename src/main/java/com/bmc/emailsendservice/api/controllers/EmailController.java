package com.bmc.emailsendservice.api.controllers;

import com.bmc.emailsendservice.api.models.ApiResponse;
import com.bmc.emailsendservice.api.models.SendEmailRequest;
import com.bmc.emailsendservice.data.AppData;
import com.bmc.emailsendservice.services.impl.SendEmailServiceImpl;
import com.bmc.emailsendservice.services.interfaces.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class EmailController {
    @Autowired
    private AppData appData;

    @PostMapping("/api/v1/sendEmail")
    public ApiResponse sendEmail(@Valid @RequestBody SendEmailRequest sendEmailRequest)  {
        Future<ApiResponse> apiResponseFuture = appData.getExecutorService().submit(new SendEmailServiceImpl(sendEmailRequest));
        try {
            return apiResponseFuture.get();
        } catch (Exception exc){
            log.error(String.format("EXCEPTION: %s : %s", exc.getMessage(), Arrays.toString(exc.getStackTrace())));
            return new ApiResponse("error");
        }
    }
}
