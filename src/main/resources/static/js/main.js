const inputField = document.querySelector('#input-search');


function showMessage(message) {

    Swal.fire({
        title: message,
        showCloseButton: true,
        showCancelButton: false,
        showConfirmButton: false

    })

}


function showMessageConfirm(message) {

    Swal.fire({
        title: message,
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Yes',
        denyButtonText: `No`

    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            Swal.fire('Saved!', '', 'success')

        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info')
        }
    })

}

function checkJarQuantity(sendButtonElement, jarType, waxQuantityElement) {

    $.ajax({
        url: "/api/check",
        type: "GET",
        data: {
            jarTpe: jarType,
            waxQuantity: waxQuantityElement.val()
        },
        success: function (xhr) {
            sendButtonElement.unbind('click');
            waxQuantityElement.removeClass('col-pink');
        },
        error: function (xhr) {
            waxQuantityElement.addClass('col-pink');

            if (xhr.status == '400') {
                showMessage('Error occurred, pleases contact system administrator!');
                return;
            }

            showMessage(xhr.responseText);

            $('#subAddProd').click(function (event) {
                showMessage('The needed wax quantity cannot be more than the selected jar capacity.');
                event.preventDefault();
            });

        }

    });

}

let allSpanElements = document.getElementsByTagName('p');
let spanElLength = allSpanElements.length;

if (spanElLength >= 1) {

    for (let i = 0; i < spanElLength; i++) {
        let currEl = allSpanElements[i];

        currEl.addEventListener('click', function () {
            let divEl = this.nextSibling.nextSibling;

            if (divEl.classList.contains('hidden')) {
                divEl.classList.remove('hidden');

            } else {
                divEl.classList.add('hidden');
            }

        });

    }

}


// if (document.querySelector('#form')) {
//     const form = document.querySelector('#form')
//
//     form.addEventListener('submit', event => {
//         event.preventDefault();
//
//         const searchingText = inputField.value.toLowerCase();
//
//         const pElements = document.querySelectorAll('.container .p-prod');
//
//         pElements.forEach(el => {
//             const parentElement = el.parentElement;
//
//             if (searchingText !== '') {
//                 const productName = el.textContent.toLowerCase();
//
//                 if (!productName.includes(searchingText)) {
//                     parentElement.classList.add('hidden');
//                 } else {
//                     parentElement.classList.remove('hidden');
//                 }
//
//             } else if (parentElement.classList.contains('hidden')) {
//                 parentElement.classList.remove('hidden');
//             }
//
//             console.log(el.textContent);
//
//         });
//
//     });
//
// }


inputField.addEventListener('input', e => {
    const inputValue = inputField.value.toLowerCase();

    const pElements = document.querySelectorAll('.container .p-prod');

    let sRegex = "";
    let sFilter = inputValue;
    let addLetter = '';

    if (inputValue === '') {
        sRegex = '';

    } else {
        let r = (inputValue !== '' ? inputValue.replace(/([.\[\]\(\)])/g, '\\$1') : '.');

        if (typeof r === 'string') {
            r = r.toLowerCase();
        }

        sRegex = new RegExp(r);
    }

    let isMatched = false;

    //When passing the value, convert name and address to lowerCase
    for (let i = 0; i < pElements.length; i++) {
        const currElement = pElements[i];
        const parentElement = currElement.parentElement;
        const productName = currElement.textContent.toLowerCase();

        if (sFilter === '' || sRegex.test(productName.toLowerCase())) {
            parentElement.classList.remove('hidden');
            isMatched = true;

        } else {
            parentElement.classList.add('hidden');
        }

    }

});

