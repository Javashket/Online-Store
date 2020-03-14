function add () {
    $(document).ready(function () {
        $('.add_product').click(function () {
            $.ajax({
                beforeSend: alert("product_add/"+$('.category_check[name="gender"]').val()+"_"+
                    $('.category_check[name="type"]').val() + "_"+
                    $('.category_check[name="season"]').val()),
                method: "GET",
                url: "product_add/"+$('.category_check[name="gender"]').val()+"_"+
                    $('.category_check[name="type"]').val() + "_"+
                    $('.category_check[name="season"]').val(),
                data: {

                }

            })
        })
    });
}