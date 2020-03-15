<#ftl output_format="HTML">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/customer/product.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>${product.name}</title>
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

<section class="product_page">
    <div class="container">
        <div class="product_image">
            <img src="img/photo.png" alt="">
        </div>
        <div class="product_title">
            <h2 class="pr_title">Название товара: ${product.name}</h2>
        </div>
        <div class="product_availability">
            <p>Наличие : ${product.count} шт</p>
        </div>
        <div class="product_description">
            <p>Описание :${product.description}</p>
        </div>
        <div class="product_price">
            <p> Цена : ${product.price} Р</p>
        </div>
        <div class="product_actions">
            <button class="product_btn" onclick="addInBasket(${product.id})"> <img src="img/cart.png" alt=""></button>
            <button class="product_btn" onclick="addInList(${product.id})"> <img src="img/heart.png" alt=""></button>
        </div>
    </div>
</section>

<footer>
    <div class="container">
        <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>
</body>
<script defer="defer">
    function addInBasket(id) {
        $( document ).ready(function(){
            $.ajax({
                method: "POST",
                url: "/add_product_basket"+id,
                data: {
                    name1: id
                }
            })
        })
    }

    function addInList(id) {
        $(document).ready(function () {
            $.ajax({
                method: "POST",
                url: "/add_product_favorites" + id,
                data: {
                    name1: id
                }
            })
        })
    }
</script>
</html>