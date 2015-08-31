<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'comodityInput.label', default: 'ComodityInput')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><i class="fa fa-dashboard"></i> <a class="home" href="${createLink(uri: '/')}"><g:message
                    code="default.home.label"/></a></li>
            <li class="active"><i class="fa fa-list"></i> <g:link class="list" action="index"><g:message
                    code="default.list.label" args="[entityName]"/></g:link></li>
        </ol>
    </div>
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <g:if test="${flash.message}">
            <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${inflationCommandModelInstance}">
            <ul class="alert alert-danger" role="alert">
                <g:eachError bean="${comodityinflationCommandModelInstanceInputInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form url="[action: 'calculateinflation', , controller: 'ComodityInput']" method="GET" role="form">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="fa fa-plus"></i>
                        <g:message code="default.create.label" args="[entityName]"/>
                    </h3>
                </div>

                <div class="panel-body">
                    <div class="form-group">
                        <label for="price">
                            <g:message code="comodityInput.start.label" default="Start Date"/>
                            <span class="required-indicator">*</span>
                        </label>
                        <g:datePicker name="start" value="${inflationCommandModelInstance?.start}" precision="month"
                                      noSelection="['': '-Choose-']" relativeYears="[-2..7]"/>
                    </div>

                    <div class="form-group">
                        <label for="price">
                            <g:message code="comodityInput.end.label" default="End Date"/>
                            <span class="required-indicator">*</span>
                        </label>
                        <g:datePicker name="end" value="${inflationCommandModelInstance?.end}" precision="month"
                                      noSelection="['': '-Choose-']" relativeYears="[-2..7]"/>
                    </div>
                </div>

                <div class="panel-footer">
                    <g:submitButton name="calculate" class="btn btn-primary save"
                                    value="${message(code: 'default.button.calculates.label', default: 'Calculate')}"/>
                </div>
            </div>
        </g:form>
        <div class="clearfix"></div>
        <span class="inflasi text-success">inflasinya : <g:fieldValue bean="${inflationCommandModelInstance}" field="inflation"/> %</span>
    </div>
</div>
</body>
</html>
