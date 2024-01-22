package kr.ant.kpa.pharmcrew.data.facade.news;

import com.bumdori.data.AbsDataChain;
import com.bumdori.data.AbsDataFacade;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.data.AbsRedisChain;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.NewsDao;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NoticeFacade extends AbsDataFacade<Long, NoticeVo> implements InitializingBean {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private NewsDao newsDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        AbsDataChain<Long, NoticeVo> cacheChain = new AbsRedisChain<Long, NoticeVo>(CACHE.NOTICE, cacheManager) {
        };
        AbsDataChain<Long, NoticeVo> dbChain = new AbsDataChain<Long, NoticeVo>() {
            @Override
            protected NoticeVo getData(Long k) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "notice_id", k);
                return newsDao.selectNotice(param);
            }
        };
        cacheChain.setNextChain(dbChain);
        this.dataChain = cacheChain;
    }
}
