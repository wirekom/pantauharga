<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Pantau Harga Application</title>
</head>
<body>


	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Welcome Page <small>Pantau Harga Application</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-3">
			<h1>Application Status</h1>
			<ul class="list-group">
				<li class="list-group-item">App version: <g:meta
						name="app.version" /></li>
				<li class="list-group-item">Grails version: <g:meta
						name="app.grails.version" /></li>
				<li class="list-group-item">Groovy version: ${GroovySystem.getVersion()}</li>
				<li class="list-group-item">JVM version: ${System.getProperty('java.version')}</li>
				<li class="list-group-item">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li class="list-group-item">Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li class="list-group-item">Domains: ${grailsApplication.domainClasses.size()}</li>
				<li class="list-group-item">Services: ${grailsApplication.serviceClasses.size()}</li>
				<li class="list-group-item">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h1>Installed Plugins</h1>
			<ul class="list-group">
				<g:each var="plugin"
					in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li class="list-group-item">
						${plugin.name} - ${plugin.version}
					</li>
				</g:each>
			</ul>
		</div>
		<div class="col-lg-9">
			<h1>Welcome to Grails</h1>
			<p>Congratulations, you have successfully started your first
				Grails application! At the moment this is the default page, feel
				free to modify it to either redirect to a controller or display
				whatever content you may choose. Below is a list of controllers that
				are currently deployed in this application, click on each to execute
				its default action:</p>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul class="list-group">
					<g:each var="c"
						in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="list-group-item"><g:link
								controller="${c.logicalPropertyName}">
								${c.fullName}
							</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

<g:javascript src="tinymce/tinymce.min.js"/>
<g:javascript>
	$(function(){
tinyMCE.init({
    theme : "modern",
    mode : "textareas",
    plugins: [
            "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak spellchecker",
            "searchreplace wordcount code fullscreen nonbreaking",
            "table contextmenu directionality textcolor paste fullpage textcolor colorpicker textpattern"
    ],
    toolbar1: "newdocument fullpage | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
    toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | code preview | forecolor backcolor",
    toolbar3: "table | hr removeformat | subscript superscript | fullscreen | ltr rtl | spellchecker | nonbreaking pagebreak restoredraft",

    editor_selector : "html-editor",
    menubar: false,
    toolbar_items_size: 'small',
});
	});
</g:javascript>