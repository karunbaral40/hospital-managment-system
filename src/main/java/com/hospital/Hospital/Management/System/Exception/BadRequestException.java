package com.hospital.Hospital.Management.System.Exception;

public class BadRequestException
        extends RuntimeException
{
    public BadRequestException(String message){
        super(message);
    }

}
