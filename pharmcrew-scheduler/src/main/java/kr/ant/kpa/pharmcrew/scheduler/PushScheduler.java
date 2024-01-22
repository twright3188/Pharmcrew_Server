package kr.ant.kpa.pharmcrew.scheduler;

import com.bumdori.spring.BLogger;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.notify.NotifyHelper;
import kr.ant.kpa.pharmcrew.type.notify.PUSH_STATE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushScheduler {
    private final Logger logger = LoggerFactory.getLogger(PushScheduler.class);

    @Autowired
    private PushDao pushDao;

    @Autowired
    private NotifyHelper notifyHelper;

    public PushScheduler() {
        BLogger.info(logger, "constructor: {}", this);
    }

    //@Scheduled(cron = "0 0/1 * * * *")	// 1분마다
    @Scheduled(fixedDelay=5*1000)		// 5 초마다
    public void onEvery1Minutes() {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "state_div", PUSH_STATE.R.name());
        MapUtils.put(param, "timeToSend", true);
        List<PushVo> pushVos = pushDao.listPush(param);

        for (PushVo pushVo: pushVos) {
            notifyHelper.send(pushVo);
        }
    }
}
