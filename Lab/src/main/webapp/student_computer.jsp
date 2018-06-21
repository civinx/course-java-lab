<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ page import="model.Lab" %>
<%@ page import="model.User" %>
<%@ page import="model.Computer" %>
<%@ page import="model.Record" %>
<%@ page import="model.LabUser" %>
<%@ page import="model.Log" %>
<%@ page import="model.ComputerPK" %>
<%@ page import="java.util.List" %>
<%@ page import="utility.Constants" %>

<rapid:override name="body">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">实验室</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <%
        Lab lab = (Lab) request.getAttribute(Constants.ATTRIBUTE_LAB);
    %>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <%=lab.getLabName()%>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>电脑编号</th>
                            <th>电脑IP</th>
                            <th>电脑位置</th>
                            <th>电脑状态</th>
                            <th>开始上机</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Computer> computerList = (List) request.getAttribute(Constants.ATTRIBUTE_COMPUTER_LIST);
                            User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
                            if (computerList == null) return;;
                            for (int i = 0; i < computerList.size(); i ++) {
                                Computer computer = (Computer) computerList.get(i);
                        %>
                        <tr class="odd gradeX">
                            <td><%=i+1%></td>
                            <td><%=computer.getLabId()+"-"+computer.getComputerId()%></td>
                            <td><%=computer.getComputerIp()%></td>
                            <td><%=computer.getComputerLoc()%></td>
                            <td><%=Constants.MAP_STATE[computer.getComputerState()]%></td>

                            <% if (computer.getComputerState() == Constants.STATE_USED && computer.getUserId() != user.getUserId()) {%>
                            <td><button type="button" class="btn btn-danger disabled"> 已被使用 </button></td>
                            <%} else if (computer.getComputerState() == Constants.STATE_USED && computer.getUserId() == user.getUserId()) {%>
                            <td><button id="<%="end-btn" + (i+1)%>" value="<%=computer.getComputerIp()%>" type="button" class="btn btn-warning"> 下机 </button></td>
                            <%} else { %>
                            <td><button id="<%="start-btn" + (i+1)%>" value="<%=computer.getComputerIp()%>" type="button" class="btn btn-primary"> 开始上机 </button></td>
                            <%}%>

                        </tr>
                        <%}%>


                        </tbody>
                    </table>
                    <!-- /.table-responsive -->

                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <script>
        $(window).ready(function () {
            for (var i = 0; i < <%=computerList.size()%>; i ++) {
                $("#start-btn"+(i+1)).click(function() {
                    start($(this).attr("value"));
                });
                $("#end-btn"+(i+1)).click(function() {
                    end($(this).attr("value"));
                });
            }

            function start(computerIp) {
                var data = {"computerIp": computerIp};
                $.ajax({
                        type: "POST",
                        url: "/home/student/computer/start",
                        contentType: "application/json",
                        data: JSON.stringify(data),
                        dataType: "json",
                        success: function (result) {
                            if (result.success !== true) {
                                alert(result.msg);
                                return;
                            }
                            $(location).attr('href', "/home/student/computer?labId=" + <%=lab.getLabId()%>);

                        },
                        error:
                            function (result, status) {
                                console.log(result);
                            }
                    }
                )
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
                            $(location).attr('href', "/home/student/computer?labId=" + <%=lab.getLabId()%>);
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
</rapid:override>

<jsp:include page="/base.jsp" flush="true"></jsp:include>