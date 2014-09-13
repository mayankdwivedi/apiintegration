<html>
<head>
    <title> FrontRunnerApp </title>
    <meta name="layout" content="main">
</head>
<body>

<div class="content">
  <div class="row">
    <form action='${postUrl}' method='POST' id="loginForm" name="loginForm">
      <div class="span6 offset3">
        <div class="well">
            <g:if test="${flash.message}">

                        <h2>${flash.message}</h2>

            </g:if>

          <h3 class="lead">Log In</h3>
          <div class="divider"></div>
          <br />
          <input type="text" class="span5" placeholder="Email / Username " name="j_username" id="j_username" value="${session['SPRING_SECURITY_LAST_USERNAME']}" />

          <label for="password"></label>
          <input type="password" placeholder="Password" class="span5" name="j_password" id="j_password" />
          <br>
          <input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
          <g:message code='spring.security.ui.login.rememberme'/> &nbsp;|&nbsp;
          <g:link controller="user" action="resetPassword" >Forgot your password?</g:link>
          <br>
          <br>
          <input class="btn btn-success" id="user_submit" name="commit" type="submit" value="Log in" />
        </div>

        <div class="well">
          <p>
            <br>
            <g:link controller="employer" action="register" >Sign up</g:link><br>
            <g:link controller="user" action="resetPassword" >Forgot your password?</g:link><br />
            <a href="/confirmation/new">Didn't receive confirmation instructions?</a><br />
            <a href="/auth/linked_in">Sign in with LinkedIn</a>
          </p>
        </div>

          <div class="well">
          <p>
            <br>
            <g:link controller="employer" action="register" >Register as Employer</g:link><br>
          </p>
        </div>
      </div>


    </form>
  </div>
</div>

</body>
</html>  
