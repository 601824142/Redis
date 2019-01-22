package com.wan;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author 万星明
 * @Date 2019/1/21
 */
public class JavaDemo {
    public static void main(String[] args) {

        redisRun();

    }

    /**
     * 直接获取Redis连接
     * @return
     */
    public static Jedis getJedis(){
        Jedis jedis = new Jedis("47.112.22.169",6379);
        //验证设置密码
//        jedis.auth("");
        return  jedis;
    }

    /**
     * 从连接池中获得Redis连接
     * @return
     */
    public static Jedis getJedisByPool(){
        //新建Jedis连接池的配置对象,配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        //给连接池设置一些配置
        jedisPoolConfig.setMaxWaitMillis(1000);//连接最大等待时间(毫秒)
        jedisPoolConfig.setMaxIdle(50);//连接池的最大空闲数量
//        jedisPoolConfig.setMinIdle();//连接池的最小空闲数量
        jedisPoolConfig.setMaxTotal(1000);//最大连接总数
//        jedisPoolConfig.setBlockWhenExhausted();//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
//        jedisPoolConfig.setEvictorShutdownTimeoutMillis();//逐出连接的最小空闲时间 默认1800000毫秒(30分钟)

        //新建Redis连接池(jedis配置,主机,端口,连接超时/读写超时,redis密码)
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "47.112.22.169", 6379, 1000, null);

        //从连接池中获取连接
        Jedis jedisPoolResource = jedisPool.getResource();

        //返回连接对象
        return jedisPoolResource;
    }

    /**
     * 主体运行程序
     */
    public static void redisRun(){
        //获得Redis连接
//        Jedis jedis = getJedis();
        Jedis jedis = getJedisByPool();

        //设定初值
        int i=1;
        //开始时间
        long start = System.currentTimeMillis();
        //死循环
        while (true){
            long end = System.currentTimeMillis();
            //如果运行的时间大于等于1秒,跳出
            if(end - start >= 1000){
                break;
            }

            //设置Redis中的键值
            jedis.set("key"+i,"value"+i);
            i++;
        }

        System.out.println("1秒内生成了"+i+"次键值对");
        jedis.close();
    }
}
