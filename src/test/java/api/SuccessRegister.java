package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessRegister {
    private User user;
    private String token;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public static class User {
        private String email;
        private String avatarURL;
        private String provider;
        private boolean verify;
    }
}
