<!DOCTYPE html>
<head>
    <title>events</title>
</head>
<body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<h1>upload page</h1>
<br/>
<form method="post" action="" enctype="multipart/form-data">
    <fieldset>
        <legend>upload events</legend>
        <label><input type="file" name="jsonFile"></label>
        <br>

        <input type="submit" value="upload"><br>
    </fieldset>
</form>

<br/>
<form action="/welcome">
    <input type="submit" value="main page"/>
</form>

</body>
</html>