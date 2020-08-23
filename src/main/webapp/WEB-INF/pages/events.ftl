
<!DOCTYPE html>
<head>
    <title>events</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


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

     <form method="get" action="/events/getbyname" >
      <fieldset>
                     <legend>get by name</legend>
          <label><input type="text" name="name"></label>
          <br>
          <input type="submit" value="get by event name"><br>
       </fieldset>
      </form>
<br/>
     <form method="post" action="/events/upload">
            <fieldset>
                <legend>upload events</legend>
          <label><input type="file" name="eventsFile">event name</label>
          <br>

          <input type="submit" value="upload"><br>
          </fieldset>
      </form>




<br/>
 <form action="/welcome">
        <input type="submit" value="main page" />
  </form>
  <br/>

</body>
</html>