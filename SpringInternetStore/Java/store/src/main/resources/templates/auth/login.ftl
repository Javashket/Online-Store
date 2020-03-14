<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Вход</title>
  <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="/css/auth/login.css">
</head>
<body>
  <header>
    <div class="container">
        <a  class="logo" href="/index">Online Clothing Store</a>
    </div>
  </header>
<section>
  <div class="container">
    <div class="titles">
      <h1>Вход</h1>
      <p class="reginfo">Еще не зарегистрированы? <br>
      Для регистрации перейдите на главную страницу <br>
      и нажмите кнопку "Регистрация"</p>
    </div>
    <div class="form_container">
    <form action="/login/process" method="post" name="input">
      <input type="text" name="email" required placeholder="Email или логин" >
      <input type="password" name="password" required placeholder="Пароль">
      <input type="submit" name="userinput" value="Войти">
    </form>
      <#if error??>
          <p class="eror">Неправильные email/логин или пароль</p>
      </#if>
      <#if successRegistration??>
        <p class="success">Письмо активации отправлено <br>
          на ваш почтовый ящик.</p>
      </#if>
      <#if success??>
        <p class="success">Аккаунт активирован</p>
      </#if>
      <#if editPassword??>
        <p class="success">Пароль успешно изменен</p>
      </#if>
      <#if errorCode??>
      <p class="eror">Код активации не найден</p>
      </#if>
    </div>
    <div class="recovery"><a href="/recovery">Забыли пароль?</a></div>
  </div>
</section>
  <footer>
    <div class="container">
      <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
  </footer>
</body>
</html>
