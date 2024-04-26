package api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private String _id;
    private String chatId;
    private String name;
    private String email;
    private String gender;
    private String avatar;
    private Integer dailyWaterRequirement;
}
