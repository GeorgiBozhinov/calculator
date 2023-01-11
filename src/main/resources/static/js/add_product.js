$("#waxQuantity").keyup(function () {
    let waxQuantity = $("#waxQuantity");

    if (waxQuantity.val() >= 1) {
        $.ajax({
            url: "/api/check",
            type: "GET", //send it through get method
            data: {
                jarTpe: $('#candleJar').val(),
                waxQuantity: waxQuantity.val()
            },
            success: function (xhr) {
                $('#subAddProd').unbind('click');
                waxQuantity.removeClass('col-pink');
            },
            error: function (xhr) {
                if (xhr.status == '400') {
                    showMessage('Error occurred, pleases contact system administrator!');

                    return;
                }

                showMessage(xhr.responseText);

                $('#subAddProd').click(function (event) {
                    showMessage('The needed wax quantity cannot be more than the selected jar capacity.');
                    event.preventDefault();

                    // if (!waxQuantity.hasClass('col-pink')) {
                    //     waxQuantity.addClass('col-pink');
                    // }

                });

            }

        });

    }

});


$( '#otherIng' ).select2( {
    theme: "bootstrap-5",
    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
    placeholder: $( this ).data( 'placeholder' ),
    closeOnSelect: false,
} );

