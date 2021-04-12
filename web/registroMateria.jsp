<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="co.com.academica.utils.Mensajes.*"%>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema de Registro Académico - Cali</title>
    <jsp:include page="/template/styles.jsp"></jsp:include>
    <!-- Include one of jTable styles. -->
    <script type="text/javascript">
        $(document).ready(function() {
            var cachedDocenteOptions = null;
            var cachedCursoOptions = null;
            $('#MateriaTableContainer').jtable({ // permite abrir la tabla por el evento clik en el icono de la tabla para que se abra
                title : 'Registro de Materias de Estudiantes',
                animationsEnabled : true,
                paging: true, //Enable paging
                columnResizable: true,
                pageSize: 10, //Set page size (default: 10)
                openChildAsAccordion: true,
                //selecting: true, //Enable selecting
                //multiselect: false, //Allow multiple selecting
                //selectingCheckboxes: true, //Show checkboxes on first column
                //selectOnRowClick: false,
                actions : {
                    listAction: 'FrontController?service=DNMaterias&action=list',/* va a la clase Dnmateria y carga la la data*/
                    createAction:function (postData) {
                        var filtros = $('#jtable-create-form').serializeArray();
                        var _string = '{';
                        for(var ix in filtros) 
                        {
                            var row = filtros[ix];
                            _string += '"' + row.name + '":"' + row.value + '",';
                        }
                        var end =_string.length - 1;
                        _string = _string.substr(0, end);
                        _string += '}';
                        console.log("Loading from custom function...");
                        
                        return $.Deferred(function ($dfd) {
                            $.ajax({                             
                                type: 'POST',
                                dataType: 'json',
                                data: JSON.stringify(filtros),
                                url: 'FrontController?service=DNMaterias&action=add&data='+ JSON.stringify(JSON.parse(_string)),
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    },
                    updateAction: function (postData) {
                        var filtros = $('#jtable-edit-form').serializeArray();                        
                        var _string = '{';
                        for(var ix in filtros)
                        {
                            var row = filtros[ix];
                            _string += '"' + row.name + '":"' + row.value + '",';
                        }
                        var end =_string.length - 1;
                        _string = _string.substr(0, end);
                        _string += '}';
                        console.log("Loading from custom function...");
                        
                        return $.Deferred(function ($dfd) {
                            $.ajax({                             
                                type: 'POST',
                                dataType: 'json',
                                data: JSON.stringify(filtros),
                                url: 'FrontController?service=DNMaterias&action=update&data='+ JSON.stringify(JSON.parse(_string)),
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    }    
                },
                fields : {
                    //CHILD TABLE DEFINITION FOR "EXAMS"
                    numcalendario: {
                        title: 'Horario',
                        width: '3%',
                        sorting: false,
                        edit: false,
                        create: false,
                        display: function (data) {
                            //Create an image that will be used to open child table
                            var $img = $('<img class="child-opener-image" src="./img/list_metro.png" title="Resultado Horarios" />');
                            //Open child table when user clicks the image traduce: Abrir tabla secundaria cuando el usuario hace clic en la imagen

                            $img.click(function () {
                                $('#MateriaTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                    title: data.record.strnombre + ' - Resultado Horarios',
                                    actions: {
                                        listAction: 'FrontController?service=DNCalendarioMaterias&action=listaHorario&data='+data.record.nummateria,
                                        createAction:function (postData) {
                                            var filtros = $('#jtable-create-form').serializeArray();
                                            var _string = '{';
                                            for(var ix in filtros)
                                            {
                                                var row = filtros[ix];
                                                _string += '"' + row.name + '":"' + row.value + '",';
                                            }
                                            var end =_string.length - 1;
                                            _string = _string.substr(0, end);
                                            _string += '}';
                                            console.log("Loading from custom function...");
                        
                                            return $.Deferred(function ($dfd) {
                                                $.ajax({                             
                                                    type: 'POST',
                                                    dataType: 'json',
                                                    data: JSON.stringify(filtros),
                                                    url: 'FrontController?service=DNCalendarioMaterias&action=add&data='+ JSON.stringify(JSON.parse(_string)),
                                                    success: function (data) {
                                                        $dfd.resolve(data);
                                                    },
                                                    error: function () {
                                                        $dfd.reject();
                                                    }
                                                });
                                            });
                                        },
                                        updateAction: function (postData) {
                                            var filtros = $('#jtable-edit-form').serializeArray();                        
                                            var _string = '{';
                                            for(var ix in filtros)
                                            {
                                                var row = filtros[ix];
                                                _string += '"' + row.name + '":"' + row.value + '",';
                                            }
                                            var end =_string.length - 1;
                                            _string = _string.substr(0, end);
                                            _string += '}';
                                            console.log("Loading from custom function...");
                        
                                            return $.Deferred(function ($dfd) {
                                                $.ajax({                             
                                                    type: 'POST',
                                                    dataType: 'json',
                                                    data: JSON.stringify(filtros),
                                                    url: 'FrontController?service=DNCalendarioMaterias&action=update&data='+ JSON.stringify(JSON.parse(_string)),
                                                    success: function (data) {
                                                        $dfd.resolve(data);
                                                    },
                                                    error: function () {
                                                        $dfd.reject();
                                                    }
                                                });
                                            });
                                        }   
                                    },
                                    fields: {
                                        nummateria: {
                                            type: 'hidden',
                                            defaultValue: data.record.nummateria
                                        },
                                        numcalendario: {
                                            title: 'Id',
                                            width: '3%',
                                            key: true,
                                            create: false,
                                            edit: true,
                                            list: true,
                                            input: function (data){
                                                if (data.value){
                                                    return '<input type="text" readonly class="form-control input-sm" name="numcalendario" value="' + data.value + '"/>';
                                                }
                                            }
                                        },
                                        dtmhorainicial: {
                                            title: 'Hora Inicial',
                                            width: '10%',
                                            edit: true,
                                            list: true,
                                            create: true,
                                            input: function (data) {
                                                if (data.record) {
                                                    return '<input type="time" class="form-control input-sm" name="dtmhorainicial" style="width: 100%;" size="10" maxlength="10" value="' + data.value + '" />';
                                                } else {
                                                    return '<input type="time" class="form-control input-sm" name="dtmhorainicial" style="width: 100%;" size="10" maxlength="10"/>';
                                                }
                                            }
                                        },
                                        dtmhorafinal: {
                                            title: 'Hora Final',
                                            width: '10%',
                                            edit: true,
                                            list: true,
                                            create: true,
                                            input: function (data) {
                                                if (data.record) {
                                                    return '<input type="time" class="form-control input-sm" name="dtmhorafinal" style="width: 100%;" size="10" maxlength="10" value="' + data.value + '" />';
                                                } else {
                                                    return '<input type="time" class="form-control input-sm" name="dtmhorafinal" style="width: 100%;" size="10" maxlength="10"/>';
                                                }
                                            }
                                        },
                                        strdia: {
                                            title: 'Día',
                                            width: '10%',
                                            edit: true,
                                            list: true,
                                            create: true,
                                            inputClass: 'btn btn-default dropdown-toggle',
                                            options : {'LUNES':'LUNES','MARTES':'MARTES','MIERCOLES':'MIERCOLES',
                                                'JUEVES':'JUEVES', 'VIERNES':'VIERNES', 'SABADO':'SABADO', 'DOMINGO':'DOMINGO'}
                                        },
                                        strobservaciones: {
                                            title: 'Observaciones',
                                            width: '40%',
                                            edit: true,
                                            list: true,
                                            create: true,
                                            inputClass: 'form-control',
                                            type: 'textarea'
                                        }
                                    }
                                }, function (data) { //opened handler
                                    data.childTable.jtable('load');
                                });
                            });
                            //Return image to show on the person row
                            return $img;
                        }
                    },
                    nummateria : {
                        title : 'Id',
                        width : '3%',
                        key : true,
                        list : true,
                        edit: true,
                        input: function (data){
                            if (data.value){
                                return '<input type="text" readonly class="form-control input-sm" name="nummateria" value="' + data.value + '"/>';
                            }
                        }
                    },
                    strnombre : {
                        title : 'Nombre',
                        width : '30%',
                        input: function (data) {
                            if (data.record) {
                                return '<input type="text" class="form-control input-sm" name="strnombre" style="width: 100%;" size="25" maxlength="50" value="' + data.value + '" />';
                            } else {
                                return '<input type="text" class="form-control input-sm" name="strnombre" style="width: 100%;" size="25" maxlength="50"/>';
                            }
                        }
                    },
                    numdocente : {
                        title : 'Docente',
                        width : '20%',
                        inputClass: 'btn btn-default dropdown-toggle',
                        options: function () {
                         
                            if (cachedDocenteOptions) { //Check for cache
                                return cachedDocenteOptions;
                            }
 
                            var options = [];
 
                            $.ajax({ //Not found in cache, get from server
                                url: 'FrontController?service=DNDocentes&action=listDocentes&data=1', /*caga el nomb del docente en la grilla*/
                                type: 'POST',
                                dataType: 'json',
                                async: false,
                                success: function (data) {
                                    if (data.Result != 'OK') {
                                        alert(data.Message);
                                        return;
                                    }
                                    options = data.Options;
                                }
                            });
                         
                            return cachedDocenteOptions = options; //Cache results and return options
                        }
                    },
                    numcurso : {
                        title : 'Curso',
                        width : '20%',
                        inputClass: 'btn btn-default dropdown-toggle',
                        options: function () {
                         
                            if (cachedCursoOptions) { //Check for cache
                                return cachedCursoOptions;
                            }
 
                            var options = [];
 
                            $.ajax({ //Not found in cache, get from server
                                url:'FrontController?service=DNCursos&action=listCursos&data=1',
                                type: 'POST',
                                dataType: 'json',
                                async: false,
                                success: function (data) {
                                    if (data.Result != 'OK') {
                                        alert(data.Message);
                                        return;
                                    }
                                    options = data.Options;
                                }
                            });
                         
                            return cachedCursoOptions = options; //Cache results and return options
                        }
                    }
                },
                //Initialize validation logic when a form is created
                formCreated: function (event, data) {
                    data.form.find('input[name="strnombre"]').addClass('validate[required,maxSize[50]]');
                    data.form.find('select[name="numdocente"]').addClass('validate[required]');
                    data.form.find('select[name="numcurso"]').addClass('validate[required]');
                    data.form.find('input[name="dtmhorainicial"]').addClass('validate[required]');
                    data.form.find('input[name="strobservaciones"]').addClass('validate[required]');
                    data.form.validationEngine({promptPosition : "bottomLeft", scroll: false});
                },
                //Validate form when it is being submitted
                formSubmitting: function (event, data) {
                    return data.form.validationEngine('validate');
                },
                //Dispose validation logic when form is closed
                formClosed: function (event, data) {
                    data.form.validationEngine('hide');
                    data.form.validationEngine('detach');
                }    
            });//termina Tabla
            loadConsultaMaterias();
            function loadConsultaMaterias(){
                var filtros = "{a1:\"1\"}";
                $('#MateriaTableContainer').jtable('load', {service:"DNMaterias",
                    action:"list",
                    data: filtros});
            }
        });
    </script>

</head>
<body>
    <jsp:include page="/template/header.jsp"></jsp:include>
    <jsp:include page="/template/menu.jsp"></jsp:include>
    <div style="width: 100%; text-align: left;">
        <div id="MateriaTableContainer"></div>
    </div>
    <jsp:include page="/template/footer.jsp"></jsp:include>
</body>

</html>