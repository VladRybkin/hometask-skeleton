
<!DOCTYPE html>
<head>
    <title>tickets</title>
</head>
<body>
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
        <td><#if t.user??>${t.user.email}</#if></td>
        <td><a href="/tickets/getbyid/${t.id}">get details</a></td>
        <td><form action="/tickets/remove/${t.id}">
             <input type="submit" value="remove" />
             </form></td>
      </tr>
    </#list>
  </table>
 <br/>

        <form method="post" action="/tickets/add"  modelAttribute="ticket">
            <fieldset>
                   <legend>add ticket</legend>
               <input list="options" name="eventName" id="eventName">
                    <datalist id="options">
                     <#list options as t>
                      <option value="${t}"/>
                       </#list>
                    </datalist>
            <br>
           <label><input type="number" name="seat"></label>
           <br>
           <label><input type="number" name="basePrice"></label>
            <br>
           <input type="submit" value="add ticket"><br>
           </fieldset>
       </form>
<br/>
    <tr>


</body>
</html>