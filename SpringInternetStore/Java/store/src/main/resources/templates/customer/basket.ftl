<#ftl output_format="HTML">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/customer/basket.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Корзина</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/customer">Online Clothing Store</a>
        </div>
        <div class="customer_buttons">
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

<section class="cart">
    <div class="container">
        <h1>Корзина</h1>
        <#if products??>
        <table>
            <tr><th>№</th><th>Название</th><th>Цена</th><th>Кол-во</th><th>Стоимость</th><th>Удалить</th></tr>
            <#list products as product>
            <tr><td>${product.productId}</td><td>${product.productName}</td><td>${product.productPrice}</td>
                <td><div class='ctrl'>
                    <div class='ctrl__button ' onclick="dec(${product.id})" >&ndash;</div>
                    <div class='ctrl__counter'>
                        <input class='ctrl__counter-input' maxlength='5' type='text' value='1' readonly>
                        <div class='ctrl__counter-num' id="${product.id}">${product.count}</div>
                    </div>
                    <div class='ctrl__button ' onclick="inc(${product.id})" >+</div>
                </div></td>
                <td class="${product.id}">${product.productTotalPrice}</td><td>
                        <button class="delete_button" onclick="deleteProd(${product.id})" ><img src="img/delete.png" alt=""></button>
                </td>
            </tr>
            </#list>
        </table>
            <div >Общая сумма:
                <p class="total"> ${sum} </p>
                <form action="/pay" method="post">
                <button class="submit">Оформить</button>
                </form>
                <#if errors??>
                    <#list errors as error>
                    <p class="eror">${error}</p>
                    </#list>
                </#if>
                <#if succesPay??>
                        <p class="success">${succesPay}</p>
                </#if>
            </div>
        <#else>
            <div class="titles">
                <p class="reginfo"> Товары в корзине отсутствуют.</p>
            </div>
        </#if>
    </div>
</section>

<footer>
    <div class="container">
        <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>

<script defer="defer">

    function deleteProd(id) {
        $(document).ready(function () {
            $.ajax({
                method: "POST",
                url: "/delete_product_position_basket" + id,
                data: {
                    name1: id
                },
                success: function (data) {
                    document.location.href = "http://localhost:8080/basket";
                }
            })
        })
    }

    function inc(id) {
            $.ajax({
                method: "POST",
                url: "/inc_count_product"+id,
                data: {
                    name1: id
                },
                success: function (data) {
                    var response_obj = JSON.parse(data);
                    var str1 = "#"+id;
                    var str2 = "."+id;
                    $(str1).html(response_obj.count);
                    $(str2).html(response_obj.price);
                    $('.total').html(response_obj.sum);
                }
            })
    }

    function dec(id) {
            $.ajax({
                method: "POST",
                url: "/dec_count_product"+id,
                data: {
                    name1: id
                },
                success: function (data) {
                    var response_obj = JSON.parse(data);
                    var str1 = "#"+id;
                    var str2 = "."+id;
                    $(str1).html(response_obj.count);
                    $(str2).html(response_obj.price);
                    $('.total').html(response_obj.sum);
                }
            })
    }

    (function() {
        'use strict';

        function ctrls() {
            var _this = this;

            this.counter = 0;
            this.els = {
                decrement: document.querySelector('.ctrl__button--decrement'),
                counter: {
                    container: document.querySelector('.ctrl__counter'),
                    num: document.querySelector('.ctrl__counter-num'),
                    input: document.querySelector('.ctrl__counter-input')
                },
                increment: document.querySelector('.ctrl__button--increment')
            };

            this.decrement = function() {
                var counter = _this.getCounter();
                var nextCounter = (_this.counter > 0) ? --counter : counter;
                _this.setCounter(nextCounter);
            };

            this.increment = function() {
                var counter = _this.getCounter();
                var nextCounter = (counter < 9999999999) ? ++counter : counter;
                _this.setCounter(nextCounter);
            };

            this.getCounter = function() {
                return _this.counter;
            };

            this.setCounter = function(nextCounter) {
                _this.counter = nextCounter;
            };

            this.debounce = function(callback) {
                setTimeout(callback, 100);
            };

            this.render = function(hideClassName, visibleClassName) {
                _this.els.counter.num.classList.add(hideClassName);

                setTimeout(function() {
                    _this.els.counter.num.innerText = _this.getCounter();
                    _this.els.counter.input.value = _this.getCounter();
                    _this.els.counter.num.classList.add(visibleClassName);
                }, 100);

                setTimeout(function() {
                    _this.els.counter.num.classList.remove(hideClassName);
                    _this.els.counter.num.classList.remove(visibleClassName);
                }, 200);
            };

            this.ready = function() {
                _this.els.decrement.addEventListener('click', function() {
                    _this.debounce(function() {
                        _this.decrement();
                        _this.render('is-decrement-hide', 'is-decrement-visible');
                    });
                });

                _this.els.increment.addEventListener('click', function() {
                    _this.debounce(function() {
                        _this.increment();
                        _this.render('is-increment-hide', 'is-increment-visible');
                    });
                });

                _this.els.counter.input.addEventListener('input', function(e) {
                    var parseValue = parseInt(e.target.value);
                    if (!isNaN(parseValue) && parseValue >= 0) {
                        _this.setCounter(parseValue);
                        _this.render();
                    }
                });

                _this.els.counter.input.addEventListener('focus', function(e) {
                    _this.els.counter.container.classList.add('is-input');
                });

                _this.els.counter.input.addEventListener('blur', function(e) {
                    _this.els.counter.container.classList.remove('is-input');
                    _this.render();
                });
            };
        };

        // init
        var controls = new ctrls();
        document.addEventListener('DOMContentLoaded', controls.ready);
    })();
</script>
</body>
</html>