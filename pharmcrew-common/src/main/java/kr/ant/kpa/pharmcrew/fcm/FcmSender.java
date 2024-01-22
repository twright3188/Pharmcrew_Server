package kr.ant.kpa.pharmcrew.fcm;

import com.bumdori.fcm.AbsFcmSender;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FcmSender extends AbsFcmSender {
    @Autowired
    private PushDao pushDao;

    @Override
    public void onErrorIds(Long... ids) {
        // TODO fcm 데이터 오류 호출해야 한다.
    }

    @Override
    public void onFailRegistrationTokens(List<String> tokens) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "tokens", tokens);
        pushDao.deletePushToken(param);
    }
}
