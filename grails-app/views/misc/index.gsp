<%--
  Created by IntelliJ IDEA.
  User: GE62
  Date: 2/4/2016
  Time: 1:42 PM
--%>
<%@ page import="com.pantau.core.EmailBlastCommandModel" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'EmailBlastCommandModel.label', default: 'Email')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><i class="fa fa-dashboard"></i> <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li class="active"><i class="fa fa-list"></i> <g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <g:if test="${flash.message}">
                <div class="alert alert-info" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${emailInstance}">
                <ul class="alert alert-danger" role="alert">
                    <g:eachError bean="${emailInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form  controller="Misc" action="send" method="post">

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="fa fa-plus"></i>
                            <g:message code="default.create.label" args="[entityName]" />
                        </h3>
                    </div>

                    <div class="panel-body">
                        <div class="form-group ${hasErrors(bean: emailInstance, field: 'Subject', 'error')} required">
                            <label for="name">
                                <g:message code="EmailBlastCommandModel.name.label" default="Subject" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField class="form-control" name="name" required="" value="${emailInstance?.Subject}"/>

                        </div>
                        <div class="form-group ${hasErrors(bean: emailInstance, field: 'body', 'error')} required">
                            <label for="body">
                                <g:message code="EmailBlastCommandModel.body.label" default="body" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textArea class="form-control" name="body" required="" value="${emailInstance?.Body}"/>

                        </div>
                    </div>

                    <div class="panel-footer">
                        <g:submitButton name="create" class="btn btn-primary save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </div>
                </div>
            </g:form>
            <div class="clearfix"></div>
        </div>
    </div>




</body>
</html>