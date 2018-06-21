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
<%@ page import="static utility.Constants.SESSION_USER" %>

<rapid:override name="body">
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

</rapid:override>

<jsp:include page="/base.jsp" flush="true"></jsp:include>