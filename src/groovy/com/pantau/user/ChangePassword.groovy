package com.pantau.user

import grails.validation.Validateable;

@Validateable
class ChangePassword {
	String oldPassword
	String newPassword
	String retypePassword

	static constraints = {
		oldPassword blank: false, minSize: 6, validator: { val, obj -> println 'test validate' }
		newPassword blank: false, minSize: 6, validator: { val, obj, errors ->
			if (!(obj.retypePassword == val)) ['noMatch']
		}
		retypePassword blank: false, minSize: 6
	}
}
