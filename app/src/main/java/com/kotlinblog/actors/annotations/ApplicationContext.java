package com.kotlinblog.actors.annotations;

//@Qualifier
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ApplicationContext

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {

}

