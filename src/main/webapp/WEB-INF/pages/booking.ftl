<!DOCTYPE html>
<html>
	<head>
		<title>Booking page</title>
	</head>
	<body>
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
                <td><form name="bookingForm" method="post" action="">
                                <input type="hidden" name="ticketId" value="${t.id}">
                                <input type="hidden" name="userId" value="1">
                                <input type="submit" value="book ticket"><br>
                            </form></td>
              </tr>
            </#list>
          </table>

<br/>
<br/>
</body>
</html>
