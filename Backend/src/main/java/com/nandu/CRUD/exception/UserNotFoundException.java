package com.nandu.CRUD.exception;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(Long id)
    {
        super("Id Not Found"+id);;
    }
}
