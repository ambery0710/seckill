package com.jiuzhang.seckill.component;

import com.jiuzhang.seckill.db.dao.SeckillActivityDao;
import com.jiuzhang.seckill.db.po.SeckillActivity;
import com.jiuzhang.seckill.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisPreheatRunner implements ApplicationRunner {
    @Autowired
    RedisService redisService;

    @Autowired
    SeckillActivityDao seckillActivityDao;

    /**
     * 启动项目时 向Redis存入商品库存
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SeckillActivity> seckillActivities = seckillActivityDao.querySeckillActivitysByStatus(1);
        for (SeckillActivity seckillActivity : seckillActivities) {
            RedisService redisService = this.redisService.setValue("stock:" + seckillActivity.getId(), (long) seckillActivity.getTotalStock());
        }
    }

}
