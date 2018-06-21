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
            <h1 class="page-header">我的实验室</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    实验室列表
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>实验室ID</th>
                            <th>实验室名称</th>
                            <th>实验室网段</th>
                            <th>选择电脑上机</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER));
                            List<Lab> labList = (List) request.getAttribute(Constants.ATTRIBUTE_LAB_LIST);
                            if (labList == null) return;
                            for (Lab lab : labList) {
                                if (lab.getLabGate() == Constants.STATE_DELETE) continue;
                        %>
                        <tr class="odd gradeX">
                            <td><%=lab.getLabId()%></td>
                            <td><%=lab.getLabName()%></td>
                                <%--<td><%=Constants.MAP_STATE[lab.getLabState()]%></td>--%>
                            <td><%=Constants.IP_GATE + lab.getLabGate() + ".0~255"%></td>
                            <td><button type="button" class="btn btn-primary"
                                        onclick="window.location.href='/home/student/computer?labId=<%=lab.getLabId()%>'"> 选择电脑上机  </button></td>
                        </tr>
                        <%}%>
                        </tbody>

                    </table>
                    <div class="well">
                            <%--<h4>DataTables Usage Information</h4>--%>
                            <%--<p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>--%>
                        <a class="btn btn-default btn-lg btn-block" target="_blank" href="/home/student/record?userId=<%=user.getUserId()%>">我的上机记录</a>

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