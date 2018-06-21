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
            <h1 class="page-header">账号管理</h1>
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
                            <th>用户ID</th>
                            <th>用户账号</th>
                            <th>用户昵称</th>
                            <th>用户类型</th>
                            <th>用户状态</th>
                            <th>删除操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<User> userList = (List) request.getAttribute(Constants.ATTRIBUTE_USER_LIST);
                            if (userList == null) return;
                            for (User user : userList) {
                        %>
                        <tr class="odd gradeX">
                            <td><%=user.getUserId()%></td>
                            <td><%=user.getUserName()%></td>
                            <td><%=user.getUserNick()%></td>
                            <td><%=Constants.MAP_USER_TYPE[user.getUserType()]%></td>
                            <td><%=Constants.MAP_STATE[user.getUserState()]%></td>
                            <%
                                User curUser = (User) request.getSession().getAttribute(SESSION_USER);
                                if (curUser.getUserId() == user.getUserId()) { %>
                            <td><button type="button" class="btn btn-danger" disabled> 不能删自己哦 </button></td>
                            <%} else {%>
                            <td><button type="button" class="btn btn-danger"
                                        onclick="window.location.href='/home/user/delete?userId=<%=user.getUserId()%>'"> 删除 </button></td>
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