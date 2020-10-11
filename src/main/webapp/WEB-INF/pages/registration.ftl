<!DOCTYPE html>
<head>
    <title>auditoriums</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>










<br/>
<form method="post" action=""  modelAttribute="userForm">
    <fieldset>
        <legend>add user</legend>
        <label><input type="text" name="email" placeholder="Username" required>user email</label>
        <br>
        <label><input type="text" name="firstName">first name</label>
        <br>
        <label><input type="password" name="password" placeholder="Password" required>password</label>
        <br>
        <label><input type="date" name="dateOfTheBirth">date of birth</label>
        <br>
        <input type="submit" value="register"><br>
    </fieldset>
</form>

<br/>




</body>
</html>