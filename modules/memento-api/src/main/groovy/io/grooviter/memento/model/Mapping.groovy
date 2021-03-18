package io.grooviter.memento.model

import groovy.transform.ToString

@ToString(includeNames = true)
class Mapping {
    String alias
    Class type
}
