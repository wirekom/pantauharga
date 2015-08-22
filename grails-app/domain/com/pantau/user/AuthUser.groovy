package com.pantau.user

class AuthUser {

	transient springSecurityService

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired


	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false, unique: true, email: true
		
	}

	static mapping = {
		password column: '`password`'
	}
	

	
	Set<AuthRole> getAuthorities() {
		AuthUserAuthRole.findAllByAuthUser(this).collect { it.authRole }
	}
	
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		//def bcryptService
		//password = passwordEncoder.encode(password);
		//password = password.encodeAsBcrypt()
	}
}
