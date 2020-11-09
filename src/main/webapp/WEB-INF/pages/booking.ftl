<!DOCTYPE html>
<html>
<head>
    <title>Booking page</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<h1>Booking page</h1>
<br/>

<table>
    <tr>
        <th>id</th>
        <th>seat</th>
        <th>basePrice</th>
        <th>event</th>
        <th>user</th>
        <th>book ticket</th>
    </tr>
            <#list tickets as t>
              <tr>
                  <td>${t.id}</td>
                  <td>${t.seat}</td>
                  <td>${t.basePrice}</td>
                  <td>${t.event.name}</td>
                  <td><#if t.user??>${t.user.email}</#if></td>
                  <td>
                      <form name="bookingForm" method="post" action="">
                          <input type="hidden" name="ticketId" value="${t.id}">
                          <input type="submit" value="book ticket"><br>
                      </form>
                  </td>
              </tr>
            </#list>
</table>

<br/>
<form action="/welcome">
    <input type="submit" value="main page"/>
</form>
<br/>
</body>
</html>
