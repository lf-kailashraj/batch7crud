<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 2/2/16
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="left">
    <ul>
        <li><a href="employees">Employee List</a></li>
        <li>
            <form id="logout" action="authenticate/logout" method="post">
                <input type="hidden" name="name" value="value"/>
                <a onclick="document.getElementById('logout').submit();">Logout</a>
            </form>
        </li>
    </ul>
</div>