<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/auth/registration.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Регистрация</title>
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
            <h1>Регистрация</h1>
        </div>
        <div class="form_container">
            <form action="/registration" method="post" name="input">
                    <div id="radio1" class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="customRadioInline1" name="role" value="CUSTOMER" class="custom-control-input" checked>
                        <label class="custom-control-label" for="customRadioInline1">Покупатель</label>
                    </div>
                    <div id="radio2" class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="customRadioInline2" name="role" value="SELLER" class="custom-control-input">
                        <label class="custom-control-label" for="customRadioInline2">Продавец</label>
                    </div>
                <input type="email" name="email" maxlength="30" required placeholder="Email" >
                <#if errorDublicateMail??>
                    <p class="error">Пользователь с таким email уже существует</p>
                </#if>
                <#if emailError??>
                <p class="error">${emailError}</p>
                </#if>
                <input type="text" name="login" minlength="6" maxlength="15" required placeholder="Логин">
                <#if loginError??>
                <p class="error">${loginError}</p>
                </#if>
                <input type="password" name="password" minlength="6" maxlength="15" required placeholder="Пароль">
                <#if passwordError??>
                <p class="error">${passwordError}</p>
                </#if>
                <input type="password" name="confirm_password" minlength="6" maxlength="15" required placeholder="Пароль еще раз">
                <#if errorEqualsPassword??>
                    <p class="error">Пароли не совпадают</p>
                </#if>
                <input type="submit" name="userinput" value="Регистрация">
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