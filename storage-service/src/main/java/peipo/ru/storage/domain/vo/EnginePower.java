package peipo.ru.storage.domain.vo;

public record EnginePower(int horsePower)
{
    public EnginePower
    {
        if (horsePower < 0)
        {
            throw new IllegalArgumentException("Horse power cannot be negative");
        }
    }
}
