package art.xingzou.listenpoetry.security;

import art.xingzou.listenpoetry.props.AppProps;
import art.xingzou.listenpoetry.utils.TokenUtil;
import art.xingzou.listenpoetry.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(AuthFilter.class);
    @Autowired
    private AppProps appProps;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // 判断是否需要鉴权
        if (appProps.getSkipAuthUris().contains(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        Token token = TokenUtil.getToken();
        if (token != null) {
            TokenUtil.refresh();
            filterChain.doFilter(request, response);
            return;
        }

        log.debug("无权访问: {}", requestURI);
        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name());
    }
}
