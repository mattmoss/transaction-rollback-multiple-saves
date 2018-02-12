package demo

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

class ProcessService {

    @Transactional
    private saveNames(List<String> names) {
        for (name in names) {
            new Item(name: name).save(failOnError: false)
        }
    }

    def processItems(List<String> names) {
        try {
            saveNames names
            [success: true]
        }
        catch (ValidationException ex) {
            [success: false]
        }
    }
}
