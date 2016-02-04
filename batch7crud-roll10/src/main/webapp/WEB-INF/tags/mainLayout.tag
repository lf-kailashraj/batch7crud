<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="mainContent" fragment="true" %>

<t:genericPage>

    <jsp:attribute name="body">
        <div class="wrapper">
            <div class="header">
                <div class="header-content">
                    <div class="company-name">
                        <h2>abcd</h2>
                    </div>

                    <div class="header-menu">
                        <ul>
                            <li><a href="/auth/logout">logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="main-body">
                <div class="sidebar">
                    <div class="navigation">
                        <ul>
                            <li><a href="home">Dashboard</a></li>
                            <li><a href="students/list">List Students</a></li>
                            <li><a href="students/create">add student</a></li>
                            <li><a href="students/createTest">add student test</a></li>
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
                    <p>2016 leapfrog technology</p>
                </div>
            </div>
        </div>
    </jsp:attribute>

</t:genericPage>