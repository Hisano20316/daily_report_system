<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_FLW.getValue()}" />
<c:set var="action" value="${ForwardConst.ACT_FLW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commNumSerch" value="${ForwardConst.CMD_NUMSERCH.getValue()}" />
<c:set var="jspcheck" value="jspcheck" />
<c:set var="commFollow" value="${ForwardConst.CMD_FOLLOW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>従業員検索</h2>
        <br />
        <h3>従業員番号を入力してください</h3><br />

        <form method="POST" action="<c:url value='/?action=${action}&command=${commNumSerch}' />">
            <label for="${AttributeConst.EMP_CODE.getValue()}">社員番号</label>
            <input type="text" name="${AttributeConst.EMP_CODE.getValue()}" value="${code}"/>
            <button type="submit">検索</button>
        </form>




        <br /><br />



       <c:choose>
           <c:when test="${employee == null}">
                <p>データはありません</p>
                <p></p>
           </c:when>
           <c:otherwise>
           <table id="employee_list">
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td><c:out value="${employee.code}" /></td>
                    <td><c:out value="${employee.name}" /></td>
                    <td>

                        <form method="POST" action="<c:url value='?action=${action}&command=${commFollow}' />">
                            <button type="submit" onclick=”clickDisplayAlert()”>フォロー</button>
                        </form>
                        <script>
                        function clickDisplayAlert(){
                            if(confirm("フォローしますか？")){
                                document.forms[1].submit();
                            }
                        }


                        </script>
                    </td>
                </tr>
           </table>
            <br />
           </c:otherwise>
       </c:choose>








    </c:param>
</c:import>