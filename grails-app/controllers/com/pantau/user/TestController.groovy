package com.pantau.user
import grails.plugin.springsecurity.SpringSecurityUtils

import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.web.WebAttributes
import com.pantau.core.*

import javax.servlet.http.HttpServletResponse

//import grails.plugin.springsecurity.SecurityFilterPosition
//lat=-6.2087634&lng=106.84559899999999&rad=0.1

@Secured('permitAll')
class TestController {

    def index() {
		
		BigDataRequestModel test = new BigDataRequestModel()
		println test.getNearby ("-6.2087634", "106.84559899999999", "0.1")
		
	}
}
