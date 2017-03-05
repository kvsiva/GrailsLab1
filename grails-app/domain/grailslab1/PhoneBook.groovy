package grailslab1

class PhoneBook {
    String firstName
	String lastName
	String phoneNumber	
	
    static constraints = {
		firstName (blank:false)
		lastName (blank:false)
		phoneNumber (blank:false)
		
    }
}
