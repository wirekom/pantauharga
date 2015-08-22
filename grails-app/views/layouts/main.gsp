<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Pantau Harga Application"/></title>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <g:layoutHead/>
</head>

<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${createLink(uri: '/')}">Pantau Harga
            App</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <sec:ifLoggedIn>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"><i class="fa fa-user"></i> <sec:loggedInUserInfo
                            field="username"/> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-fw fa-envelope"></i>
                            Inbox</a></li>
                        <li><a href="#"><i class="fa fa-fw fa-gear"></i>
                            Settings</a></li>
                        <li class="divider"></li>
                        <li><g:remoteLink class="logout" controller="logout"
                                          method="post" asynchronous="false"
                                          onSuccess="location.reload()">
                            <i class="fa fa-fw fa-sign-out"></i> Logout</g:remoteLink></li>
                    </ul></li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <li><g:link controller='login' action='auth'>
                    <i class="fa fa-fw fa-sign-in"></i> Login</g:link></li>
            </sec:ifNotLoggedIn>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="${createLink(uri: '/')}"><i
                        class="fa fa-fw fa-dashboard"></i> Dashboard
                </a></li>
                <sec:ifAnyGranted
                        roles="ROLE_TENANT_ADMIN, ROLE_TENANT_PARKING, ROLE_TENANT_PHONE, ROLE_TENANT_RFID, ROLE_TENANT_SIKB, ROLE_TENANT_OVERTIME">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#panelPantau Harga"><i class="fa fa-fw fa-user"></i>
                        Panel Pantau Harga <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="panelPantau Harga" class="collapse">
                            <li><g:link controller="profile" action="profile">Profile</g:link></li>
                            <sec:ifAllGranted roles="ROLE_TENANT_ADMIN	">
                                <li><g:link controller="profile" action="tenant">Pantau Harga Account</g:link></li>
                            </sec:ifAllGranted>
                            <li><g:link controller="profile" action="ticket">Ticket</g:link></li>
                            <li><g:link controller="profile" action="invoice">Invoice</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted
                        roles="ROLE_TENANT_ADMIN, ROLE_TENANT_PARKING, ROLE_TENANT_PHONE, ROLE_TENANT_RFID, ROLE_TENANT_SIKB, ROLE_TENANT_OVERTIME">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#panelRequest"><i class="fa fa-fw fa-question"></i>
                        Request <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="panelRequest" class="collapse">
                            <sec:ifAnyGranted roles="ROLE_TENANT_ADMIN, ROLE_TENANT_PARKING">
                                <li><g:link controller="carParkingSticker">Car Parking Sticker</g:link></li>
                                <li><g:link
                                        controller="motorcycleParkingSticker">Motorcycle Parking Sticker</g:link></li>
                                <li><g:link controller="reservedLot">Reserved Lot</g:link></li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_TENANT_ADMIN, ROLE_TENANT_PHONE">
                                <li><g:link controller="phoneRequest">Telephone</g:link></li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_TENANT_ADMIN, ROLE_TENANT_RFID">
                                <li><g:link controller="RFID">RFID</g:link></li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_TENANT_ADMIN, ROLE_TENANT_SIKB">
                                <li><g:link controller="SIKB">SIKB</g:link></li>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_TENANT_ADMIN, ROLE_TENANT_OVERTIME">
                                <li><g:link controller="overTime">Over Time</g:link></li>
                            </sec:ifAnyGranted>
                        </ul></li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MARKETING">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#prospectiveCustomer"><i
                                class="fa fa-fw fa-table"></i> Prospective Customer <i
                                class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="prospectiveCustomer" class="collapse">
                            <li><g:link controller="leaseProposal" action="create">Create Proposal</g:link></li>
                        </ul></li>
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#manageLease"><i
                                class="fa fa-fw fa-table"></i> Manage Lease <i
                                class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="manageLease" class="collapse">
                            <li><g:link controller="customer" action="create">Input Pantau Harga Detail</g:link></li>
                            <li><g:link controller="leaseConfirmation">Lease Confirmation</g:link></li>
                            <li><g:link controller="tenant">Pantau Harga Account</g:link></li>
                            <li><g:link controller="leaseAsset">Manage Unit</g:link></li>
                            <li><g:link controller="freeFacility">Free Facility</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#manageRequest"><i class="fa fa-fw fa-question"></i>
                        Manage Request <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="manageRequest" class="collapse">
                            <li><g:link controller="carParkingSticker" action="admin">Car Parking Sticker</g:link></li>
                            <li><g:link controller="motorcycleParkingSticker"
                                        action="admin">Motorcycle Parking Sticker</g:link></li>
                            <li><g:link controller="reservedLot" action="admin">Reserved Lot</g:link></li>
                            <li><g:link controller="phoneRequest" action="admin">Phone</g:link></li>
                            <li><g:link controller="RFID" action="admin">RFID</g:link></li>
                            <li><g:link controller="SIKB" action="admin">SIKB</g:link></li>
                            <li><g:link controller="overTime" action="admin">Over Time</g:link></li>
                            <li><g:link controller="ticket">Manage Ticket</g:link></li>
                            <li><g:link controller="ticket" action="ownTicket">My Ticket</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_HRD">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#payroll"><i class="fa fa-fw fa-list"></i>
                        Payroll <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="payroll" class="collapse">
                            <li><g:link controller="employee">Employee</g:link></li>
                            <li><g:link controller="department">Department</g:link></li>
                            <li><g:link controller="position">Position</g:link></li>
                            <li><g:link controller="salary">Salary</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ACCOUNTING">
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#accounting"><i class="fa fa-fw fa-list"></i>
                        Accounting <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="accounting" class="collapse">
                            <li><g:link controller="leaseConfirmation" action="finance">Lease Confirmation</g:link></li>
                            <li><g:link controller="account">Account</g:link></li>
                            <li><g:link controller="AccountingForm">Account Form</g:link></li>
                            <li><g:link controller="JournalEntry">Journal Entry</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">

                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#parking"><i class="fa fa-fw fa-list"></i> Parking
                        <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="parking" class="collapse">
                            <li><g:link controller="parkingPrice">Parking Price</g:link></li>
                        </ul></li>
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#master"><i class="fa fa-fw fa-list"></i> Master
                        <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="master" class="collapse">
                            <li><g:link controller="parameter">Parameter</g:link></li>
                            <li><g:link controller="counter">Counter</g:link></li>
                            <li><g:link controller="religion">Religion</g:link></li>
                            <li><g:link controller="education">Education</g:link></li>
                            <li><g:link controller="phone">Phone</g:link></li>
                            <li><g:link controller="ticketType">Ticket Type</g:link></li>
                            <li><g:link controller="documentType">Document Type</g:link></li>
                            <li><g:link controller="emergencyType">Emergency Type</g:link></li>
                            <li><g:link controller="typeAccount">Type Account</g:link></li>
                            <li><g:link controller="billingType">Billing Type</g:link></li>
                            <li><g:link controller="goods">Goods</g:link></li>
                        </ul></li>
                    <li><a href="javascript:;" data-toggle="collapse"
                           data-target="#administration"><i class="fa fa-fw fa-gear"></i>
                        Administration <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="administration" class="collapse">
                            <li><g:link controller="authUser">User</g:link></li>
                            <li><g:link controller="authRole">Role</g:link></li>
                        </ul></li>
                </sec:ifAnyGranted>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">
            <g:layoutBody/>
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
</body>
</html>