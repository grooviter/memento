package io.grooviter.memento.bookkeeper.common.config

import com.google.inject.AbstractModule
import groovy.transform.TupleConstructor
import groovy.yaml.YamlSlurper

@TupleConstructor
class YamlConfigModule<T> extends AbstractModule {
    String yamlResourcePath
    Class<T> configClass

    void config() {
        YamlSlurper yamlSlurper = new YamlSlurper()
        InputStream inputStream = YamlConfigModule
            .getClassLoader()
            .getResourceAsStream(yamlResourcePath)

        T yamlConfiguration = yamlSlurper
            .parse(inputStream)
            .asType(configClass)

        bind(configClass).toInstance(yamlConfiguration)
    }
}
