import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole


class BootStrap {

    def init = {
		def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
		def adminUser = new AuthUser(username: 'admin', fullName: 'Administrator', enabled: true, password: 'admin', email: 'admin@gmail.com').save(flush: true)

		AuthUserAuthRole.create adminUser, adminRole, true
	}
    def destroy = {
    }
}
