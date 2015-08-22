import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole


class BootStrap {

    def init = {
		def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new AuthRole(authority: 'ROLE_USER').save(flush: true)
		def trustedRole = new AuthRole(authority: 'ROLE_TRUSTED').save(flush: true)
		def untrustedRole = new AuthRole(authority: 'ROLE_UNTRUSTED').save(flush: true)
		def specialRole = new AuthRole(authority: 'ROLE_SPECIAL').save(flush: true)
		
		def adminUser = new AuthUser(username: 'admin', fullName: 'Administrator', enabled: true, password: 'admin', email: 'admin@gmail.com').save(flush: true)

		AuthUserAuthRole.create adminUser, adminRole, true
	}
    def destroy = {
    }
}
