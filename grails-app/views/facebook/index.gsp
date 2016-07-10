<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Facebook</title>
</head>

<body>
<g:form class="form-container" method="POST" mapping="springSocialConnect" params="[providerId:'facebook']">
    <h2>Connect with Facebook</h2>
    <input type="hidden" name="scope" value="publish_pages" />
    <div class="formInfo">
        <h6>You aren't connected to Facebook yet.</h6>
        <p> Click the button to connect with your Facebook account.</p>
    </div>
    <button class="btn btn-lg btn-primary"><i class="fa fa-facebook"></i> Login</button>
</g:form>
</body>
</html>