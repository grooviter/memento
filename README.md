[![license](https://img.shields.io/github/license/grooviter/memento.svg)]() [![main](https://github.com/grooviter/memento/actions/workflows/gql-release.yml/badge.svg?branch=main)](https://github.com/grooviter/memento/actions/workflows/gql-release.yml) ![Maven Central](https://img.shields.io/maven-central/v/com.github.grooviter/memento-csv)

## What is Memento ?

Memento is a simple EventStore abstraction. Out of the box Memento supports storing events ands napshots
in memory or csv files. It has a simple structure suitable for creating new backend implementations.

## Gradle

To use `Memento` in your Gradle project you can add Maven Central repository:

```groovy
repositories {
    mavenCentral()
}
```

Then add the base dependency to your project:

```groovy
compile 'com.github.grooviter:memento-csv:0.1.0'
```

## Grab

To use memento in your Groovy scripts you can use the `@Grab` annotation:
```groovy
@Grab('com.github.grooviter:memento-csv:0.1.0')
import memento.*
import memento.model.*

//...
```

## Documentation

Documentation will be available asap at: http://grooviter.github.io/memento/