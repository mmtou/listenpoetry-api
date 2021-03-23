package art.xingzou.listenpoetry.request;

import art.xingzou.listenpoetry.utils.TokenUtil;
import art.xingzou.listenpoetry.vo.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Request {

    @JsonIgnore
    public Long getCurrentUserId() {
        Token token = TokenUtil.getToken();
        if (token != null) {
            return (long) token.getId();
        }
        return null;
    }

    @JsonIgnore
    public String getCurrentNickname() {
        Token token = TokenUtil.getToken();
        if (token != null) {
            return token.getNickname();
        }
        return null;
    }
}
