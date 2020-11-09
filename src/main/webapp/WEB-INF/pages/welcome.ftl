<!DOCTYPE html>
<html>
<head>
    <title>Simple JSP Application</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<h1>Welcome!</h1>
<br/>
<br/>
<form action="/events">
    <input type="submit" value="events"/>
</form>
<br/>

<form action="/users">
    <input type="submit" value="users"/>
</form>
<br/>

<form action="/booking">
    <input type="submit" value="booking"/>
</form>
<br/>
<form action="/tickets">
    <input type="submit" value="tickets"/>
</form>
<br/>
<form action="/upload">
    <input type="submit" value="upload"/>
</form>
<br/>
<form action="/auditoriums">
    <input type="submit" value="auditoriums"/>
</form>
<br/>
<form action="/registration">
    <input type="submit" value="registration"/>
</form>
<br/>
<form action="/login">
    <input type="submit" value="login"/>
</form>
<br/>
<form action="/logout" method="POST">
    <button type="submit">Logout</button>
</form>
</body>
</html>
