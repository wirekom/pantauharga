class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'comodityInput', action: 'map')
        "500"(view:'/error')
        "404"(view:'/error')
        "403"(view:'/error')
	}
}
