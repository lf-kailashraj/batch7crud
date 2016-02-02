<%@tag description="Section template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="sectionTitle" required="true"%>
<%@attribute name="sectionBody" fragment="true" %>
<%@attribute name="sectionFooter" fragment="true" %>

<t:masterpage>
    <jsp:attribute name="section">
        <div class="section">
            <div class="section-header">
                ${sectionTitle}
            </div>

            <jsp:invoke fragment="sectionBody"/>

            <div class="section-footer">
                <jsp:invoke fragment="sectionFooter"/>
            </div>
        </div>
    </jsp:attribute>
</t:masterpage>