package social.web.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * @author andre-arao
 */
public class PostRequest implements Serializable {

    public String getFollowerId() {
        return followerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    private static final long serialVersionUID = 1113488483222411111L;

    @NotBlank
    private String followerId;

    @NotBlank
    private String userId;

}