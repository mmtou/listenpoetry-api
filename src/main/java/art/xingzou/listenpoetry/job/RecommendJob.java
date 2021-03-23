package art.xingzou.listenpoetry.job;

import art.xingzou.listenpoetry.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecommendJob {

    @Autowired
    private RecommendService recommendService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void generate() {
        recommendService.generate();
    }

}
