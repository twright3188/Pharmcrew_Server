package kr.ant.kpa.pharmcrew.data.facade.education;

import com.bumdori.data.AbsDataChain;
import com.bumdori.data.AbsDataFacade;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.data.AbsRedisChain;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.EducationDao;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EducationFacade extends AbsDataFacade<Long, EducationVo> implements InitializingBean {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private EducationDao educationDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        AbsDataChain<Long, EducationVo> cacheChain = new AbsRedisChain<Long, EducationVo>(CACHE.EDUCATION, cacheManager) {
        };
        AbsDataChain<Long, EducationVo> dbChain = new AbsDataChain<Long, EducationVo>() {
            @Override
            protected EducationVo getData(Long k) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "education_id", k);
                return educationDao.selectEducation(param);
            }
        };
        cacheChain.setNextChain(dbChain);
        this.dataChain = cacheChain;
    }
}
