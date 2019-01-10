package com.ps.beans.ctr;

import com.ps.beans.SimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class CIBeansTest {
    private Logger logger = LoggerFactory.getLogger(CIBeansTest.class);

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-02.xml");

        logger.info(" --- All beans in context --- ");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type " + ctx
              .getBean(beanName)
              .getClass()
              .getSimpleName());
        }

        //TODO 3. Retrieve beans of types ComplexBean and make sure their dependencies were correctly set.
        SimpleBean simpleBean0 = (SimpleBean) ctx.getBean("simpleBean0");
        SimpleBean simpleBean1 = (SimpleBean) ctx.getBean("simpleBean1");

        ComplexBeanImpl complexBean = (ComplexBeanImpl) ctx.getBean("complexBean0");
        assertThat(complexBean.getSimpleBean(), is(equalTo(simpleBean0)));

        ComplexBeanImpl complexBean1 = (ComplexBeanImpl) ctx.getBean("complexBean1");
        assertThat(complexBean1.getSimpleBean(), is(equalTo(simpleBean0)));
        assertThat(complexBean1.isComplex(), is(true));

        ComplexBean2Impl complexBean2 = (ComplexBean2Impl) ctx.getBean("complexBean2");
        assertThat(complexBean2.getSimpleBean1(), is(equalTo(simpleBean0)));
        assertThat(complexBean2.getSimpleBean2(), is(equalTo(simpleBean1)));
    }
}
