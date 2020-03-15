<#ftl output_format="HTML">
<#import "../parts/pager.ftl" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/customer/style.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Продавец</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="/customer">Online Clothing Store</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="btn btn-success ml-2 mr-2" href="/product_add">Добавить товар</a>
                </li>
            </ul>
            <a href="/logout"> <button type="button" class="btn btn-danger ml-2 mr-2">Выйти</button></a>
        </div>
    </nav>
</header>
<section class="customer_info">
    <div class="container">
        <p class="status">${login} : Продавец</p>
    </div>
</section>
<section class="main_content">
    <div class="container wrapper">
        <div class="row">
            <main class="col-lg-9 order-lg-2">
                <div class="row page-title">
                    <div class="col-md-8">
                        <h1 class="page-title__header">Товары:</h1>
                    </div>
                </div>
                <div class="row page-content content-grid">
                    <#if products??>
                    <@p.pager url products/>
                    <#list products.content as product>
                    <div class="col-md-3 content-item mt-2 mb-2">
                        <div class="card" style="width: 195px;">
                            <img class="card-img-top" src="img/photo.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title text-center"><a href="/product_seller${product.id}" class="item_title">${product.name}</a></h5>
                                <p class="card-text text-center">Осталось ${product.count} штук!</p>
                                <p class="card-text text-center">${product.price} Руб.</p>
                                <a href="/edit_product${product.id}"><button type="button" class="btn btn-primary" ><img src="img/edit.png" alt=""></button></a>
                                <button type="button" class="btn btn-primary" onclick="deleteProd(${product.id})"><img src="img/delete.png" alt=""></button>
                            </div>
                        </div>
                    </div>
                    <#else>
                    <div class="titles2">
                        <p class="reginfo"> В этой категории нет товаров</p>
                    </div>
                </div>
                </#list>
                <@p.pager url products/>
                </#if>
            </main>
            <#if notCategory??>
                <div class="titles">
                    <p class="reginfo"> Выберите категорию товара<br>
                        и нажмите «Подобрать» </p>
                </div>
            </#if>
            <aside class="col-lg-3 order-lg-1 sidebar">
                <div class="container-fluid sidebar-filter">
                    <a href="/search_for_seller" class="btn btn-primary ml-2 mr-2 mt-2 mb-2 search_button">Поиск</a>
                    <form action="/seller" method="get">
                        <fieldset class="form-group">
                            <legend>Пол</legend>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio1" value="мужской" name="gender" class="custom-control-input"
                                       <#if gender?? && gender == "мужской" >checked <#else > checked</#if>>
                                <label class="custom-control-label" for="customRadio1">Мужское</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio2" value="женский" name="gender" class="custom-control-input"
                                       <#if gender?? && gender == "женский" >checked </#if>>
                                <label class="custom-control-label" for="customRadio2">Женское</label>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <legend>Вид</legend>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio3" value="верхняя" name="type" class="custom-control-input"
                                       <#if type?? && type == "верхняя" >checked <#else > checked</#if>>
                                <label class="custom-control-label" for="customRadio3">Верхняя</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio4" value="нижняя" name="type" class="custom-control-input"
                                       <#if type?? && type == "нижняя" >checked </#if>>
                                <label class="custom-control-label" for="customRadio4">Нижняя</label>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <legend>Сезон</legend>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio5" name="season" value="лето" class="custom-control-input"
                                       <#if season?? && season == "лето" >checked <#else > checked</#if>>
                                <label class="custom-control-label" for="customRadio5">Лето</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio6" name="season" value="осень" class="custom-control-input"
                                        <#if season?? && season == "осень" > checked</#if>>
                                <label class="custom-control-label" for="customRadio6">Осень</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio7" name="season" value="зима" class="custom-control-input"
                                        <#if season?? && season == "зима" > checked</#if>>
                                <label class="custom-control-label" for="customRadio7">Зима</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio8" name="season" value="весна" class="custom-control-input"
                                        <#if season?? && season == "весна" > checked</#if>>
                                <label class="custom-control-label" for="customRadio8">Весна</label>
                            </div>
                        </fieldset>
                        <button class="btn btn-success btn-block mb-2 sidebar-filter__submit" type="submit">Подобрать!</button>
                    </form>
                </div>
            </aside>
        </div>
    </div>
</section>

<footer class="fixed-bottom page-footer bg-dark text-white text-center">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 page-footer__copyright">
                <span>© «Интернет-магазин одежды» , 2019.</span>
            </div>
        </div>
    </div>
</footer>
</body>
<script defer="defer">

    function deleteProd(id) {
        $(document).ready(function () {
            $.ajax({
                method: "POST",
                url: "/delete_product" + id,
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