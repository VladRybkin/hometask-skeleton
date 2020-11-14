<!DOCTYPE html>
<head>
    <title>tickets</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<h1>tickets page</h1>
<h2></h2>
<br/>
<br/>


<table>
    <tr>
        <th>id</th>
        <th>seat</th>
        <th>basePrice</th>
        <th>event</th>
        <th>event date</th>
        <th>user</th>
        <th>details</th>
        <th>remove ticket</th>
    </tr>
    <#list tickets as t>
      <tr>
          <td>${t.id}</td>
          <td>${t.seat!}</td>
          <td>${t.basePrice!}</td>
          <td><#if t.event??>${t.event.name}</#if></td>
          <td>${t.dateTime!}</td>
          <td><#if t.user??>
              <form action="/users/getbyemail">
                  <input type="hidden" name=email value="${t.user.email}"/>
                  <input type="submit" value="${t.user.email}"/>
              </form>
          <#else>
          available to purchase
          </#if></td>
          <td><a href="/tickets/getbyid/${t.id}">get details</a></td>
          <td>
              <form action="/tickets/remove/${t.id}">
                  <input type="submit" value="remove"/>
              </form>
          </td>
      </tr>
    </#list>
</table>
<br/>

<form method="post" action="/tickets/add" modelAttribute="ticket">
    <datalist id="options">
                <#list eventNames as en>
                    <option value="${en}"/>
                </#list>
    </datalist>
    <fieldset>
        <legend>add ticket</legend>
        <label><input list="options" name="eventName">choose event</label>
        <br>
        <label><input type="number" name="seat">seat</label>
        <br>
        <label><input type="number" name="basePrice">base price</label>
        <br>
        <input type="submit" value="add ticket"><br>
    </fieldset>
</form>
<br/>
<tr>
    <form method="GET" action="/tickets/pdf">
        <datalist id="options">
                <#list eventNames as en>
                    <option value="${en}"/>
                </#list>
        </datalist>
        <fieldset>
            <legend>print booked tickets</legend>
            <label><input type="datetime-local" name="ticketDate">date time</label>
            <br/>
            <label><input list="options" name="eventName">choose event</label>
            <br/>
            <input type="submit" value="print booked tickets "><br>
        </fieldset>
    </form>

    <br/>
    <form action="/welcome">
        <input type="submit" value="main page"/>
    </form>
    <br/>

</body>
</html>