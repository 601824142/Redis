package com.wan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/1/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class RedisTest {


    /**
     * 自动注入配置中的模板
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setKeyValue(){

//        List<String> list = new ArrayList<String>();
//        list.add("小明");
//        list.add("小红");
//        list.add("小刚");
//
//        //设置key-value
//        redisTemplate.opsForValue().set("list", list);

        //存值
        redisTemplate.opsForValue().set("name","wan");

    }


    @Test
    public void getKeyValue(){

        //获得key-value
//        String value = (String) redisTemplate.opsForValue().get("name");
//        System.out.println("从redis中获得数据：" + value);

        List<String> list = (List<String>) redisTemplate.opsForValue().get("list");
        System.out.println(list);
    }

}
