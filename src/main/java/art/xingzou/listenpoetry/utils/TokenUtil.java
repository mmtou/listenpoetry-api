package art.xingzou.listenpoetry.utils;

import art.xingzou.listenpoetry.vo.Token;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

public class TokenUtil {

    // 根据token获取用户信息
    public static Token getToken() {
        String tokenStr = getTokenStr();
        if (StringUtils.isNotBlank(tokenStr)) {
            return getToken(tokenStr);
        }
        return null;
    }

    // 根据token获取用户信息
    public static Token getToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            RedisTemplate<String, String> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
            String value = redisTemplate.opsForValue().get(token);
            if (StringUtils.isNotBlank(value)) {
                return JSON.parseObject(value, Token.class);
            }
        }
        return null;
    }

    // 刷新token时间
    public static void refresh() {
        String tokenStr = getTokenStr();
        if (StringUtils.isNotBlank(tokenStr)) {
            RedisTemplate<String, String> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
            // token缓存时常
            long TOKEN_TIME_OUT = 7 * 24 * 60 * 60 * 1000;
            redisTemplate.expire(tokenStr, TOKEN_TIME_OUT, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 获取tokenstr
     */
    private static String getTokenStr() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            return request.getHeader("token");
        }
        return null;
    }

}
