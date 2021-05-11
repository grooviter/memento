[![license](https://img.shields.io/github/license/grooviter/memento.svg)]()

**MEMENTO** is a simple EventStore implementation. Out of the box Memento supports storing events andsnapshots
in memory or csv files. It has a simple structure suitable for creating new backend implementations.

To use `Memento` in your Gradle project you can find it Maven Central:

    repositories {
        mavenCentral()
    }

Then you can add the base dependency to your project:

    compile 'io.grooviter:memento-base:0.1.0-SNAPSHOT'

Current documentation is available at: http://grooviter.github.io/memento/

**AT THE MOMENT MEMENTO IS STILL IN BETA PHASE**