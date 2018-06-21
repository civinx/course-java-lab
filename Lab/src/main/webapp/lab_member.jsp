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
                            <th>人员序号</th>
                            <th>人员名称</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<User> userList = (List) request.getAttribute(Constants.ATTRIBUTE_USER_LIST);
                            if (userList == null) return;
                            for (int i = 0; i < userList.size(); i ++) {
                        %>
                        <tr class="odd gradeX">
                            <td><%=i+1%></td>
                            <td><%=userList.get(i).getUserNick()%></td>
                            <td><a class="btn btn-danger" href='/home/lab/member/delete?labId=<%=lab.getLabId()%>&userId=<%=userList.get(i).getUserId()%>'> 删除 </a></td>
                        </tr>
                        <%}%>


                        </tbody>
                    </table>
                    <!-- /.table-responsive -->
                    <div class="well">
                            <%--<h4>DataTables Usage Information</h4>--%>
                            <%--<p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>--%>
                        <a class="btn btn-default btn-lg btn-block" target="_blank" href="/home/lab/member/add?labId=<%=lab.getLabId()%>">添加成员</a>

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