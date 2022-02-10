package com.kdh.redisson.test;

import com.kdh.redisson.test.config.RedissonConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.redisson.api.RedissonReactiveClient;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    private final RedissonConfig redissonConfig = new RedissonConfig();
    protected RedissonReactiveClient client;

    @BeforeAll
    void setClient() {
        this.client = this.redissonConfig.getReactiveClient();
    }

    @AfterAll
    void shutdown() {
        this.client.shutdown();
    }
}
