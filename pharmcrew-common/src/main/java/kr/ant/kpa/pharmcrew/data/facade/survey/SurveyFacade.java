package kr.ant.kpa.pharmcrew.data.facade.survey;

import com.bumdori.data.AbsDataChain;
import com.bumdori.data.AbsDataFacade;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.data.AbsRedisChain;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.SurveyDao;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SurveyFacade extends AbsDataFacade<Long, SurveyVo> implements InitializingBean {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SurveyDao surveyDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        AbsDataChain<Long, SurveyVo> cacheChain = new AbsRedisChain<Long, SurveyVo>(CACHE.SURVEY, cacheManager) {
        };
        AbsDataChain<Long, SurveyVo> dbChain = new AbsDataChain<Long, SurveyVo>() {
            @Override
            protected SurveyVo getData(Long k) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "survey_id", k);
                return surveyDao.selectSurvey(param);
            }
        };
        cacheChain.setNextChain(dbChain);
        this.dataChain = cacheChain;
    }
}
