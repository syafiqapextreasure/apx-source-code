<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${displayStyle}">
		<td><span style="color: Red;">${gl27cate}</span></td>
		<td><span style="color: Red;">${gl27icat}</span></td>
		<td><span style="color: Red;">${gl27smd}</span></td>
		<td><span style="color: Red;">${gl27brnc}</span></td>
		<td><span style="color: Red; font-size: 20px; display: bold" id="ind">*</span></td>
	</c:when>
	<c:when test="${!displayStyle}">
		<td>${gl27cate}</td>
		<td>${gl27icat}</td>
		<td>${gl27smd}</td>
		<td>${gl27brnc}</td>
		<td></td>
	</c:when>
</c:choose>