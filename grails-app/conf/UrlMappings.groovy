class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }
        "/api/comodityInputs"(resources: 'comodityInput', namespace: 'api')
        "/api/comodities"(resources: 'comodity', namespace: 'api')
        "/api/comodityTypes"(resources: 'comodityType', namespace: 'api')
        "/api/authUsers"(resources: 'authUser', namespace: 'api')
        "/api/guest/register"(resources: 'authUser', namespace: 'api', action: 'create')
        "/api/guest/test"(resources: 'test', namespace: 'api', action: 'index')
        "/"(controller: 'page', action: 'index')
        "500"(view: '/error')
        "404"(view: '/error')
        "403"(view: '/error')
    }
}
