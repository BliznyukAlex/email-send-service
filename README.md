# email-send-service

Test task for BMC

## Getting Started

The project is located at https: https://github.com/BliznyukAlex/email-send-service

Source code can be download as Zip file or by using Git
https://github.com/BliznyukAlex/email-send-service.git

# Running the application

There are several ways to run a Spring Boot application on your local machine.
One way is to execute the main method in the com/bmc/emailsendservice/EmailSendServiceApplication.java class from your IDE.

Another way is to go to project directory and run from command line

    mvn spring-boot:run

# DB configuration

DB locates on remote server so you don't need to configure it

## Endpoints
    
    POST request to /api/v1/sendEmail --> send email to customer

## Request examples

    POST /registration
    Accept: application/json
    Content-Type: application/json
    {
    "employeeId": 1,
    "customerEmail": "customer@gmail.com",
    "subject" : "!!!Urgent!!!",
    "emailText": "hello, customer"
    }
    RESPONSE: HTTP 200 (Ok)