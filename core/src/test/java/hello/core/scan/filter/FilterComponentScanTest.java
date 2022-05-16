package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

class FilterComponentScanTest {

    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class TestConfig {}

    ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void should_scan_include_BeanA_and_exclude_BeanB() {
        assertThat(context.getBean(BeanA.class)).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(BeanB.class));
    }

}
