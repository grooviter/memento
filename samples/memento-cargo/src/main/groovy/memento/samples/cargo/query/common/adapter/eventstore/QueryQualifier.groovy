package memento.samples.cargo.query.common.adapter.eventstore

import jakarta.inject.Qualifier
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface QueryQualifier {}