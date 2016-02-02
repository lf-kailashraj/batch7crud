<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="mainContent" fragment="true" %>

<t:genericPage>

    <jsp:attribute name="body">
        <div class="wrapper">
             <div class="header">
            <div class="header-content">
                <div class="company-name">
                    <h2>Leapfrog Employee Management System</h2>
                </div>

                <div class="header-menu">
                    <ul>
                        <li><a href="authentication/logout" id="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>

            <div class="main-body">
                <div class="sidebar">
                    <div class="navigation">
                        <ul>
                            <li><a href="home">Dashboard</a></li>
                            <li><a href="employees/create">Add Employee</a></li>
                            <li><a href="employees">List Employee</a></li>
                        </ul>
                    </div>
                </div>

                <div class="content-wrapper">
                    <div class="main-content">
                        <jsp:invoke fragment="mainContent"/>
                    </div>
                </div>
            </div>

            <div class="footer">
                <div class="footer-content">
                    <p>2016 leapfrog technology | Designed by Kiran Pariyar</p>
                </div>
            </div>
        </div>
    </jsp:attribute>

</t:genericPage>