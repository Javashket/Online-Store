<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/admin/order_list.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Список заказов</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/admin">Online Clothing Store</a>
        </div>
        <div class="admin_buttons">
            <a href="/user_list" class="users">Список пользователей</a>
            <a href="/logout"><input class="output_button" type="button" value="Выйти"></a>
        </div>
    </div>
</header>

<section class="admin_info">
    <div class="container">
        <p class="status">${login} : Администратор</p>
    </div>
</section>

<section class="order_list">
    <div class="container">
        <h1>Список заказов</h1>
        <#if orders??>
        <table>
            <tr><th>№</th><th>Логин</th><th>Содержание заказа</th><th>Сумма</th></tr>
            <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.user.login}</td>
                <td>
                    <#list order.products as product>
                        ${product.productName}*${product.count} шт,
                    </#list>
                </td>
                <td>${order.summ}</td>
            </tr>
            </#list>
        </table>
        <#else>
        <div class="titles">
            <p class="reginfo">Заказы отсутствуют</p>
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