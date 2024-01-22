package kr.ant.kpa.pharmcrew.data.facade.common;

import com.bumdori.data.AbsDataChain;
import com.bumdori.data.AbsDataFacade;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.data.AbsRedisChain;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrganizeFacade extends AbsDataFacade<Long, OrganizeVo> implements InitializingBean {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CommonDao commonDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        AbsDataChain<Long, OrganizeVo> cacheChain = new AbsRedisChain<Long, OrganizeVo>(CACHE.ORGANIZE, cacheManager) {
        };
        AbsDataChain<Long, OrganizeVo> dbChain = new AbsDataChain<Long, OrganizeVo>() {
            @Override
            protected OrganizeVo getData(Long k) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "organize_id", k);
                MapUtils.put(param, "withDepthNames", true);
                return commonDao.selectOrganize(param);
            }
        };
        cacheChain.setNextChain(dbChain);
        this.dataChain = cacheChain;
    }
}
