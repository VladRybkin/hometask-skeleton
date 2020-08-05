
<!DOCTYPE html>
<head>
    <title>events</title>
</head>
<body>
<h1>events page</h1>
<h2></h2>
<br/>
<br/>
<table>
    <tr>
      <th>id</th>
      <th>name</th>
      <th>basePrice</th>
      <th>remove</th>
    </tr>
    <#list events as ev>
      <tr>
        <td>${ev.id}</td>
        <td>${ev.name}</td>
        <td>${ev.basePrice}</td>
        <td><form action="/events/remove/${ev.id}">
             <input type="submit" value="remove" />
             </form></td>
      </tr>
    </#list>
  </table>
 <br/>

</body>
</html>