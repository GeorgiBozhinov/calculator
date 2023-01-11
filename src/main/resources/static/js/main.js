function showMessage(message) {

    Swal.fire({
        title: message,
        showCloseButton: true,
        showCancelButton: false,
        showConfirmButton: false

    })


    //     .then((result) => {
    //     if (result.isDismissed) {
    //       funcToExec();
    //
    //     }
    //
    // });

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

