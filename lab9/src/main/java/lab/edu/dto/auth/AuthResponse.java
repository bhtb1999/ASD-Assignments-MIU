package lab.edu.dto.auth;

import java.util.List;

public record AuthResponse(
        String token,
        String tokenType,
        String username,
        List<String> roles
) {
}
