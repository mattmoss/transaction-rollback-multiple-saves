package demo

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

class ProcessServiceSpec extends HibernateSpec
        implements ServiceUnitTest<ProcessService>{

    @Override
    List<Class> getDomainClasses() {
        [Item]
    }

    void 'no items persisted if any fails validation'() {
        given: 'some existing data in the database'
        ['foo', 'bar', 'qux'].each {
            new Item(name: it).save(flush: true, failOnError: true)
        }

        expect: 'three items in the database'
        Item.count == 3

        when: 'attempt to add some new items, last name is not unique'
        def result = service.processItems(['abc', 'xyz', 'bar'])

        then: 'should fail with no new items persisted'
        Item.count == 3     // nothing new should persist
        !result.success
    }
}
