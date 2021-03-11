package io.grooviter.memento.bookkeeper.projector

import com.google.inject.AbstractModule
import io.grooviter.memento.bookkeeper.common.config.YamlConfigModule
import io.grooviter.memento.bookkeeper.projector.adapter.config.ProjectorConfig

class ProjectorModule extends AbstractModule {
    void configure() {
        install(new YamlConfigModule<ProjectorConfig>("projector.yml", ProjectorConfig))
    }
}
