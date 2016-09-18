<!doctype html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>

  <h4>Hello, ${user}</h4>
  <div>
  <form action="./login" method="post">
  	<div>
  		<label>User Name: </label>
  		<input type="text" name="username">
  	</div>
  	<div>
  		<label>password: </label>
  		<input type="password" name="password">
  	</div>
  	<div>
  	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  		<input type="submit" name="submit" value="Login">
  	</div>
  </form>
  </div>
</div>
</body>
</html>
