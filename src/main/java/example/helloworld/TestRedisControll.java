package example.helloworld;

import example.annotations.MyAnnotation;
import example.holders.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ${xzl} on 2017/6/19.
 */
@Controller
@RequestMapping("/redis")
public class TestRedisControll {

    @Autowired
    protected RedisTemplate redisTemplate;


    @Autowired @Qualifier("stringRedisTemplate")
    RedisTemplate stringRedisTemplate;

    @RequestMapping("/setKey/{myKey}")
    @MyAnnotation("Mater")
    @ResponseBody
    public String setKey(@PathVariable String myKey){
        List<String> list =new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
//        redisTemplate.opsForList().leftPushAll("test",list);
        redisTemplate.opsForList().leftPushAll(myKey,list);//设置list
        redisTemplate.opsForValue().set("name","xzl");//设置string
        redisTemplate.opsForValue().set("number",666);//设置string
        redisTemplate.opsForValue().set("number1",01001);//取出的结果变为了 513
        redisTemplate.opsForValue().set("number2",100);//设置string
        return "Ok:"+myKey+ DBContextHolder.getDbType()+" 设置成功";
    }
    @RequestMapping("/getKey/{key}")
    @ResponseBody
//    @MyAnnotation("Mater")
    public Set getKey(@PathVariable String key){
        String name = (String) redisTemplate.opsForValue().get("name");
        int num = (int) redisTemplate.opsForValue().get("number");
        int num2 =  (int)redisTemplate.opsForValue().get("number2");
        System.out.println(DBContextHolder.getDbType()+" : "+name+","+num+","+redisTemplate.opsForValue().get("number1")+","+num2);
        return stringRedisTemplate.keys("*");
    }
}
