<%@ page import="utility.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Lab" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="/home/lab/member/add_action">
                        <fieldset id="add-form">
                            <%--style="display: none"--%>
                            <div class="form-group">
                                <input class="form-control" style="display: none" id="labId" name="labId" type="text" value="<%=request.getParameter("labId")%>">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="电脑ID" id="computerId" name="computerId" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="电脑IP" id="computerIpLast" name="computerIpLast" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="电脑位置" id="computerLoc" name="computerLoc" type="text" autofocus>
                            </div>

                            <input id="add-btn" value="添加" class="btn btn-lg btn-success btn-block">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%
    Lab lab = (Lab) request.getAttribute(Constants.ATTRIBUTE_LAB);
%>


<!-- jQuery -->
<script src="/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/dist/js/sb-admin-2.js"></script>

<script>
    $(window).ready(function () {
        $("#add-btn").click(function() {
            add();
        });
        function add() {
            $.ajax({
                    type: "POST",
                    url: "/home/lab/computer/add_action",
                    data: $('#add-form').serialize(),
                    dataType: "json",
                    success: function (result) {
                        alert(result);
                        if (result.code !== 'SUCCESS') {
                            console.log(result.code)
                            alert(result.msg);
                        }
                        $(location).attr('href', "/home/lab/computer?labId=" + <%=lab.getLabId()%>);
                    },
                    error: function (result, status) {
                        console.log(result);
                        alert(result);
                    }
                }
            )
        }
    })

</script>
</body>

</html>
