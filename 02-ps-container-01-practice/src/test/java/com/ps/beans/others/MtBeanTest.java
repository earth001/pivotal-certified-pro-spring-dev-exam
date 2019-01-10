package com.ps.beans.others;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class MtBeanTest {

    private Logger logger = LoggerFactory.getLogger(MtBeanTest.class);

    @Test
    public void testConfig() {
        //TODO 6. Modify this class to use the new set of configuration files, created by resolving TODO 5.
        //TODO 7. Try to use wildcards as well.
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/others/sample-config-01.xml");
        ApplicationContext ctxSplitted = new ClassPathXmlApplicationContext(
            "classpath:spring/others/sample-config-01-splitted.xml");
        ApplicationContext ctxWildcard = new ClassPathXmlApplicationContext("classpath:spring/others/split-config/*-config-01.xml");

        MultipleTypesBean mtBean = (MultipleTypesBean) ctx.getBean("mtBean");
        assertNotNull(mtBean);

        assertThat(ctx.getBeanDefinitionCount(),is(equalTo(ctxSplitted.getBeanDefinitionCount())));
        assertThat(ctx.getBeanDefinitionCount(),is(equalTo(ctxWildcard.getBeanDefinitionCount())));
    }
}
