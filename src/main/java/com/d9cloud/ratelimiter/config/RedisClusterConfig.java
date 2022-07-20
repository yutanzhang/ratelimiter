package com.d9cloud.ratelimiter.config;

import com.d9cloud.ratelimiter.handler.JedisSlotAdvancedConnectionHandler;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2022/07/20
 */
@Configuration
public class RedisClusterConfig {

    @Value("${redis.nodes}")
    private String clusterNodes = "";

    @Value("${redis.maxIdle}")
    private int maxIdle = 200;

    @Value("${redis.maxTotal}")
    private int maxTotal = 1000;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow = true;

    @Value("${redis.maxWaitMills}")
    private int maxWaitMills = 1800;

    @Bean
    @ConditionalOnBean()
    public JedisSlotAdvancedConnectionHandler jedisSlotAdvancedConnectionHandler() {
        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(8);

        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setMaxWaitMillis(maxWaitMills);
        poolConfig.setLifo(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(1000);
        poolConfig.setMinEvictableIdleTimeMillis(1000 * 60);
        return new JedisSlotAdvancedConnectionHandler(nodes, poolConfig, maxWaitMills, maxWaitMills);
    }

}
