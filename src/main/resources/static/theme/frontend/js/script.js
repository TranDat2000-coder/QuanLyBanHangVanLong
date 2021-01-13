$(document).ready(function() {
    $('#search').keyup(function() {
        var key = $(this).val().toLowerCase();
        var productLit = $('.product');

        productLit.each(function() {
            var product = $(this);
            var productInfo = product.text().toLowerCase();

            if (productInfo.indexOf(key) == -1) {
                product.hide()
            } else {
                product.show();
            }
        });
    });
});