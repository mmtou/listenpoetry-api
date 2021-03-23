package art.xingzou.listenpoetry.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProps {

    private List<String> skipAuthUris;

    public List<String> getSkipAuthUris() {
        return skipAuthUris;
    }

    public void setSkipAuthUris(List<String> skipAuthUris) {
        this.skipAuthUris = skipAuthUris;
    }
}
