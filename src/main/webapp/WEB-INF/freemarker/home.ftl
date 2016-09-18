<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>SAT1 - Home</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">SAT01</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="./Home">Home</a></li>
            <li><a href="./about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
      <div class="row">
        <div class="col-lg-9">
          <h4>User List</h4>

          <div class="btn-group" role="group">
            <button type="button" class="btn btn-default" id="button-add-cust">Add</button>
          </div>
          <div>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>LoginID</th>
                  <th>Nick Name</th>
                  <th>emailAddress</th>
                  <th>status</th>
                </tr>
              </thead>
              <tbody>
                <#list custList as cust>
                <tr>
                  <td>${cust.loginId}</td>
                  <td>${cust.nickName}</td>
                  <td>${cust.emailAddress}</td>
                  <td>${cust.status}</td>
                </tr>
                </#list>
              </tbody>
            </table>
          </div>
        </div>

        <div class="col-lg-3">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Useful links</h3>
            </div>
            <div class="panel-body">
              <ul class="list-group">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Cras justo odio</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" tabindex="-1" role="dialog" id="modal-add-cust">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Add Customer</h4>
          </div>
          <div class="modal-body">
            <form>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf_token"/>
              <div class="form-group">
                <label for="login-id" class="control-label">Login ID:</label>
                <input type="text" class="form-control" id="login-id">
              </div>
              <div class="form-group">
                <label for="nick-name" class="control-label">Nick Name:</label>
                <input type="text" class="form-control" id="nick-name">
              </div>
              <div class="form-group">
                <label for="email-address" class="control-label">Email Address:</label>
                <input type="text" class="form-control" id="email-address">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" id="button-save">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script type="text/javascript">
      function init() {
        $('#modal-add-cust').modal({'show':false});
        $('#button-add-cust').click(popAddPanel);
        $('#button-save').click(submitToServer);
      }

      function popAddPanel(){
        $('#modal-add-cust').modal('show');
      }

      function closeAddPanel(){
        $('#modal-add-cust').modal('hide');
      }

      function submitToServer(e) {
        e.preventDefault();
        var loginid = $('#login-id').val();
        var nickname = $('#nick-name').val();
        var email = $('#email-address').val();
        if (!loginid || !nickname || !email) {
          alert('Please input all value');
          return;
        }

        var data = {
          loginid: loginid,
          nickname: nickname,
          email: email,
          '_csrf': $('#csrf_token').val()
        };

        $.post('/Sat1/view/users', data, function(retdata){
          if (window.console) {
            console.log(retdata);
          }
          closeAddPanel();
        }, 'text');
      }

      $(function(){
        init();
      });

    </script>

  </body>
</html>