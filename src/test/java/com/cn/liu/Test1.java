package com.cn.liu;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Test1 {

	//@Test
	//public void test(){
	//	User user = new User(1,"aa",11,"m","67864684648488448");
	//	User user1 = new User(2,"bb",12,"m","67864684648488448");
	//	User user2 = new User(3,"cc",13,"m","67864684648488448");
	//	User user3 = new User(4,"ff",14,"m","67864684648488448");
	//	Map<String,Object> map = new HashMap<>();
	//	Map<String,Object> map1 = new HashMap<>();
	//	Map<String,Object> map2 = new HashMap<>();
	//	Map<String,Object> map3 = new HashMap<>();
	//	map.put("user",user);
	//	map1.put("user",user1);
	//	map2.put("user",user2);
	//	map3.put("user",user3);
	//	String a = JSON.toJSONString(map);
	//	String b = JSON.toJSONString(map1);
	//	String c = JSON.toJSONString(map2);
	//	String d = JSON.toJSONString(map3);
	//	List<String> l = new ArrayList<>();
	//	l.add(a);
	//	l.add(b);
	//	l.add(c);
	//	l.add(d);
	//	List<User> user4 = l.stream().map(s -> JSON.parseObject(s, new TypeReference<HashMap<String, Object>>() {
	//	})).map(m -> JSON.toJavaObject((JSON) m.get("user"), User.class)).filter(u->u.getName().equals("aa")).collect(Collectors.toList());
	//	for (User user5 : user4) {
	//		System.out.println(user5.toString());
	//	}
	//
	//
	//}

	@Test
	public void test1(){
		BigDecimal a = BigDecimal.valueOf(20000);
		BigDecimal b = BigDecimal.valueOf(1.5);
		BigDecimal c = BigDecimal.valueOf(365);
		BigDecimal d = BigDecimal.valueOf(100);
		LocalDateTime last = LocalDateTime.now().minusYears(1);
		LocalDateTime local = LocalDateTimeUtil.now();
		Long days = LocalDateTimeUtil.between(last,local).toDays();
		System.out.println(days);
		System.out.println("直接乘法："+a.multiply(b));
		System.out.println("乘除："+a.multiply(c.divide(d)));
	}
	@Test
	public void test2(){
		BigDecimal a = BigDecimal.valueOf(4);
		BigDecimal b = BigDecimal.valueOf(5);
		BigDecimal add = a.add(b);
		System.out.println(b.multiply(BigDecimal.valueOf(100)).divide(add,0, RoundingMode.HALF_UP));
	}
}
