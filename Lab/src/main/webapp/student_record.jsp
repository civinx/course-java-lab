<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="model.Lab" %>
<%@ page import="model.User" %>
<%@ page import="model.Computer" %>
<%@ page import="model.Record" %>
<%@ page import="model.LabUser" %>
<%@ page import="model.Log" %>
<%@ page import="model.ComputerPK" %>
<%@ page import="java.util.List" %>
<%@ page import="utility.Constants" %>
<%@ page import="static utility.Constants.SESSION_USER" %>
<%@ page import="static utility.Constants.ATTRIBUTE_COMPUTER_LIST" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Lab-Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/vendor/morrisjs/morris.css" rel="stylesheet">

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

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">实验室管理系统</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="/login"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">

                    <li>
                        <a href="/home"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="/home/user"><i class="fa fa-user fa-fw"></i> 用户</a>
                    </li>
                    <li>
                        <a href="/home/lab"><i class="fa fa-building fa-fw"></i> 实验室</a>
                    </li>
                    <li>
                        <a href="/home/student/lab"><i class="fa fa-building fa-fw"></i> 学生界面 </a>
                    </li>

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">我的上机记录</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <%
            User user = (User) request.getSession().getAttribute(SESSION_USER);
        %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <%=user.getUserNick()%>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>记录编号</th>
                                <th>实验室名称</th>
                                <th>电脑编号</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
//                                int test = (Integer) request.getAttribute("test");
//                                System.out.println(test);
                                List<Record> recordList = (List) request.getAttribute(Constants.ATTRIBUTE_RECORD_LIST);
                                List<Lab> labList = (List) request.getAttribute(Constants.ATTRIBUTE_LAB_LIST);
                                List<Computer> computerList = (List) request.getAttribute(Constants.ATTRIBUTE_COMPUTER_LIST);
                                if (labList == null) {
                                    System.out.println(222);
                                    return;
                                }
                                if (recordList == null) {
                                    System.out.println(111);
                                    return;
                                }

                                for (int i = 0; i < recordList.size(); i ++) {
                                    Record record = recordList.get(i);
                                    Lab lab = labList.get(i);
                                    Computer computer = computerList.get(i);
                            %>
                            <tr class="odd gradeX">
                                <td><%=record.getRecordId()%></td>
                                <td><%=lab.getLabName()%></td>
                                <td><%=record.getComputer().getLabId() + "-" + record.getComputerId()%></td>
                                <td><%=record.getRecordStartTime()%></td>
                                <% if (record.getRecordEndTime() == null) {%>
                                <td><button id="<%="end-btn" + (i+1)%>" value="<%=computer.getComputerIp()%>" type="button" class="btn btn-warning"> 下机 </button></td>
                                <% } else { %>
                                <td><%=record.getRecordEndTime()%></td>
                                <%}%>
                            </tr>
                            <%}%>

                            </tbody>
                        </table>
                        <!-- /.table-responsive -->
                        <div class="well">
                            <%--<h4>DataTables Usage Information</h4>--%>
                            <%--<p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>--%>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/vendor/raphael/raphael.min.js"></script>
<script src="/vendor/morrisjs/morris.min.js"></script>
<script src="data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/dist/js/sb-admin-2.js"></script>

<script>
    $(window).ready(function () {
        for (var i = 0; i < <%=computerList.size()%>; i ++) {

            $("#end-btn"+(i+1)).click(function() {
                end($(this).attr("value"));
            });
        }

        function end(computerIp) {
            var data = {"computerIp": computerIp};
            $.ajax({
                    type: "POST",
                    url: "/home/student/computer/end",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    dataType: "json",
                    success: function (result) {
                        if (result.success !== true) {
                            alert(result.msg);
                            return;
                        }
                        $(location).attr('href', "/home/student/record");
                    },
                    error:
                        function (result, status) {
                            console.log(result);
                        }
                }
            )
        }
    })

</script>
</body>

</html>
