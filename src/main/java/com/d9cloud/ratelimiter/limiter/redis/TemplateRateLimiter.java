package com.d9cloud.ratelimiter.limiter.redis;

import com.d9cloud.ratelimiter.limiter.RateLimiter;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2022/07/20
 */
public class TemplateRateLimiter implements RateLimiter {
    @Override
    public boolean shouldLimit(int userId, int maxCount, int period) {
        return false;
    }
}
