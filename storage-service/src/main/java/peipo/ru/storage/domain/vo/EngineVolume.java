package peipo.ru.storage.domain.vo;

public record EngineVolume(int volume)
{
    public EngineVolume
    {
        if (volume < 0)
        {
            throw new IllegalArgumentException("Volume cannot be negative");
        }
    }
}
