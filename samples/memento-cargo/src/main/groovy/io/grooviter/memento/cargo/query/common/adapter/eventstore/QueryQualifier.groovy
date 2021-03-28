package io.grooviter.memento.cargo.query.common.adapter.eventstore

import javax.inject.Qualifier
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface QueryQualifier {}