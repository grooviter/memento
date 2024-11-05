package memento.model

class Mappings {

    final Map<String, Class> aliasToType
    final Map<Class, String> typeToAlias
    final List<Mapping> mappingList

    Mappings(List<Mapping> list) {
        this.mappingList = list
            .asUnmodifiable()
        this.aliasToType = list
            .collectEntries { Mapping mapping -> [(mapping.alias): mapping.type] }
            .asUnmodifiable()
        this.typeToAlias = list
            .collectEntries { Mapping mapping -> [(mapping.type): mapping.alias] }
            .asUnmodifiable()
    }

    Class resolveClass(String alias){
        Optional<Class> resolvedClass = Optional.ofNullable(this.aliasToType.get(alias))
                | () -> Optional.of(Class.forName(alias)) // only executed if doesn't have an alias

        return resolvedClass.orElse(null)
    }

    String resolveAlias(Class type) {
        return this.typeToAlias.get(type) ?: type.name
    }

    static Builder builder() {
        return new Builder()
    }

    static class Builder {
        List<Mapping> builderMappingList = []

        Builder addMapping(String alias, Class type) {
            this.builderMappingList.add(new Mapping(alias: alias, type: type))
            return this
        }

        Mappings build() {
            return new Mappings(this.builderMappingList)
        }
    }
}