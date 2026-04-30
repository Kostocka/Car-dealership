package peipo.ru.common.exception;

public class EntityNotFoundException extends DomainException
{
    public EntityNotFoundException(String message)
    {
        super(message);
    }
}
