<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
</title>
</head>
<body>
<table class="table">
    <tr>
        <td colspan="2" align="center">
        	<tiles:insertAttribute name="header"></tiles:insertAttribute>
        </td>
    </tr>
    <tr>
        <td>
        	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
        </td>
        <td>
        	<tiles:insertAttribute name="body"></tiles:insertAttribute>
        </td>
    </tr>
    <tr>
        <td colspan="2"  align="center">
        	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
        </td>
    </tr>
</table>
<script type="text/javascript">
$(document).ready(function() {
    $('#empTable').dataTable();
} );
</script>
</body>
</html>