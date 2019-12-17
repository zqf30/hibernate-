package test;

import table.StudentEntity;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

/**
 * 测试类
 */
public class StudentTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;




    @Before
    public void init(){
        //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        //  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();（使用这种方法会报错，unkonw Entity 。。。。）
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂对象

        sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建会话对象
        session  = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }


    @Test
    public void testSaveStudents(){
        StudentEntity s = new StudentEntity();
        s.setId(11);
        s.setName("张奇锋");
        s.setSex("男");
        session.save(s);//保存对象进入数据库
    }

    @After
    public void destory(){
        //提交事务
        transaction.commit();
        //关闭session
        session.close();
        //关闭sessionFactory
        sessionFactory.close();
    }
}
