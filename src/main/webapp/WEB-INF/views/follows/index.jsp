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
<c:set var="commSerch" value="${ForwardConst.CMD_SERCH.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>フォロー一覧</h2>
        <c:if test="${login_employee != null}">
            <ul>
                <li><c:out value="${login_employee.name}" /></li>
            </ul>



            <c:choose>
                <c:when test="${follow != null}">
                    <table id="follow_list">
                        <tbody>
                            <tr>
                                <th class="follow_code">従業員番号</th>
                                <th class="follow_name">名前</th>
                                <th class="follow_action">操作</th>
                            </tr>
                            <c:forEach var="follows" items="${follow}" varStatus="status">
                                 <tr>
                                      <td><c:out value="${follows.followed_employee.code}" /></td>
                                      <td><c:out value="${follows.followed_employee.name}" /></td>
                                      <td>
                                          <form method="POST" action="<c:url value='?action=${action}&command=${commShow}' />">
                                              <button type="submit" onclick=”clickDisplayAlert()”>日誌一覧</button>
                                          </form>
                                      </td>
                                 </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>フォローしている人はいません。</p>
                </c:otherwise>
            </c:choose>
        </c:if>

        <p><a href="<c:url value='?action=${action}&command=${commSerch}' />">従業員検索</a></p>


    </c:param>
</c:import>