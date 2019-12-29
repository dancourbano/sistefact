$.extends(true, $.fn.dataTable.defaults,{
	 columnDefs: [{orderable: !1, dom: "tf", targets: [-1, 0]}],
     info: !1,
     language: {paginate: {previous: "&laquo;", next: "&raquo;"}},
});
function dropDown(id) {
    var dropDownTable = '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-sm">'
        + '<button type="button" onclick="showDetail(' + id + ')" class="btn btn-default"><i class="icon icon-edit"></i></button>'
        + '<button type="button" onclick="deleteData(' + id + ')" class="btn btn-danger"><i class="icon icon-eraser"></i></button>'
        + '</div></div>';

    return dropDownTable;
}
function saveFunction(saveURL,arrayData,idElement) {
	var TypeJson;
	 console.log("idElement "+idElement);
    if (idElement!= "0") {
        var id = parseInt(idElement);
        TypeJson='put';
    } else{
        var id = 0;
        TypeJson='post';
    }	
    sUrl = saveURL + id;
     
    
    $.ajax({
        url: saveURL,
        type: TypeJson,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(arrayData),
        beforeSend: function () {
            //showLoadingPage();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //closeLoadingPage();
            swal(jqXHR.statusText);
        },
        success: function (response) {

            //closeLoadingPage();
            if (response.status == "error") {
                mensajeSalida = "";
                $.each(response.result,
                    function (idx, elem) {
                        mensajeSalida = elem.defaultMessage + "\n" + mensajeSalida;
                    });
                swal("Advertencia", mensajeSalida, "warning");

            } else {
                resetForm();
                swal("Éxito",
                    "Se Guardó con éxito");
                showList();
                $('#modal_default').modal('hide');

            }
        }
    });
}
function showListAllFunction(listURL,arrayDatatable,dtTable,functionArrayDatatable) {

            $.ajax({
                url: listURL,
                dataType: 'json',
                type: 'GET',
                before: function () {

                },
                success: function (data) {
                	dtTable.clear().draw();
                    $.each(data,
                        function (idx, elem) {
                    	dtTable.row.add(arrayDatatable(elem)).draw();
                        });

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Error", jqXHR.statusText);
                }
            });
        }

function showDetailFunction(getByURL,id, functionSetData) {

    url = getByURL + id;
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'GET',
        before: function () {

        },
        success: function (data) {
        	functionSetData(data);
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.statusText);
        }
    });
    $('#modal_default').modal();
}
function deleteElement(textElement,idCliente,urlElement,showListFunctions,resetFormFunction) {

    swal(
        {
            title: "Desea eliminar "+textElement,
            text: "Se borrarán los datos del "+textElement,
            type: "warning",
            showLoaderOnConfirm: true,
            showCancelButton: true,
            confirmButtonColor: "#37474F",
            confirmButtonText: "Eliminar",
            closeOnConfirm: false
        },
        function () {
            sUrl = urlElement+idCliente;

            $.ajax({
                url: sUrl,
                type: 'GET',

                beforeSend: function () {
                    //showLoadingPage();
                },
                error: function (jqXHR, textStatus,
                                 errorThrown) {
                    swal("Error", jqXHR.statusText);
                },
                success: function (response) {

                    if (response.status == "error") {
                        setTimeout(function () {
                            swal("Error", "No se ha podido Eliminar");
                        }, 2000);
                    } else {
                         
                        
                        setTimeout(
                            function () {
                                swal("Éxito", response.message);
                            }, 2000);
                        showListFunctions();
                        resetFormFunction();
                        $('#modal_default').modal('hide');
                    }
                }
            });
        });
}