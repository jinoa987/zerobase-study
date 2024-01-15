<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" href="./css/style.css">
  <meta charset="UTF-8">
</head>
<body>

<h1>와이파이 정보 구하기</h1>
<ul class="nav-list">
  <li><a href="/">홈</a></li>
  <li><a href="/history">위치 히스토리 목록</a></li>
  <li><a href="/load-wifi">Open API 와이파이 정보 가져오기</a></li>
</ul>

<form action="" method="get">
  <label>LAT:
    <input type="number" name="lat" step="0.0000001" placeholder="0.0" id="lat">
  </label>
  <label>, LNT:
    <input type="number" name="lnt" step="0.0000001" placeholder="0.0" id="lnt">
  </label>
  <button onclick="getMyPosition()" type="button">내 위치 가져오기</button>
  <button type="submit">근처 WIFI 정보 보기</button>
</form>

<table class="info-table">
  <tr>
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>
  <c:choose>
    <c:when test="${dtoList == null || dtoList.size() == 0}">
      <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach items="${dtoList}" var="dto">
        <tr>
          <td></td>
          <td>${dto.mgrNo}</td>
          <td>${dto.section}</td>
          <td><a href="#">${dto.wifiName}</a></td>
          <td>${dto.roadAddress}</td>
          <td>${dto.detailAddress}</td>
          <td>${dto.instlFloor}</td>
          <td>${dto.instlType}</td>
          <td>${dto.instlAgency}</td>
          <td>${dto.serviceType}</td>
          <td>${dto.netType}</td>
          <td>${dto.instlYear}</td>
          <td>${dto.inoutDoor}</td>
          <td>${dto.connEnv}</td>
          <td>${dto.lat}</td>
          <td>${dto.lnt}</td>
          <td>${dto.workDate}</td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</table>

<script src="/js/main.js"></script>
</body>
</html>