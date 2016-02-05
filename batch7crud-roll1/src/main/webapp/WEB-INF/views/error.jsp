<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>

    <jsp:attribute name="body">
        <div class="wrapper">
            <div class="error">
                <h2>Error!</h2>
                <p>${errorMessage}</p>
                <p>This is not the page you are looking for</p>
                <div class="form-group">
                    <a href="#" class="button button-primary">Please Go Back</a>
                </div>
            </div>
        </div>
    </jsp:attribute>

</t:genericPage>