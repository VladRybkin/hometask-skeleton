
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <title>Simple JSP Application</title>
</head>
<body>
<h1>events page</h1>
<h2></h2>
<br/>
<h1>${oneObj}</h1>
<br/>

<br/>
<table class="datatable">
    <tr>
      <th>id</th>
      <th>name</th>
      <th>basePrice</th>
    </tr>
    <#list events as ev>
      <tr>
        <td>${ev.id}</td>
        <td>${ev.name}</td>
        <td>${ev.basePrice}</td>
      </tr>
    </#list>
  </table>

</body>
</html>