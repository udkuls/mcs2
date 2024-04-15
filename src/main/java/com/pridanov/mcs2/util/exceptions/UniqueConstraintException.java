package com.pridanov.mcs2.util.exceptions;

public class UniqueConstraintException extends RuntimeException {
    public UniqueConstraintException(String massage) { super(massage); }
}
