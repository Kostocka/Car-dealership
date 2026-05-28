package peipo.ru.integration.infrastructure;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;

public final class DockerHelper
{
    private static final String DOCKER_COMPOSE_FILE =
            System.getProperty(
                    "docker.compose.file",
                    "../docker/docker-compose.yml"
            );

    public static void stop(String service) throws IOException, InterruptedException
    {
        execute("stop", service);
    }

    public static void start(String service) throws IOException, InterruptedException
    {
        execute("start", service);
    }

    public static void restart(String service) throws IOException, InterruptedException
    {
        execute("restart", service);
    }

    public static void waitFor(long millis) throws InterruptedException
    {
        Thread.sleep(millis);
    }

    private static void execute(String command, String service) throws IOException, InterruptedException
    {
        int code = new ProcessBuilder("docker", "compose", "-f", DOCKER_COMPOSE_FILE, command, service)
                .inheritIO()
                .start()
                .waitFor();

        Assertions.assertEquals(0, code);
    }
}