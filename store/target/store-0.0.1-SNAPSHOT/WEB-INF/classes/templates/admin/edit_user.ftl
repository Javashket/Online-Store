<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/admin/edit_user.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Редактирование пользователя</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/admin">Online Clothing Store</a>
        </div>
        <div class="admin_buttons">
            <a href="/user_list" class="users">Список пользователей</a>
            <a href="/order_list" class="orders">Список заказов</a>
            <a href="/logout"><input class="output_button" type="button" value="Выйти"></a>
        </div>
    </div>
</header>

<section class="admin_info">
    <div class="container">
        <p class="status">${login} : Администратор</p>
    </div>
</section>

<section>
    <div class="container">
        <div class="title">
            <h1>Редактирование ${role} </h1>

        </div>
        <div class="form_container">
            <form action="/edit_user${code}" method="post" name="input">
                <input type="text" name="login" minlength="6" maxlength="15" required placeholder="Логин">
                <input type="text" name="password" minlength="6" maxlength="15" required placeholder="Пароль">
                <#if role == "CUSTOMER">
                    <input type="number" name="balance" class="product_price" required placeholder="Баланс" max="999999999">
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