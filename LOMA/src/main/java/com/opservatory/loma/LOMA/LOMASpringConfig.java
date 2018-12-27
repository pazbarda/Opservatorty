package com.opservatory.loma.LOMA;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// TODO - figure out why this file can't be found
//@PropertySource("classpath:loma.properties")
@ComponentScan(basePackages =  {"com.opservatory.loma.*"})
public class LOMASpringConfig {

}
