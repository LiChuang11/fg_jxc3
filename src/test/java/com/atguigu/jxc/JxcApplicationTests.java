package com.atguigu.jxc;

import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.service.ManageService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxcApplicationTests {

    @Autowired
    private ManageService manageService;

    @Test
    public void test() {
        System.out.println("userDao = " + manageService);
    }

	@Test
	@SneakyThrows
	public void contextLoads() {
//		Arrays.sort();
		Class<ArrayList> arrayListClass = ArrayList.class;
		Class<? extends ArrayList> aClass = new ArrayList<>().getClass();
		Class<?> clazz = Class.forName("java.util.ArrayList");
		Constructor<?> declaredConstructor1 = clazz.getDeclaredConstructor(null);
		Object o = declaredConstructor1.newInstance();
		System.out.println("o = " + o);
		Constructor<? extends ArrayList> declaredConstructor = aClass.getDeclaredConstructor(null);
		ArrayList arrayList = declaredConstructor.newInstance();
		arrayList.add("1");
		System.out.println("arrayList = " + arrayList.getClass());

		Constructor<?> declaredConstructor2 = clazz.getDeclaredConstructor(null);
		Object o1 = declaredConstructor2.newInstance();
		System.out.println("o1 = " + o1.toString());

		Object o2 = clazz.newInstance();
		System.out.println("o2 = " + o2);

	}

}
