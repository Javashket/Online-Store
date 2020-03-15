<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/customer/order_hystory.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>История заказов</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/customer">Online Clothing Store</a>
        </div>
        <div class="customer_buttons">
            <a href="/basket" class="basket">Корзина</a>
            <a href="/favorites" class="favorite">Избранное</a>
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
        <h1>История заказов</h1>
        <#if orderlists??>
        <table>
            <tr><th>№ Заказа</th><th>Содержание</th><th>Стоимость</th></tr>
            <#list orderlists as orderlist>
            <tr><td>${orderlist.id}</td><td>
                    <#list orderlist.products as product>
                    ${product.productName}*${product.count} шт,
                    </#list>
                </td>
                <td>${orderlist.summ}</td>
            </tr>
            </#list>
        </table>
        <#else>
            <div class="titles">
                <p class="reginfo"> Заказы отсутствуют.</p>
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

