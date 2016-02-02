<%@tag description="User profile template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="firstName" %>
<%@attribute name="lastName" %>
<%@attribute name="station" %>


<t:section sectionTitle="Employee Profile">
    <jsp:attribute name="sectionBody">
        <div class="profile">
            <ul>
                <li>${firstName} ${lastName}</li>
                <li>${station}</li>
            </ul>
        </div>
    </jsp:attribute>
</t:section>