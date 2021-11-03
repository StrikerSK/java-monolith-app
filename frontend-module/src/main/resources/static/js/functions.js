function validateValues() {
    let lname = document.getElementById("lname").value;
    let fname = document.getElementById("fname").value;
    let country = document.getElementById("country").value;

    if(lname !== "" && fname !== "" && country !== "Choose your country"){
        return confirm('Are you sure you want to register student ' + fname + ' ' + lname + '?');
    } else {
        if(country === "Choose your country"){
            alert("You must choose a country");
            return false;
        } else {
            alert("Check the fields!");
            return false;
        }
    }
}

function chooseProgrammingLanguage() {

    let hasArray = false;
    let previousResult = document.getElementById("radio-result");
    let newValue = document.querySelector('input[name="favoriteLanguage"]:checked').value;

    $('input:checkbox[name=knownLanguages]:checked').each(function(){
        if ($(this).val() === newValue){
            hasArray = true;
        }
    });

    if (previousResult.value === '' || newValue !== previousResult.value && !hasArray){
        changeCheckboxes(newValue, previousResult, hasArray, function () {
            previousResult.value = newValue;
        })
    } else {
        console.log("Main else called!");
        if (newValue !== previousResult.value || previousResult.value === ''){
            console.log("Main else if called!");
            // document.querySelector("input[name=knownLanguages][value='" + previousResult.value + "']").checked = false;
            getCheckboxByValue(previousResult.value).checked = false;
            // document.querySelector("input[name=knownLanguages][value='" + previousResult.value + "']").disabled = false;
        } else {
            console.log("Main else else called!");
            // document.querySelector("input[name=knownLanguages][value='" + newValue + "']").checked = true;
            getCheckboxByValue(newValue).checked = true;
            // document.querySelector("input[name=knownLanguages][value='" + newValue + "']").disabled = true;
        }
    }
}

function changeCheckboxes(newValue, oldValue, inArray, callback) {
    let isChecked = getCheckboxByValue(newValue).checked;

    if (isChecked || !inArray){
        // document.querySelector("input[name=knownLanguages][value='" + newValue + "']").checked = true;
        getCheckboxByValue(newValue).checked = true;
        // document.querySelector("input[name=knownLanguages][value='" + newValue + "']").disabled = true;
        // console.log("changeCheckboxes 1. if called!");
    }

    if (oldValue.value !== '' && oldValue.value !== newValue || inArray){

        // document.querySelector("input[name=knownLanguages][value='" + oldValue.value + "']").checked = false;
        getCheckboxByValue(oldValue.value).checked = false;
        // document.querySelector("input[name=knownLanguages][value='" + oldValue.value + "']").disabled = false;
        // console.log("changeCheckboxes 2. if called!");

    }

    callback();
}

function getCheckboxByValue(requiredValue) {
    return document.querySelector("input[name=knownLanguages][value='" + requiredValue + "']");
}
