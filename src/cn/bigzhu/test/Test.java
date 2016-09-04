package cn.bigzhu.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.bigzhu.dao.IItems;
import cn.bigzhu.po.Items;

public class Test {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Items items = (Items) session.selectOne(
					"cn.bigzhu.dao.IItems.findItemById", 1);
			System.out.println(items);
			 IItems iItems=session.getMapper(IItems.class);
			 Items items1 = iItems.findItemById(1);
			System.out.println(items1);
		} finally {
			session.close();
		}
	}
}