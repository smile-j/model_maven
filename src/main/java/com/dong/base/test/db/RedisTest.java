package com.dong.base.test.db;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RedisTest {


    private static final String LOCK_SUCCESS = "OK";
    //nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";//EX代表秒，PX代表毫秒。

    private Jedis jedis = null;
    public static JedisPool pool ;

    @Before
    public void init(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        pool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        System.out.println("连接池初始化成功");

    }

    @After
    public void cloePool(){
        if(null != pool){
            pool.destroy();
            System.out.println("连接池关闭");
        }
    }

    /**
     * 操作string
     */
    @Test
    public void testStr(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();
        //设置键 获取键 set  get
        result = jedis.set("steve","stevetao");
        System.out.println(result+" 设置后的值："+jedis.get("steve"));
        //追加键 append
        resultFlag = jedis.append("steve"," Is Good Man");
        System.out.println(resultFlag+" 追加后的值："+jedis.get("steve"));
        //删除 del
        resultFlag = jedis.del("steve");
        System.out.println(resultFlag+" 删除后的值："+jedis.get("steve"));
        //setnx msetnx 不存在就保存      1-success  0-erro
        resultFlag=jedis.setnx("steve","stevetao");
        System.out.println(resultFlag+" 设置后的值："+jedis.get("steve"));
        System.out.println("再次设置后的值："+jedis.setnx("steve","stevetao2"));
        //截取字符串  substr
        System.out.println("截取后的值："+jedis.substr("steve",0,4));
        //设置多个键值对 mset mget
        result = jedis.mset(new String[]{"zhangsan","123","lisi","1234"});
        System.out.println(result+" 多个值设置后的值："+jedis.mget("zhangsan","lisi"));
        //递增 递减  incr decr  incrby decrby
        resultFlag = jedis.incr("zhangsan");
        System.out.println("resultFlag："+resultFlag);
        resultFlag = jedis.decr("lisi");
        System.out.println(resultFlag+" 递增递减后值："+jedis.mget("zhangsan","lisi"));
        resultFlag = jedis.incrBy("zhangsan",6);
        System.out.println("resultFlag："+resultFlag);
        resultFlag = jedis.decrBy("lisi",3);
        System.out.println(resultFlag+" 递增递减后值："+jedis.mget("zhangsan","lisi"));
    }

    /**
     * 操作list
     */
    @Test
    public void testList(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();
        //尾添加 rpush  头添加 lpush
        jedis.lpush("books","java","c++","Ruby","Scala","python");
        jedis.rpush("language","java","c++","Ruby","Scala","python");
        //-1 表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推
        System.out.println("头添加后books值:"+jedis.lrange("books",0,-1));//头添加后books值:[python, Scala, Ruby, c++, java]
        System.out.println("尾添加后language值:"+jedis.lrange("language",0,-1));//尾添加后language值:[java, c++, Ruby, Scala, python]
        //尾部删除 rpop  头部删除。lpop
        System.out.println("删除的值为："+jedis.lpop("books"));
        System.out.println("删除的值为："+jedis.rpop("language"));
        System.out.println("头部删除后的books值:"+jedis.lrange("books",0,-1));
        System.out.println("尾部部删除后的language值:"+jedis.lrange("books",0,-1));
        //尾部删除并头添加rpoplpush
        result = jedis.rpoplpush("language","books");
        System.out.println("尾部删除并头添加后books的值："+jedis.lrange("books",0,-1));
        System.out.println("尾部删除并头添加后的language的值："+jedis.lrange("language",0,-1));
//        //区别：只能给存在的list做添加，不能向lpush那样新增list
        resultFlag = jedis.lpushx("books","php");
        resultFlag = jedis.lpushx("book","php");
        System.out.println(resultFlag + " 头添加后的books值："+jedis.lrange("books",0,-1));
        System.out.println(resultFlag + " 头添加后的books值："+jedis.lrange("book",0,-1));
        //获取集合长度len  指定索引的值lindex 保留截取的值ltrim
        System.out.println("books集合长度："+jedis.llen("books"));
        System.out.println("books集合长度："+jedis.lindex("books",1));
        result = jedis.ltrim("books",0,2);
        System.out.println(result + " 截取后books的值："+jedis.lrange("books",0,-1));

    }

    /**
     * Hash  有管操作
     */
    @Test
    public void testHash(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();

        //适合字段：设值hset,取值hget(如果value是json字符串，类似保存对象)
        jedis.hset("student","name","zhangsan");
        System.out.println("student中name的值为："+jedis.hget("student","name"));
        //适合对象：设值hmset  取值hmget
        Map<String,String> map = new HashMap<>();
        map.put("name","lisi");
        map.put("age","36");
        jedis.hmset("teacher",map);
        System.out.println("teacher的name、age的值为："+jedis.hmget("teacher","name","age"));
        //teacher是否存在键
        if(jedis.hexists("teacher","age")){
            //给指定值增加4 hincryBy
            jedis.hincrBy("teacher","age",4);
            System.out.println("teacher中name、age的值为："+jedis.hmget("teacher","name","age"));
        }
        //返回key的个数hlen 返回值hvals
        jedis.hset("student","age","13");
        jedis.hset("student","qq","123456789");
        jedis.hset("student","address","北京");
        System.out.println("student中键的个数为："+jedis.hlen("student"));
        System.out.println("student中所有的键为："+jedis.hkeys("student"));
        System.out.println("student中所有的值为："+jedis.hvals("student"));
        System.out.println("student中所有的键值对为："+jedis.hgetAll("student"));
        //删除 hdel
        resultFlag = jedis.hdel("teacher",new String[]{"name","age"});
        System.out.println(resultFlag+" 删除后，student中所有的键值对为："+jedis.hgetAll("teacher"));
        resultFlag = jedis.hdel("addreess",new String[]{"address","qq","age"});
        System.out.println(resultFlag +" +删除后，addreess中所有的键值对为："+jedis.hgetAll("addreess"));


    }

    /**
     * set 类型有关操作
     */
    @Test
    public void testSet(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();

        //set中添加值 sadd      取值 smembers
        jedis.sadd("student","Jan","John","Steve","jack","lili","peter","Anna");
        jedis.sadd("girls","Jan","lili","Alice","Jeanne","Anna");
        System.out.println("排名不分先后："+jedis.smembers("student"));
        //set个数 scard      是否存在某个值 sismember
        System.out.println("set集合的个数："+jedis.scard("student"));
        System.out.println("student是否存在steve："+jedis.sismember("student","Steve"));
        System.out.println("student是否存在stevetao："+jedis.sismember("student","Stevetao"));
        //System.out.println(jedis.sscan("student","0").getResult());
        //删除指定的值 srem     随机删除并返回 spop
        System.out.println("删除指定的值Steve："+jedis.srem("student","Steve"));
        System.out.println("删除的值为："+jedis.spop("student"));
        System.out.println("再次排名不分先后："+jedis.smembers("student"));
        //集合操作
        System.out.println("两个set的交集："+jedis.sinter("student","girls"));
        System.out.println("两个set的并集："+jedis.sunion("student","girls"));
        System.out.println("student对girls的差集："+jedis.sdiff("student","girls"));
        System.out.println("girls对student的差集："+jedis.sdiff("girls","student"));
        //集合操作并保存
        jedis.sinterstore("jiaoji","student","girls");
        jedis.sunionstore("bingji","student","girls");
        jedis.sdiffstore("chaji","student","girls");

        System.out.println("交集："+jedis.smembers("jiaoji"));
        System.out.println("并集："+jedis.smembers("bingji"));
        System.out.println("student对girls的差集："+jedis.smembers("chaji"));
    }


    /**
     *
     */
    @Test
    public void testZset(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();

        jedis.zadd("math",75,"Jim");
        jedis.zadd("math",86,"Lina");
        jedis.zadd("math",52,"Dive");
        jedis.zadd("math",91,"Bobber");
        jedis.zadd("math",9,"zhangsan");
        System.out.println("有序集合的成员数:"+jedis.zcard("math"));
        System.out.println("有序集合的成员:"+jedis.zrevrangeByScore("math",100,0));
        //返回set<Tuple>
        System.out.println("有序集合的成员:"+jedis.zrangeWithScores("math",0,100));
        /**
         * 有序集合的成员数:4
         * 有序集合的成员:[Bobber, Lina, Jim, Dive]
         * 有序集合的成员:[[[68, 105, 118, 101],52.0], [[74, 105, 109],75.0], [[76, 105, 110, 97],86.0], [[66, 111, 98, 98, 101, 114],91.0]]
         */
        Set<String> zrangeByScore = jedis.zrangeByScore("math", 10.0, 100);
        System.out.println("zrangeByScore = " + zrangeByScore);
        Set<Tuple> zrangeByScoreWithScores = jedis.zrangeByScoreWithScores("math", 10.0, 100);
       System.out.println("zrangeByScoreWithScores = " + zrangeByScoreWithScores);

        //删除
//        resultFlag = jedis.zrem("math","Jim","Lina");
    }

    @Test
    public void testZset2(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();
        System.out.println(" :"+jedis.zscore("math","Lina"));
        System.out.println(" :"+jedis.zrevrank("math","Lina"));//（score从大到小，从0开始，所以需要加1）
        System.out.println(" :"+jedis.zrank("math","Lina"));//（score从小到大，从0开始，所以需要加1）
        System.out.println(" :"+jedis.zrange("math",0,2));
        Map map = new HashMap() ;
    }

    @Test
    public void test1(){
        String result = null;
        long resultFlag ;
        jedis = pool.getResource();

        result = jedis.set("math1","2222", SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME,10000);

        System.out.println(result+"------"+jedis.get("math1"));

    }

    @Test
    public void test2(){
        String result = null;
        long resultFlag =-11,resultFlag2=-1;
        jedis = pool.getResource();
        System.out.println("zrevrangeByScore有序集合的成员:"+jedis.zrevrangeByScore("math",100,0));
        System.out.println("zrevrange:"+jedis.zrevrange("math",0,0));

//        resultFlag = jedis.zrem("math","zhangsan");
        resultFlag = jedis.zadd("math",76,"Jim");
//         resultFlag2 = jedis.zadd("math",1,"zhangsan");
        System.out.println(resultFlag+" zrange有序集合的成员:"+jedis.zrange("math",0,-1));
        System.out.println(resultFlag2+" zrangeByScore 有序集合的成员:"+jedis.zrangeByScore("math",0,100));

//        Set<Long> positionIds = jedis.zrevrange();
//        reverseRange(key, 0, 4);


    }


}
