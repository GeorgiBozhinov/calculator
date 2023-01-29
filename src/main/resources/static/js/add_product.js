$("#waxQuantity").keyup(function () {
    let waxQuantity = $("#waxQuantity");

    if (waxQuantity.val() >= 1) {
        checkJarQuantity($('#subAddProd'), $('#candleJar').val() ,waxQuantity);

    }

});
