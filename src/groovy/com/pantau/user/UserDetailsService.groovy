package com.pantau.user

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.transaction.Transactional
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsService implements GrailsUserDetailsService {

    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
     * one role, so we give a user with no granted roles this one which gets
     * past that restriction but doesn't grant anything.
     */
    static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

    UserDetails loadUserByUsername(String username, boolean loadRoles)
            throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    @Transactional(readOnly = true, noRollbackFor = [IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = AuthUser.findByUsernameOrEmailOrNohp(username, username, username)
        if (!user) throw new UsernameNotFoundException('User not found', username)

        def authorities = user.authorities.collect {
            new GrantedAuthorityImpl(it.authority)
        }

        return new GrailsUser(user.username, user.password, user.enabled, !user.accountExpired,
                !user.passwordExpired, !user.accountLocked,
                authorities ?: NO_ROLES, user.id)
    }
}
