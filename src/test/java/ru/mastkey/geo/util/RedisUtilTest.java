package ru.mastkey.geo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import redis.clients.jedis.Jedis;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisUtilTest {
    @Mock
    private Jedis jedis;

    @InjectMocks
    private RedisUtil redisUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetValueByKey() {
        when(jedis.get(anyString())).thenReturn("Мокированное Значение");

        String result = redisUtil.getValueByKey("Мокированный Ключ");

        assertEquals("Мокированное Значение", result);
    }

    @Test
    void testSaveValueByKey() {
        redisUtil.saveValueByKey("Мокированный Ключ", "Мокированное Значение");

        verify(jedis, times(1)).setex(anyString(), anyLong(), anyString());
    }
}
