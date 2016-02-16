package com.pantau

import org.springframework.web.servlet.support.RequestContextUtils as RCU

class BootstrapTagLib {
    static defaultEncodeAs = [paginate: 'html']
	
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	def paginate = { attrs ->

		if (attrs.total == null) {
			throwTagError("Tag [paginate] is missing required attribute [total]")
		}
		def messageSource = grailsAttributes.messageSource
		def locale = RCU.getLocale(request)

		def total = attrs.int('total') ?: 0
		def action = (attrs.action ? attrs.action : (params.action ? params.action : "index"))
		def offset = params.int('offset') ?: 0
		def max = params.int('max')
		def maxsteps = (attrs.int('maxsteps') ?: 10)

		if (!offset) offset = (attrs.int('offset') ?: 0)
		if (!max) max = (attrs.int('max') ?: 10)

		def linkParams = [:]
		if (attrs.params) linkParams.putAll(attrs.params)
		linkParams.offset = offset - max
		linkParams.max = max
		if (params.sort) linkParams.sort = params.sort
		if (params.order) linkParams.order = params.order

		def linkTagAttrs = [action:action]
		if (attrs.namespace) {
			linkTagAttrs.namespace = attrs.namespace
		}
		if (attrs.controller) {
			linkTagAttrs.controller = attrs.controller
		}
		if (attrs.id != null) {
			linkTagAttrs.id = attrs.id
		}
		if (attrs.fragment != null) {
			linkTagAttrs.fragment = attrs.fragment
		}
		//add the mapping attribute if present
		if (attrs.mapping) {
			linkTagAttrs.mapping = attrs.mapping
		}

		linkTagAttrs.params = linkParams

		def cssClasses = "pagination"
		if (attrs.class) {
			cssClasses = "pagination " + attrs.class
		}

		// determine paging variables
		def steps = maxsteps > 0
		int currentstep = (offset / max) + 1
		int firststep = 1
		int laststep = Math.round(Math.ceil(total / max))

		out << "<ul class=\"${cssClasses}\">"
		// display previous link when not on firststep
		if (currentstep > firststep) {
			linkParams.offset = offset - max
			out << '<li class="prev">'
			out << link(linkTagAttrs.clone()) {
				(attrs.prev ?: messageSource.getMessage('paginate.prev', null, '&laquo;', locale))
			}
			out << '</li>'
		}
		else {
			out << '<li class="prev disabled">'
			out << '<span>'
			out << (attrs.prev ?: messageSource.getMessage('paginate.prev', null, '&laquo;', locale))
			out << '</span>'
			out << '</li>'
		}

		// display steps when steps are enabled and laststep is not firststep
		if (steps && laststep > firststep) {
			linkTagAttrs.class = 'step'

			// determine begin and endstep paging variables
			int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
			int endstep = currentstep + Math.round(maxsteps / 2) - 1

			if (beginstep < firststep) {
				beginstep = firststep
				endstep = maxsteps
			}
			if (endstep > laststep) {
				beginstep = laststep - maxsteps + 1
				if (beginstep < firststep) {
					beginstep = firststep
				}
				endstep = laststep
			}

			// display firststep link when beginstep is not firststep
			if (beginstep > firststep) {
				linkParams.offset = 0
				out << '<li>'
				out << link(linkTagAttrs.clone()) {firststep.toString()}
				out << '</li>'
				out << '<li class="disabled"><span>...</span></li>'
			}

			// display paginate steps
			(beginstep..endstep).each { i ->
				if (currentstep == i) {
					out << "<li class=\"active\">"
					out << "<span>${i}</span>"
					out << "</li>";
				}
				else {
					linkParams.offset = (i - 1) * max
					out << "<li>";
					out << link(linkTagAttrs.clone()) {i.toString()}
					out << "</li>";
				}
			}

			// display laststep link when endstep is not laststep
			if (endstep < laststep) {
				out << '<li class="disabled"><span>...</span></li>'
				linkParams.offset = (laststep -1) * max
				out << '<li>'
				out << link(linkTagAttrs.clone()) { laststep.toString() }
				out << '</li>'
			}
		}

		// display next link when not on laststep
		if (currentstep < laststep) {
			linkParams.offset = offset + max
			out << '<li class="next">'
			out << link(linkTagAttrs.clone()) {
				(attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, '&raquo;', locale))
			}
			out << '</li>'
		}
		else {
			linkParams.offset = offset + max
			out << '<li class="disabled">'
			out << '<span>'
			out << (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, '&raquo;', locale))
			out << '</span>'
			out << '</li>'
		}

		out << '</ul>'
	}
}
