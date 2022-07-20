package com.d9cloud.ratelimiter.limiter;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2022/07/20
 */
public interface RateLimiter {

    boolean shouldLimit(int userId, int maxCount, int period);

}
