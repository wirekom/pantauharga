import com.pantau.core.Comodity
import com.pantau.core.ComodityType
import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
import grails.converters.JSON

class BootStrap {
        def bootstrapService
        def grailsApplication
        def init = { servletContext ->
            if(grailsApplication.config.dataSource.dbCreate == "create-drop") {
                def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
                def userRole = new AuthRole(authority: 'ROLE_USER').save(flush: true)
                def trustedRole = new AuthRole(authority: 'ROLE_TRUSTED').save(flush: true)
                def untrustedRole = new AuthRole(authority: 'ROLE_UNTRUSTED').save(flush: true)
                def specialRole = new AuthRole(authority: 'ROLE_SPECIAL').save(flush: true)

                def adminUser = new AuthUser(username: 'admin', nama: 'admin', enabled: true, password: 'admin', email: 'admin@gmail.com', nohp: '8098080980').save(flush: true)
                //try {
                    AuthUserAuthRole.create adminUser, adminRole, true
              //  def authRoleUserAdmin = new AuthUserAuthRole (useradminUser, adminRole)
                //} catch (Exception e) {
                //    handleException(e, "There was an error while attempting to create the Username")

                //}
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
            JSON.registerObjectMarshaller(Comodity) {
                def returnArray = [:]
                returnArray['id'] = it.id
                returnArray['name'] = it.name
                returnArray['sku'] = it.sku
                return returnArray
            }
            JSON.registerObjectMarshaller(AuthUser) {
                def returnArray = [:]
                returnArray['username'] = it.username
                returnArray['nama'] = it.nama
                returnArray['email'] = it.email
                returnArray['ktp'] = it.ktp
                returnArray['nohp'] = it.nohp
                returnArray['alamat'] = it.alamat
                returnArray['kodepos'] = it.kodepos
                return returnArray
            }

        }
        def destroy = {
        }
}
