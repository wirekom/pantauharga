package com.pantau.user

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

class ProfileController {


    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def changePassword() {
        respond new ChangePassword()
    }

    @Transactional
    def change(ChangePassword changePasswordInstance) {
        if (changePasswordInstance == null) {
            notFound()
            return
        }

        if (changePasswordInstance.hasErrors()) {
            respond changePasswordInstance.errors, view: 'changePassword'
            return
        }
        def currentUser = springSecurityService.currentUser
        currentUser.password = changePasswordInstance?.newPassword
        currentUser.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.changePassword.message', args: [
                        message(code: 'author.label', default: 'Change Password')
                ])
                redirect changePasswordInstance
            }
            '*' { respond changePasswordInstance, [status: OK] }
        }
    }
}
