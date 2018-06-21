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
            <h1 class="page-header">实验室上机记录</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <%
        Lab lab = (Lab) request.getSession().getAttribute(Constants.ATTRIBUTE_LAB);
        List<User> userList = (List) request.getSession().getAttribute(Constants.ATTRIBUTE_USER_LIST);
        List<Record> recordList = (List) request.getSession().getAttribute(Constants.ATTRIBUTE_RECORD_LIST);
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
                            <th>记录编号</th>
                            <th>用户名称</th>
                            <th>电脑编号</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            if (recordList == null) return;;
                            if (userList == null) return;
                            for (int i = 0; i < recordList.size(); i ++) {
                                Record record = recordList.get(i);
                                User user = userList.get(i);
                        %>
                        <tr class="odd gradeX">
                            <td><%=record.getRecordId()%></td>
                            <td><%=user.getUserNick()%></td>
                            <td><%=record.getComputer().getLabId() + "-" + record.getComputerId()%></td>
                            <td><%=record.getRecordStartTime()%></td>
                            <% if (record.getRecordEndTime() == null) {%>
                            <td>还未下机</td>
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
</rapid:override>

<jsp:include page="/base.jsp" flush="true"></jsp:include>