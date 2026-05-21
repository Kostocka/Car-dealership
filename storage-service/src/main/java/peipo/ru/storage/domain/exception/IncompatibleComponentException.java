package peipo.ru.storage.domain.exception;

import peipo.ru.common.exception.DomainException;

public class IncompatibleComponentException extends DomainException
{
    public  IncompatibleComponentException(String message)
    {
        super(message);
    }
}
