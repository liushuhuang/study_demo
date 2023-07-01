package com.cn.liu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cn.liu.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {

	@Test
	public void test(){
		User user = new User(1,"aa",11,"m","67864684648488448");
		User user1 = new User(2,"bb",12,"m","67864684648488448");
		User user2 = new User(3,"cc",13,"m","67864684648488448");
		User user3 = new User(4,"ff",14,"m","67864684648488448");
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = new HashMap<>();
		Map<String,Object> map3 = new HashMap<>();
		map.put("user",user);
		map1.put("user",user1);
		map2.put("user",user2);
		map3.put("user",user3);
		String a = JSON.toJSONString(map);
		String b = JSON.toJSONString(map1);
		String c = JSON.toJSONString(map2);
		String d = JSON.toJSONString(map3);
		List<String> l = new ArrayList<>();
		l.add(a);
		l.add(b);
		l.add(c);
		l.add(d);
		List<User> user4 = l.stream().map(s -> JSON.parseObject(s, new TypeReference<HashMap<String, Object>>() {
		})).map(m -> JSON.toJavaObject((JSON) m.get("user"), User.class)).filter(u->u.getName().equals("aa")).collect(Collectors.toList());
		for (User user5 : user4) {
			System.out.println(user5.toString());
		}


	}
}
