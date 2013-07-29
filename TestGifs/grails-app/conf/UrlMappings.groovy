class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'Gif', action:'index')// view:"/index")
		"500"(view:'/error')
	}
}
