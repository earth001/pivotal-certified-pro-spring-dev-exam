package com.ps.beans.set;

import com.ps.beans.SimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SIBeansTest {
    private Logger logger = LoggerFactory.getLogger(SIBeansTest.class);

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-02.xml");

        logger.info(" --- All beans in context --- ");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type " + ctx
              .getBean(beanName)
              .getClass()
              .getSimpleName());
        }

        //TODO 4. Retrieve beans of types ComplexBean and make sure their dependencies were correctly set.
        SimpleBean simpleBean0 = ctx.getBean("simpleBean0", SimpleBean.class);
        SimpleBean simpleBean1 = ctx.getBean("simpleBean1", SimpleBean.class);

        ComplexBeanImpl complexBean0 = ctx.getBean("complexBean0", ComplexBeanImpl.class);
        assertThat(complexBean0.getSimpleBean(), is(notNullValue()));
        assertThat(complexBean0.getSimpleBean(), is(equalTo(simpleBean0)));

        ComplexBeanImpl complexBean1 = ctx.getBean("complexBean1", ComplexBeanImpl.class);
        assertThat(complexBean1.getSimpleBean(), is(equalTo(simpleBean0)));
        assertThat(complexBean1.isComplex(), is(true));

        ComplexBean2Impl complexBean2 = ctx.getBean("complexBean2", ComplexBean2Impl.class);
        assertThat(complexBean2.getSimpleBean(), is(equalTo(simpleBean0)));
        assertThat(complexBean2.isComplex(), is(true));
    }
}
