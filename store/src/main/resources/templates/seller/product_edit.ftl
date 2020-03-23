<#ftl output_format="HTML">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/seller/product_add.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="shortcut icon" href="/img/shirt.png" type="image/png">
    <title>Редактирование товара</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="/seller">Online Clothing Store</a>
        </div>
        <div class="seller_buttons">
            <a href="/logout"><input class="output_button" type="button" value="Выйти"></a>
        </div>
    </div>
</header>

<section class="seller_info">
    <div class="container">
        <p class="status">${login} : Продавец</p>
    </div>
</section>

<section class="add_new_product">
    <div class="container">
        <h1>Редактирование товара</h1>
        <form action="/edit_product${code}" method="post">
            <p>1. Укажите название товара</p>
            <input type="text" name="name" class="name_field" value="${product.name}" required placeholder="Название" minlength="3" maxlength="20">
            <p>2. Укажите цену</p>
            <input type="number" name="price" class="product_price" value="${product.price}" required placeholder="Цена" min="0" max="999999999">
            <p>3. Количество товара на складе</p>
            <input type="number" name="count" class="product_quantity" value="${product.count}" required placeholder="Кол-во" min="0" max="999999999">
            <p>4. Добавьте описание</p>
            <textarea type="text" name="description" class="product_description"  cols="30" rows="50" maxlength="200" >${product.description}</textarea>
            <p>5. Укажите конкретную категорию</p>
            <div>
                <p class="subcategory">Пол:<input type="radio" value="мужской" name="gender"
                                                  <#if product.gender == "мужской" >checked </#if>>мужской
                    <input type="radio" value="женский" name="gender"
                           <#if product.gender == "женский" >checked </#if>>женский</p>
            </div>
            <div >
                <p class="subcategory">Вид:<input type="radio" value="верхняя" name="type"
                                                  <#if product.type == "верхняя" >checked </#if>>верхняя
                <input type="radio" value="нижняя" name="type"
                       <#if product.type == "нижняя" >checked </#if>>нижняя</p>
            </div>
            <div >
                <p class="subcategory">Сезон:
                <input type="radio" value="лето" name="season"
                       <#if product.season == "лето" >checked </#if>>лето
                <input type="radio" value="осень" name="season"
                       <#if product.season == "осень" >checked </#if>>осень
                <input type="radio" value="зима" name="season"
                       <#if product.season == "зима" >checked </#if>>зима
                <input type="radio" value="весна" name="season"
                       <#if product.season == "весна" >checked </#if>>весна</p>
            </div>
            <p>6. Подтвердите добавление</p>
            <input type="submit" class="submit_add" value="Добавить">
        </form>
    </div>
</section>

<footer>
    <div class="container">
        <p>© «Интернет-магазин одежды» , 2019.</p>
    </div>
</footer>
</body>
</html>