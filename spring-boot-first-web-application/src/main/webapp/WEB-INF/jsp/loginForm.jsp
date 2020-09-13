
<%--This is not using- now use spring security default login page--%>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<span style="color: red; ">${error}</span>
<form method="post">
    Name : <input type="text" name="name"/>
    Password : <input type="password" name="password"/>
    <input type="submit"/>
</form>
<%@ include file="common/footer.jspf" %>