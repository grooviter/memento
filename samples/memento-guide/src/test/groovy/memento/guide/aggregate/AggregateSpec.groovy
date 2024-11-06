package memento.guide.aggregate

import groovy.test.GroovyAssert
import spock.lang.Specification

import static groovy.test.GroovyAssert.assertScript

class AggregateSpec extends Specification {

    void 'creating: static method'() {
        expect:
        assertScript '''
        // tag::creating_static_method[]
        import memento.model.*
        
        class Delivery extends Aggregate {
            String clientId
        
            static Delivery create() {
                return new Delivery(id: UUID.randomUUID())
            }
        }        
        def delivery = Delivery.create()
        // ...and then apply events to the instance
        // end::creating_static_method[]
        '''
    }

    void 'creating: java constructor'() {
        expect:
        assertScript '''
        // tag::creating_java_constructor[]
        import memento.model.*
        
        class Delivery extends Aggregate {
            String clientId
        
            Delivery(UUID id) {
               super(id)
            }
        }
        def delivery = new Delivery(UUID.randomUUID())
        // ...and then apply events to the instance
        // end::creating_java_constructor[]
        '''
    }

    void 'creating: groovy @InheritConstructors'() {
        expect:
        assertScript '''
        // tag::creating_groovy_constructor[]
        import memento.model.*
        import groovy.transform.*
        
        @InheritConstructors()
        class Delivery extends Aggregate {
            String clientId
        }
        def delivery = new Delivery(UUID.randomUUID())
        // ...and then apply events to the instance
        // end::creating_groovy_constructor[]
        '''
    }
}
