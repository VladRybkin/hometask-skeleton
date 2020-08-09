
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
        <td><a href="/events/getbyid/${ev.id}">${ev.name}</a></td>
        <td>${ev.basePrice}</td>
        <td><form action="/events/remove/${ev.id}">
             <input type="submit" value="remove" />
             </form></td>
      </tr>
    </#list>
  </table>
 <br/>

       <form method="post" action="/events/add"  modelAttribute="event">
            <fieldset>
                <legend>add event</legend>
          <label><input type="text" name="name">event name</label>
          <br>
          <label><input type="number" name="basePrice">base price</label>
           <br>
          <input type="submit" value="add event"><br>
          </fieldset>
      </form>
<br/>
        <h3>get event by name</h3>
     <form method="get" action="/events/getbyname" >
          <label><input type="text" name="name"></label>
          <br>
          <input type="submit" value="get by event name"><br>
      </form>

</body>
</html>