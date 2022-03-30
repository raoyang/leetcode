package org.sunshine.lc.test;

import com.authine.h3yun.engine.controller.service.LocalConfigProvider;
import com.authine.lateinos.reds.sdk.build.RedisClientBuilder;
import com.authine.lateinos.reds.sdk.manager.RedisManager;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.Date;
import java.util.Random;

public class RedisTest {

    public static void main(String args[]){
        System.out.println(new Date(1615368064881l));
        //deleteKey();
        /*
        Random random = new Random(System.nanoTime());
        for(int i = 0 ; i < 10000 ; i ++){
            int index = random.nextInt(1);
            System.out.println(index);
        }

         */
    }

    private static void read(){
        RedisClientBuilder.setConfigProvider(new LocalConfigProvider());
        // 初始化，程序启动时调用
        RedisClientBuilder.init("H3YUN-ENGINE");
        // 创建Redis操作类
        RedisManager redisManager = RedisClientBuilder.buildFactory("H3YUN-ENGINE").getManager();

        RedissonClient redissonClient = redisManager.getRedissonClient();
        String key = redisManager.handleKey("a");

        Object obj = redissonClient.getBucket(key).get();
        if(obj == null){
            System.out.println("null");
        }else{
            boolean result = (boolean)obj;
            System.out.println(result);
        }
    }

    private static void syncHash(){
        RedisClientBuilder.setConfigProvider(new LocalConfigProvider());
        // 初始化，程序启动时调用
        RedisClientBuilder.init("H3YUN-ENGINE");
        // 创建Redis操作类
        RedisManager redisManager = RedisClientBuilder.buildFactory("H3YUN-ENGINE").getManager();

        RedissonClient redissonClient = redisManager.getRedissonClient();
        String key = "upgrade-address" + "-" + "shard1" + "-"+ "v1";
        String redisKey = redisManager.handleKey(key);
        RMap<String, String> map = redissonClient.getMap(redisKey);
        map.put("current", "192.168.0.1:8080");
        map.put("target", "192.168.0.2:8080");
    }

    private static void syncList(){
        RedisClientBuilder.setConfigProvider(new LocalConfigProvider());
        // 初始化，程序启动时调用
        RedisClientBuilder.init("H3YUN-ENGINE");
        // 创建Redis操作类
        RedisManager redisManager = RedisClientBuilder.buildFactory("H3YUN-ENGINE").getManager();

        RedissonClient redissonClient = redisManager.getRedissonClient();
        String key = "engine-upgrade-list";
        String redisKey = redisManager.handleKey(key);
        RList<String> rList = redissonClient.getList(redisKey);
        rList.add("shard4");
    }

    private static void deleteKey(){
        RedisClientBuilder.setConfigProvider(new LocalConfigProvider());
        // 初始化，程序启动时调用
        RedisClientBuilder.init("H3YUN-ENGINE");
        // 创建Redis操作类
        RedisManager redisManager = RedisClientBuilder.buildFactory("H3YUN-ENGINE").getManager();

        RedissonClient redissonClient = redisManager.getRedissonClient();
        String key = redisManager.handleKey("aaa");
        redissonClient.getBucket(key).delete();
    }
}
