const getFaculties = () => {
    $.getJSON(`/university/${$('#university').val()}/faculties`, {
        ajax : 'true'
    }, (data) => {
        let selectOptions = '';
        for ( let i = 0; i < data.length; i++) {
            selectOptions += `<option value=\"${data[i].name}\">${data[i].name}</option>`
        }

        $('#faculty').html(selectOptions).removeAttr('disabled');
    });
}

 $('#university').change(getFaculties);

$(document).ready(() => {
    let schoolValue = $('#university').val();
    if (schoolValue) {
        getFaculties();
        $('#faculty').removeAttr('disabled');
    }
});

$(document).ready(function() {
    $('#typeOfStudy').change(
        function() {
            let html = '<option value="" hidden="hidden">Select year</option>';
            if ($(this).val() === 'Bachelor'){
                html += '<option value="1st">1st</option>';
                html += '<option value="2nd">2nd</option>';
                html += '<option value="3rd">3rd</option>';
                html += '<option value="Extended">Extended</option>';
            } else {
                html += '<option value="1st">1st</option>';
                html += '<option value="2nd">2nd</option>';
                html += '<option value="Extended">Extended</option>';
            }
            $('#grade').html(html).removeAttr('disabled');
        })
});