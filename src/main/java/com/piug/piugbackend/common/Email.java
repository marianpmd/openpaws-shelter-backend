package com.piug.piugbackend.common;

import lombok.Data;

@Data
public class Email {
    private String sender;
    private String subject;
    private String message;
}
