package fr.kisled.dsl.generator.algorithm


import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@interface Generator {
    Class<? extends GeneratorStrategy> generator() default NoOpGenerator.class;
}