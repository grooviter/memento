package io.grooviter.memento.cargo.common.domain.events

import groovy.transform.AnnotationCollector
import groovy.transform.builder.Builder

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
@AnnotationCollector
@interface EventTuning {

}