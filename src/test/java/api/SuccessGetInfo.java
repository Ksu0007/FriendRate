package api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessGetInfo {
    private String _id;
    private String password;
    private String email;
    private String avatarURL;
    private boolean verify;
    private String provider;
    private String verificationToken;
    private Date createdAt;
    private Date updatedAt;
}
