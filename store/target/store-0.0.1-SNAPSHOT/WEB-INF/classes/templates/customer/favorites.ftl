<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/customer/favorites.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Избранное</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/customer">Online Clothing Store</a>
        </div>
        <div class="customer_buttons">
            <a href="/basket" class="basket">Корзина</a>
            <a href="/order_hystory" class="history">История заказов</a>
            <a href="/logout"><input class="output_button" type="button" value="Выйти"></a>
        </div>
    </div>
</header>

<section class="customer_info">
    <div class="container">
        <p class="status">${login} : Покупатель</p>
        <p class="balance">Баланс : ${balance} ₽</p>
    </div>
</section>

<section class="purchase_history">
    <div class="container">
        <h1>Избранное</h1>
        <#if products??>
        <table>
            <tr><th>№</th><th>Название</th><th>Цена</th><th>Удалить</th></tr>
            <#list products as product>
            <tr><td>${product.id}</td><td>${product.name}</td>
                <td>${product.price}</td><td>
                        <button class="delete_button" onclick="deleteProd(${product.id})" ><img src="img/delete.png" alt=""></button>
                </td></tr>

            </#list>
        </table>
        <#else>
            <div class="titles">
                <p class="reginfo"> Товары в избранном отсутствуют.</p>
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
<script>
    function deleteProd(id) {
        $(document).ready(function () {
            $.ajax({
                method: "POST",
                url: "/delete_product_favorites" + id,
                data: {
                    name1: id
                },
                success: function (data) {
                    location.reload();
                }
            })
        })
    }
</script>
</html>