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
public class SuccessLogin {
    private User user;
    private String token;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public static class User{
        public String _id;
        public String password;
        public String email;
        public String avatarURL;
        public boolean verify;
        public String provider;
        public String verificationToken;
        public String token;
        public Date createdAt;
        public Date updatedAt;
    }
}
