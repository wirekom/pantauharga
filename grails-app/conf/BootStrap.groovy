import com.pantau.core.Comodity
import com.pantau.core.ComodityType
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

        def adminUser = new AuthUser(username: 'admin', enabled: true, password: 'admin', email: 'admin@gmail.com', nohp: '8098080980').save(flush: true)

        AuthUserAuthRole.create adminUser, adminRole, true

        new ComodityType(name: 'Beras', weight: 0.5).addToComodity(new Comodity(sku: '1', name: 'Beras Medium'))
                .addToComodity(new Comodity(sku: '2', name: 'Beras Pera'))
                .addToComodity(new Comodity(sku: '3', name: 'Beras Premium'))
                .save(flush: true)


        new ComodityType(name: 'Gula', weight: 0.2)
                .addToComodity(new Comodity(sku: '4', name: 'Gula Pasir'))
                .save(flush: true)
        new ComodityType(name: 'Daging', weight: 0.3)
                .addToComodity(new Comodity(sku: '2', name: 'Daging Sapi Paha Belakang'))
                .addToComodity(new Comodity(sku: '2', name: 'Daging Sapi Murni'))
                .save(flush: true)

    }
    def destroy = {
    }
}
