//If providedByCustomer checkbox is selected, hide candleJar it is not hidden - use toggle principle
if (document.querySelector('#jarOwner')) {
    let jarOwnerElement = document.querySelector('#jarOwner');

    jarOwnerElement.addEventListener('change', function () {
        let curr = this;
        let candleJarElement = document.querySelector('#candleJar');

        if (curr.options[curr.options.selectedIndex].value === 'seller') {
            candleJarElement.parentElement.classList.remove('hidden');
            candleJarElement.setAttribute('required', '');

        } else {
            candleJarElement.parentElement.classList.add('hidden');
            candleJarElement.options.selectedIndex = 0;
            candleJarElement.removeAttribute('required');

        }

    });

}


// $( '#otherIng' ).select2( {
//     theme: "bootstrap-5",
//     width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
//     placeholder: $( this ).data( 'placeholder' ),
//     closeOnSelect: false,
// } );