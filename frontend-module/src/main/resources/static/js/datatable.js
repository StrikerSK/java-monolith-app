$(document).ready(function() {
    let table = $('#myTable').DataTable( {
        "columnDefs": [ {
            "searchable": false,
            "ordeable": false,
            "targets": 0
        },
        ]
    } );

    table.on( 'order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    // $('#myTable tbody').on('click','tr',function () {
    //     $(this).toggleClass('selected');
    // });
    //
    // $('#button').click(function () {
    //     alert(table.rows('.selected').data().length + ' row(s) selected');
    // })
} );