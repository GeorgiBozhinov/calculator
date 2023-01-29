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

$("#waxQuantity").keyup(function () {
    let waxQuantity = $("#waxQuantity");

    if (waxQuantity.val() >= 1) {
        checkJarQuantity($('#submitCalc'), $('#candleJar').val() ,waxQuantity);

    }

});

// if(document.querySelector('#resBut')){
//     document.querySelector('#resBut').addEventListener('click', function (){
//         let allElements =  document.querySelector('#calc-form');
//
//         for(let i = 0; i < allElements.length; i++){
//             if(allElements[i].tagName === "INPUT"){
//                 allElements[i].value = '';
//                 console.log('input');
//             } else if (allElements[i].tagName === "SELECT"){
//                 allElements[i].selectedIndex = false;
//                 console.log('select');
//             }
//
//         }
//
//     });
// }