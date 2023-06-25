package com.logistic.transportlogistic.exception;

import org.springframework.http.HttpStatus;


public record ExceptionInformation(String code, HttpStatus status, String message) {

}
