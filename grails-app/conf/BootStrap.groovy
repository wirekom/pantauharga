import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole


class BootStrap {

    def init = {
		def adminRole = new AuthRole(authority: 'ROLE_ADMIN')
		def userRole = new AuthRole(authority: 'ROLE_USER')
		adminRole.save()
		userRole.save()
	
		def testUser = new AuthUser(username: 'admin', enabled: true, password: 'admin', email: 'coding.stelsel@gmail.com')
		testUser.save()
	///	testUser.validate()
	//	println testUser.errors
		//AuthUserAuthRole.create testUser, adminRole, true
		def UserRole = new AuthUserAuthRole(authUser:testUser, authRole:adminRole)
		UserRole.save()
	//	assert AuthUser.count() == 1
	//	assert AuthRole.count() == 2
	//	assert AuthUserAuthRole.count() == 1
	}
    def destroy = {
    }
}
