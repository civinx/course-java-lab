<%@ page language="java" pageEncoding="UTF-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">注册</h3>
                    </div>
                    <div class="panel-body">
                            <fieldset id="form">
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" id = "userName" name="userName" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" id = "userPassword" name="userPassword" type="password" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="昵称" id = "userNick" name="userNick" type="text" value="">
                                </div>

                                <!-- Change this to a button or input when using this as a form -->
                                <%--<a href="regis" class="btn btn-lg btn-success btn-block">注册</a>--%>
                                <input id="start-btn" type="submit" value="注册" class="btn btn-lg btn-success btn-block">
                            </fieldset>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/dist/js/sb-admin-2.js"></script>

    <script type="text/javascript" src="/jquery.serializejson.js"></script>

    <script>
        $(window).ready(function () {
            $("#start-btn").click(function() {
                start();
            })
            function start() {
                // var obj = $('#form').serializeJSON();
                // var data = JSON.stringify(obj);
                // data = JSON.stringify(data);
                var name = $('#userName').val();
                var password = $('#userPassword').val();
                var nick = $('#userNick').val();
                var data = {"userName": name,
                            "userPassword": password,
                            "userNick": nick};
                $.ajax({
                        type: "POST",
                        url: "register_action",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        success: function (result) {
                            if (result.success !== true) {
                                alert(result.msg);
                                return;
                            }
                            $(location).attr('href', "/login");
                        },
                        error:
                            function (result, status) {
                                alert("error");
                            }
                    }
                )
            }
        })

    </script>
</body>

</html>
