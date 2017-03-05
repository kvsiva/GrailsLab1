package grailslab1



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PhoneBookController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PhoneBook.list(params), model:[phoneBookInstanceCount: PhoneBook.count()]
    }

    def show(PhoneBook phoneBookInstance) {
        respond phoneBookInstance
    }

    def create() {
        respond new PhoneBook(params)
    }

    @Transactional
    def save(PhoneBook phoneBookInstance) {
        if (phoneBookInstance == null) {
            notFound()
            return
        }

        if (phoneBookInstance.hasErrors()) {
            respond phoneBookInstance.errors, view:'create'
            return
        }

        phoneBookInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'phoneBook.label', default: 'PhoneBook'), phoneBookInstance.id])
                redirect phoneBookInstance
            }
            '*' { respond phoneBookInstance, [status: CREATED] }
        }
    }

    def edit(PhoneBook phoneBookInstance) {
        respond phoneBookInstance
    }

    @Transactional
    def update(PhoneBook phoneBookInstance) {
        if (phoneBookInstance == null) {
            notFound()
            return
        }

        if (phoneBookInstance.hasErrors()) {
            respond phoneBookInstance.errors, view:'edit'
            return
        }

        phoneBookInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PhoneBook.label', default: 'PhoneBook'), phoneBookInstance.id])
                redirect phoneBookInstance
            }
            '*'{ respond phoneBookInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PhoneBook phoneBookInstance) {

        if (phoneBookInstance == null) {
            notFound()
            return
        }

        phoneBookInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PhoneBook.label', default: 'PhoneBook'), phoneBookInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'phoneBook.label', default: 'PhoneBook'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
