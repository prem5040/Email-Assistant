package com.email.emailWriter.app;

import lombok.Data;

@Data //it helps to generate getter setter and constructors from lombok
public class EmailRequest {
    private String emailContent;
    private String tone;
}
