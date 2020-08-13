
<!DOCTYPE html>
<head>
    <title>users</title>
</head>
<body>
<h1>users page</h1>
<h2></h2>
<br/>
<br/>
<table>
    <tr>
      <th>id</th>
      <th>first name</th>
      <th>last name</th>
      <th>email</th>
      <th>date of birth</th>
      <th>remove</th>
    </tr>
    <#list users as u>
      <tr>
        <td>${u.id}</td>
        <td>${u.firstName!}</td>
        <td>${u.lastName!}</td>
        <td><a href="/users/getbyid/${u.id}">${u.email}</a></td>
        <td>${u.dateOfBirth!}</td>
        <td><form action="/users/remove/${u.id}">
             <input type="submit" value="remove" />
             </form></td>
      </tr>
    </#list>
  </table>
 <br/>
<hr/>
<br/>
        <form method="post" action="/users/add"  modelAttribute="user">
             <fieldset>
                 <legend>add user</legend>
           <label><input type="text" name="email">user email</label>
           <br>
           <label><input type="text" name="firstName">first name</label>
            <br>
             <label><input type="date" name="birthday">date of birth</label>
                        <br>
           <input type="submit" value="add user"><br>
           </fieldset>
       </form>



</body>
</html>