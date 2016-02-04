package com.pantau.misc

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.pantau.core.EmailBlastCommandModel
import com.pantau.user.AuthUser
@Secured(['ROLE_ADMIN'])
class MiscController {
    def mailService
    def index() {
        def emailInstance = new EmailBlastCommandModel()
        [emailInstance: emailInstance]
    }

    def send (EmailBlastCommandModel emailIntance) {

       // if (emailIntance.hasErrors()) {
          //  respond emailIntance.errors, view:'index'
          //  return
        //}
        List<AuthUser> users = AuthUser.list()
        for (AuthUser user : users) {
            mailService.sendMail {
                from "nonreplay@gmail.com"
                to user.email
                subject params.name
                body params.body

            }
            break
        }
        redirect(action: "index")
    }
}
