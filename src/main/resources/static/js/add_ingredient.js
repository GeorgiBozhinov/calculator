if (document.querySelector('#ingredientType')) {
    let ingredientField = document.querySelector('#ingredientType');

    ingredientField.addEventListener('change', function () {

        let selectedOption = this.selectedOptions[0].value;
        let units = document.querySelector('#unitName');
        let unitOptions = units.options;

        //You can be in general if has selected value and if it is TRUE to loop
        if (unitOptions.selectedIndex >= 1) {
            for (let k = 0; k < unitOptions.length; k++) {
                let currOp = unitOptions[k];

                if (currOp.selected) {
                    currOp.selected = false;

                } else {

                    if (currOp.classList.contains('hidden')) {
                        currOp.classList.remove('hidden');
                    }

                }

            }

        }

        let sizeField = document.querySelector('#size');
        let prevEl = sizeField.previousSibling.parentElement;

        if (['scent', 'jar', 'wax'].includes(selectedOption)) {
            prevEl.classList.add('hidden');

        } else {

            if (prevEl.classList.contains('hidden')) {
                prevEl.classList.remove('hidden');
            }

        }

        if (['scent', 'jar'].includes(selectedOption)) {

            for (let i = 1; i < unitOptions.length; i++) {
                let currOption = unitOptions[i];
                let currOpValue = currOption.value;

                if (currOption.value === 'мл') {
                    currOption.selected = true;

                } else {

                    if (!currOption.classList.contains('hidden')) {
                        currOption.classList.add('hidden');
                    }

                }

            }

        } else if (selectedOption === 'wax') {
            for (let n = 1; n < unitOptions.length; n++) {
                let currOption = unitOptions[n];

                if (!['гр', 'кг'].includes(currOption.value)) {
                    currOption.classList.add('hidden');
                }

            }

        } else if (selectedOption === 'wick') {
            for (let p = 1; p < unitOptions.length; p++) {
                let currOption = unitOptions[p];

                if (currOption.value === 'см') {
                    currOption.selected = true;

                } else {

                    if (!currOption.classList.contains('hidden')) {
                        currOption.classList.add('hidden');
                    }

                }

            }

        }

    });

}
