<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/auth/registration.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Восстановление пароля</title>
</head>
<body>
<header>
    <div class="container">
        <a  class="logo" href="/index">Online Clothing Store</a>
    </div>
</header>
<section>
    <div class="container">
        <div class="title">
            <h1>Восстановление доступа</h1>
            <p>Введите новый пароль</p>
        </div>
        <div class="form_container">
            <form action="/recovery_pass/${code}" method="post" name="input">
                <input type="password" name="password" minlength="6" maxlength="15" required placeholder="Пароль">
                <#if passwordError??>
                    <p class="error">${passwordError}</p>
                </#if>
                <input type="submit" name="userinput" value="Ок">
            </form>
        </div>
    </div>
</section>
<footer>
    <div class="container">
        <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>
</body>
</html>