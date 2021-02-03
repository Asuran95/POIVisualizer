package piper.poivisualizer.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import piper.poivisualizer.BasePackageClass;

@ComponentScan(basePackageClasses = BasePackageClass.class)
@Configuration
public class ComponentSetup {

}
