<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/admin/admin.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Администратор</title>
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

<div class="titles">
    <p class="admin_cursor">Для редактирования пользователей <br>
        перейдите в список пользователей</p>
    <p class="admin_cursor">
        Для просмотра продаж  <br>
        перейдите в список продаж
    </p>
</div>

<footer>
    <div class="container">
        <p> © «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>
</body>
</html>