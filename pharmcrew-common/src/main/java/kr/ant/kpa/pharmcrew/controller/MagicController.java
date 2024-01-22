package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("99. 매직")
public class MagicController {
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "/caches/{cache}", method = RequestMethod.DELETE, name = "캐시 삭제")
    @ResponseBody
    @Session
    @Description("캐시를 삭제한다.")
    @Histories({
            @History(date = "2020-05-29", description = "완료"),
    })
    public PcResp deleteCache(
            @PathVariable("cache") @Description("캐시 종류") @EnumValidation({"ORGANIZE"}) String cache,
            @RequestParam("key") @Description("키") Long key
    ) {
        cacheManager.delete(CACHE.valueOf(cache), key);

        return new PcResp();
    }
}
