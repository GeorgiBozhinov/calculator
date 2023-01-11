let allEditButtons = $('.edit');
let allEDeleteButtons = $('.delete');

if (allEditButtons.length > 1) {
    allEditButtons.each(function (element) {
        let curr = $(this);

        //debugger;
        curr.on('click', function () {
            // debugger;
            let parParEl = curr.parent().parent();
            let parentChildren = parParEl[0].children;
            let counter = 1;

            for (const child of parentChildren) {
                if (counter > 5) {
                    let editBut = $('#' + curr[0].id + '.edit');
                    let sendBut = $('#' + curr[0].id + '.send');
                    let closeBut = $('#' + curr[0].id + '.close');

                    editBut.addClass('hidden');
                    //editBut.parent().addClass('hidden');
                    sendBut.removeClass('hidden');
                    closeBut.removeClass('hidden');

                    //debugger;

                    closeBut.on('click', function () {
                        let parElement = closeBut.parent().parent();
                        let count = 1;

                        for (const child of parElement[0].children) {
                            if (count > 5) {
                                closeBut.addClass('hidden');
                                sendBut.addClass('hidden');
                                editBut.removeClass('hidden');
                                //editBut.parent().removeClass('hidden');

                                return;
                            }

                           // let inputElValue = child.querySelector('input').value;
                            //let inputElValueT = child.querySelector('input');
                            let inputElValue = child.querySelector('input').defaultValue;

                            child.querySelector('div').remove();
                            child.innerHTML = inputElValue;

                            count++;

                            //debugger;
                        }

                    });

                    sendBut.on('click', function (el) {
                        let parElement = sendBut.parent().parent();

                        const rowData = {
                            ingredientName: parElement[0].children[0].querySelector('input').value,
                            quantity: parElement[0].children[1].querySelector('input').value,
                            size: parElement[0].children[2].querySelector('input').value,
                            unitName: parElement[0].children[3].querySelector('input').value,
                            price: parElement[0].children[4].querySelector('input').value
                        };

                        $.ajax({
                            type: "PUT",
                            url: "/api/update/" + this.id,
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify(rowData),
                            dataType: 'text',
                            success: function (xhr) {
                                showMessage(xhr);
                                closeBut.click();

                            },
                            error: function (xhr) {
                                showMessage(xhr);
                            }

                        });

                    });

                    //debugger;
                    return;
                }

                let elTextContent = child.textContent;
                child.innerHTML = '';

                changeToInputFields(child, elTextContent);
                counter++;

            }

            //debugger;
        });

    });

}


if (allEDeleteButtons.length > 1) {

    allEDeleteButtons.each(function () {
        let currDel = $(this);

        currDel.on('click', function () {
            let text = this.parentElement.parentElement.children[0].textContent;

            Swal.fire({
                title: 'Сигурни ли сте, че искате да изтриете: ' + text,
                showCloseButton: false,
                showCancelButton: true,
                confirmButtonText: 'Yes',
                denyButtonText: `No`

            }).then((result) => {
                if (result.isConfirmed) {

                    $.ajax({
                        type: "DELETE",
                        url: "/api/delete/" + this.id,
                        contentType: "application/json; charset=utf-8",
                        dataType: 'text',
                        success: function (xhr) {
                            Swal.fire({
                                title: xhr,
                                showCloseButton: true,
                                showCancelButton: false,
                                showConfirmButton: false

                            }).then((result) => {
                                if (result.isDismissed) {
                                    document.querySelector('a[href="/ingredient/all"]').click();
                                }
                            });
                        },
                        error: function (xhr) {
                            showMessage(xhr);
                        }

                    });

                }

            });

        })

    })

}


function changeToInputFields(tdElement, elTextContent) {
    let divEl = document.createElement('div');
    let inputEl = document.createElement('input');

    inputEl.defaultValue = elTextContent;
    inputEl.value = elTextContent;

    divEl.appendChild(inputEl);
    tdElement.appendChild(divEl);

}


function ajaxCall(url, method, contType, data, dType) {

    $.ajax({
        type: method,
        url: url,
        contentType: contType,
        data: JSON.stringify(data),
        dataType: dType,
        success: function (xhr) {
            showMessage(xhr.responseText);
        },
        error: function (xhr) {
            showMessage(xhr.responseText);
        }

    });

    // $.ajax({
    //     type: "PUT",
    //     url: "/api/update/" + this.id,
    //     contentType: "application/json; charset=utf-8",
    //     data: JSON.stringify(rowData),
    //     dataType: 'text',
    //     success: function (xhr) {
    //         showMessage(xhr);
    //         closeBut.click();
    //
    //     },
    //     error: function (xhr) {
    //         showMessage(xhr);
    //     }
    // });

}

