
<!DOCTYPE html>
<head>
    <title>auditoriums</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<h1>auditorium page</h1>

<br/>
<br/>
<table>
    <tr>
      <th>name</th>
      <th>number of seats</th>
      <th>vip seats</th>
    </tr>
    <#list auditoriums as au>
      <tr>
        <td>${au.name!}</td>
        <td>${au.numberOfSeats!}</td>

        <td><#list au.vipSeats as vip>
                   ${vip!}"
                   </#list></td>

      </tr>
    </#list>
  </table>
 <br/>
     <form method="get" action="/auditoriums/getbyname" >
      <fieldset>
                     <legend>get by name</legend>
          <label><input type="text" name="name"></label>
          <br>
          <input type="submit" value="get by event name"><br>
       </fieldset>
      </form>
<br/>
<br/>
 <form action="/welcome">
        <input type="submit" value="main page" />
  </form>
  <br/>

</body>
</html>