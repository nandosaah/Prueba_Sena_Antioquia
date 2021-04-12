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
            $('#MatriculaTableContainer').jtable({
                title : 'Registro de Matricula',
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
                    listAction: 'FrontController?service=DNMatriculas&action=list',
                    createAction:function (postData) {
                        alert("entro al create");
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
                                url: 'FrontController?service=DNMatriculas&action=add&data='+ JSON.stringify(JSON.parse(_string)),
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
                                url: 'FrontController?service=DNMatriculas&action=update&data='+ JSON.stringify(JSON.parse(_string)),
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
                    numdocente: {
                        title: 'Materias',
                        width: '3%',
                        sorting: false,
                        edit: false,
                        create: false,
                        display: function (data) {
                            //Create an image that will be used to open child table
                            var $img = $('<img class="child-opener-image" src="./img/list_metro.png" title="Resultado Materias" />');
                            //Open child table when user clicks the image
                            $img.click(function () {
                                $('#MatriculaTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                    title: 'Resultado Materias',
                                    actions: {
                                        listAction: 'FrontController?service=DNMaterias&action=getMateriasByCursos&data='+data.record.numcurso
                                    },
                                    fields: {
                                        nummateria : {
                                            title : 'Id',
                                            width : '3%',
                                            key : true,
                                            list : true
                                        },
                                        strnombre : {
                                            title : 'Nombre',
                                            width : '30%'
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
                                    }
                                }, function (data) { //opened handler
                                    data.childTable.jtable('load');
                                });
                            });
                            //Return image to show on the person row
                            return $img;
                        }
                    },
                    nummatricula : {
                        title : 'Id',
                        width : '3%',
                        key : true,
                        list : true,
                        edit: true,
                        input: function (data){
                            if (data.value){
                                return '<input type="text" readonly class="form-control input-sm" name="nummatricula" value="' + data.value + '"/>';
                            }
                        }
                    },
                    numestudiante : {
                        title : 'Estudiante',
                        width : '30%',
                        inputClass: 'btn btn-default dropdown-toggle',
                        options: function () {
                         
                            if (cachedDocenteOptions) { //Check for cache
                                return cachedDocenteOptions;
                            }
 
                            var options = [];
 
                            $.ajax({ //Not found in cache, get from server
                                url: 'FrontController?service=DNEstudiantes&action=listEstudiantes&data=1',
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
                    },
                    strtipo : {
                        title : 'Tipo',
                        list: true,
                        create: true,
                        edit: true,
                        inputClass: 'btn btn-default dropdown-toggle validate[required]',
                        options: { 'A': 'ACADEMICA', 'F': 'FINANCIERA'}
                    }
                },
                //Initialize validation logic when a form is created
                formCreated: function (event, data) {
                    data.form.find('select[name="numestudiante"]').addClass('validate[required]');
                    data.form.find('select[name="numcurso"]').addClass('validate[required]');
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
            loadConsultaMatriculas();
            function loadConsultaMatriculas(){
                var filtros = "{a1:\"1\"}";
                $('#MatriculaTableContainer').jtable('load', {service:"DNMatricula",
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
        <div id="MatriculaTableContainer"></div>
    </div>
    <jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>