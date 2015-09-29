class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }
        /*
        "/api/authUsers"(resources: "authUser")
        "/api/authRoles"(resources: "authRole")
        "/api/comodities"(resources: "comodity")
        "/api/comodityInputs"(resources: "comodityInput")
        "/api/comodityTypes"(resources: "comodityType")
        "/"(controller: 'comodityInput', action: 'map')
        */
        "500"(view: '/error')
        "404"(view: '/error')
        "403"(view: '/error')
    }
}
