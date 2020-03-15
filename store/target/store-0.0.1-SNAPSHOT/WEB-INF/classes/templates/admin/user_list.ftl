<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/admin/user_list.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Список пользователей</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/admin">Online Clothing Store</a>
        </div>
        <div class="admin_buttons">
            <a href="/order_list" class="orders">Список заказов</a>
            <a href="/logout"><input class="output_button" type="button" value="Выйти"></a>
        </div>
    </div>
</header>

<section class="admin_info">
    <div class="container">
        <p class="status">${login}: Администратор</p>
    </div>
</section>

<section class="user_list">
    <div class="container">
        <h1>Список пользователей</h1>
        <div class="eror">
            <#if no_such_user??>
                пользователя с таким id не существует
            </#if>
        </div>
        <#if users??>
        <table>
            <tr><th>id</th><th>Логин</th><th>Пароль</th><th>Статус</th><th>Баланс</th><th>Изменить</th></tr>
            <#list users as user>
                <#if user.role != "ADMIN">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <#if user.role == "CUSTOMER">
                        ${user.balance}
                    <#else>
                        null
                    </#if>
                </td>
            <td>
                    <a href="/edit_user${user.id}">
                    <button class="edit_button"><img src="img/edit1.png" alt=""></button>
                    </a>
                <a href="/delete_user${user.id}">
                 <button class="edit_button"><img src="img/delete1.png" alt=""></button>
                    </a>
            </td>
            </tr>
                </#if>
            </#list>
        </table>
        <#else>
        <div class="titles">
            <p class="reginfo">Пользователи отсутствуют</p>
        </div>
        </#if>
    </div>
</section>

<footer>
    <div class="container">
        <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>

</body>
</html>