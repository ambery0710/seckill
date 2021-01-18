package com.jiuzhang.seckill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillActivityService {

    @Autowired
    private RedisService redisService;
    /**
     * 判读商品是否还有库存
     * @param activityId 商品Id
     * @return
     */
    public boolean seckillStockValidator(long activityId) {
        String key = "stock:" + activityId;
        return redisService.stockDeductValidator(key);
    }

}
