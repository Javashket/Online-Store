<#import "../parts/pager.ftl" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/customer/search.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Поиск</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="/customer">Online Clothing Store</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="btn btn-success ml-2 mr-2" href="/basket">Корзина</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-success ml-2 mr-2" href="/favorites">Избранное</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-success ml-2 mr-2" href="/order_hystory">История заказов</a>
                </li>
            </ul>
            <a href="/logout"> <button type="button" class="btn btn-danger ml-2 mr-2">Выйти</button></a>
        </div>
    </nav>
</header>

<section class="customer_info">
    <div class="container">
        <p class="status">${login} : Покупатель</p>
        <p class="balance">Баланс : ${balance} ₽</p>
    </div>
</section>

<section class="search_field">
    <div class="container">
    <form action="/search" method="get">
        <div class="input-group mb-3">
            <input type="search" class="form-control" name="text" maxlength="200" placeholder="Поиск...">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit" id="button-addon2">Найти!</button>
            </div>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="customRadioInline1" name="search" value="name" class="custom-control-input" checked>
            <label class="custom-control-label" for="customRadioInline1">Поиск по названию</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="customRadioInline2" name="search" value="desсription" class="custom-control-input">
            <label class="custom-control-label" for="customRadioInline2">Поиск по описанию</label>
        </div>
    </form>
    </div>
</section>

<section class="search_result">
    <div class="container wrapper">
        <div class="row">
            <main class="col-lg-12 order-lg-2">
                <div class="row page-title">
                    <div class="col-md-8">
                        <h1 class="page-title__header">Результаты поиска</h1>
                    </div>
                </div>
                <@p.pager url products/>
                <div class="row page-content content-grid">
                    <#if products??>
                    <#list products.content as product>
                    <div class="col-md-3 content-item">
                        <div class="card" style="width: 195px;">
                            <img class="card-img-top" src="img/photo.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title text-center"><a href="/product_customer${product.id}">${product.name}</a></h5>
                                <p class="card-text text-center">Осталось ${product.count} штук!</p>
                                <p class="card-text text-center">${product.price} Руб.</p>
                                <button type="button" class="btn btn-primary" onclick="addInBasket(${product.id})"><img src="img/cart.png" alt=""></button>
                                <button type="button" class="btn btn-primary" onclick="addInList(${product.id})"><img src="img/heart.png" alt=""></button>
                            </div>
                        </div>
                    </div>
                    <#else>
                        <div class="titles2">
                            <p class="reginfo"> Ничего не найдено</p>
                        </div>
                    </#list>
                    </#if>
                </div>
                <@p.pager url products/>
            </main>
        </div>
    </div>
</section>

<footer class="fixed-bottom bg-dark text-white ">
    <div class="container-fluid py-3">
        <h5 class="text-center">© «Интернет-магазин одежды» , 2019.</h5>
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