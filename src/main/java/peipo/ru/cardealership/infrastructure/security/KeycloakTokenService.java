package peipo.ru.cardealership.infrastructure.security;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakTokenService
{
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String username, String password)
    {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "car-api");
        body.add("client_secret", "secret");
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", "password");

        Map response = restTemplate.postForObject(
                "http://localhost:8081/realms/car-dealership/protocol/openid-connect/token",
                body,
                Map.class
        );

        return (String) response.get("access_token");
    }
}
