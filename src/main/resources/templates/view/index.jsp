<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" th:href="@{/css/index.css}">
  <link rel="icon" type="image/png" th:href="@{/icon/title.png}" />
  <title>Nối từ | Chơi!</title>
</head>

<body>
  <div class="bg"></div>
  <div class="bg bg2"></div>
  <div class="bg bg3"></div>
  <div class="content">
    <h1 th:text="${dictionary.text}"></h1>
    <div th:if="${!active}" class="alert alert-info" role="alert">
      Thua rồi, chơi lại nhé!
    </div>
    <div th:if="${complete}" class="alert alert-info" role="alert">
      Quá ghê gớm, bạn đã thắng!
    </div>
    <form th:action="@{/api/noi-tu}" method="post">
      <div class="input-group input-group-lg">
        <input th:disabled="${complete || !active}" th:value="${inp}" class="form-control" type="text" name="text"
          autofocus onfocus="var temp_value=this.value; this.value=''; this.value=temp_value" autocomplete="off"
          required>
      </div>
    </form>
    <a th:href="@{/api/noi-tu/reload-data}">Chơi lại</a>
  </div>
</body>

</html>