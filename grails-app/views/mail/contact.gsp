<%--
  Created by IntelliJ IDEA.
  User: Mayank
  Date: 9/15/2014
  Time: 3:01 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Test Mail</title>

    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${resource(dir: 'css', file: 'freelancer.css')}" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${resource(dir: 'font-awesome-4.1.0/css', file: 'font-awesome.min.css')}" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <div class="modal-body">
                <h2>Asynchronous Mail API</h2><g:link controller="API" action="index" class="btn">Home</g:link>
                <hr class="star-primary">
                <img src="${resource(dir: 'images/portfolio', file: 'sendMail.png')}" class="img-responsive img-centered" alt="">
                <br>
                <p>
                    The Mail API that is used here is asynchronous in nature that means user will not have to wait to get the site active after the mail is send.
                </p>

                <g:if test='${flash.message}'>
                    <div class='text-warning'>${flash.message}</div>
                </g:if>

                <g:form controller="mail" action="sendMyMail">

                    <g:textField name="mailId" style=" height:40px;" class='col-lg-12' placeholder="Email"/><br><br>
                    <g:hiddenField name="tomailId" value="mayrittech@gmail.com"/>
                    <g:textField name="subject" style=" height:40px;" class='col-lg-12' placeholder="Subject"/><br><br>
                    <g:textArea name="message" style=" height:140px;" class='col-lg-12' placeholder="Message"/><br><br><br>

                    <g:submitButton name="Submit" value="Submit" class="btn btn-success"/>

                </g:form>

            </div>
        </div>
    </div>
</div>
<br>
<br>
</body>
</html>