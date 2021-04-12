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
                var cachedCityOptions = null;
                $('#EstudianteTableContainer').jtable({
                    title : 'Registro de Estudiantes',
                    animationsEnabled : true,
                    paging: true, //Enable paging
                    columnResizable: true,
                    pageSize: 10, //Set page size (default: 10)
                    actions : {
                        listAction: 'FrontController?service=DNEstudiantes&action=list',
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
                                    url: 'FrontController?service=DNEstudiantes&action=add&data='+ JSON.stringify(JSON.parse(_string)),
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
                                    url: 'FrontController?service=DNEstudiantes&action=update&data='+ JSON.stringify(JSON.parse(_string)),
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
                        numestudiante : {
                            title : 'Id',
                            width : '3%',
                            key : true,
                            list : true,
                            create : false,
                            input: function (data){
                                if (data.value){
                                    return '<input type="text" readonly class="form-control input-sm" name="numestudiante" value="' + data.value + '"/>';
                                }
                            }
                        },
                        strcodigo : {
                            title : 'Código',
                            width : '5%',
                            key : true,
                            list : true,
                            create : false,
                            input: function (data){
                                if (data.value){
                                    return '<input type="text" readonly class="form-control input-sm" name="strcodigo" value="' + data.value + '"/>';
                                }
                            }
                        },
                        strtipoidentificacion : {
                            title : 'Tipo Identificación',
                            width : '10%',
                            list : false,
                            create : true,
                            edit: true,
                            inputClass: 'btn btn-default dropdown-toggle',
                            options: { 'CC': 'CC', 'NIT': 'NIT', 'TI': 'T.IDENT', 'PASS':'PASAPORTE', 'CE':'CE'}
                        },
                        stridentificacion : {
                            title : 'Identificación',
                            width : '10%',
                            list : true,
                            create : true,
                            edit: true,
                            input: function (data) {
                                if (data.record) {
                                    return '<input type="text" class="form-control input-sm" name="stridentificacion" style="width: 100%;" size="10" maxlength="15" value="' + data.value + '" />';
                                } else {
                                    return '<input type="text" class="form-control input-sm" name="stridentificacion" style="width: 100%;" size="10" maxlength="15"/>';
                                }
                            }
                        },
                        strnombres : {
                            title : 'Nombres y Apellidos',
                            width : '25%',
                            input: function (data) {
                                if (data.record) {
                                    return '<input type="text" class="form-control input-sm" name="strnombres" style="width: 100%;" size="10" maxlength="100" value="' + data.value + '" />';
                                } else {
                                    return '<input type="text" class="form-control input-sm" name="strnombres" style="width: 100%;" size="10" maxlength="100"/>';
                                }
                            }
                        },
                        dtmfechanacimiento : {
                            title : 'Fecha Nac.',
                            width : '10%',
                            list : true,
                            create : true,
                            edit: true,
                            type: 'date',
                            display: function (data) {
                                if (data.record) {
                                    var format = d3.time.format("%Y-%m-%d");
                                    data.record.dtmfechanacimiento = format(new Date(data.record.dtmfechanacimiento));      
                                    return data.record.dtmfechanacimiento;
                                }
                            } 
                        },
                        strsexo : {
                            title : 'Sexo',
                            list: true,
                            create: true,
                            edit: true,
                            type: 'radiobutton',
                            inputClass: 'btn btn-default dropdown-toggle validate[required]',
                            options: { 'M': 'M', 'F': 'F'}
                        },
                        strdireccion : {
                            title : 'Dirección',
                            width : '20%',
                            input: function (data) {
                                if (data.record) {
                                    return '<input type="text" class="form-control input-sm" name="strdireccion" style="width: 100%;" size="10" maxlength="100" value="' + data.value + '" />';
                                } else {
                                    return '<input type="text" class="form-control input-sm" name="strdireccion" style="width: 100%;" size="10" maxlength="100"/>';
                                }
                            }
                        },
                        strtelefono : {
                            title : 'Teléfono',
                            width : '10%',
                            input: function (data) {
                                if (data.record) {
                                    return '<input type="text" class="form-control input-sm" name="strtelefono" style="width: 100%;" size="10" maxlength="15" value="' + data.value + '" />';
                                } else {
                                    return '<input type="text" class="form-control input-sm" name="strtelefono" style="width: 100%;" size="10" maxlength="15"/>';
                                }
                            }
                        },
                        strciudadresidencia : {
                            title : 'Ciudad',
                            width : '10%',
                            inputClass: 'btn btn-default dropdown-toggle',
                            options: function () {
                         
                                if (cachedCityOptions) { //Check for cache
                                    return cachedCityOptions;
                                }
 
                                var options = [];
 
                                $.ajax({ //Not found in cache, get from server
                                    url: 'FrontController?service=DNDocentes&action=consultaCiudad&data=1',
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
                         
                                return cachedCityOptions = options; //Cache results and return options
                            }
                        },
                        strmail : {
                            title : 'E-mail',
                            width : '12%',
                            input: function (data) {
                                if (data.record) {
                                    return '<input type="email" class="form-control input-sm" name="strmail" style="width: 100%;" size="10" value="' + data.value + '" />';
                                } else {
                                    return '<input type="email" class="form-control input-sm" name="strmail" style="width: 100%;" size="10" placeholder ="prueba@example.com"/>';
                                }
                            }
                        }                                        
                    },
                    //Initialize validation logic when a form is created
                    formCreated: function (event, data) {
                        data.form.find('input[name="strnombres"]').addClass('validate[required,maxSize[100]]');
                        data.form.find('input[name="strdireccion"]').addClass('validate[required,maxSize[100]]');
                        data.form.find('input[name="strtipoidentificacion"]').addClass('validate[required]');
                        data.form.find('input[name="stridentificacion"]').addClass('validate[required,custom[number],maxSize[15]]');
                        data.form.find('input[name="dtmfechanacimiento"]').addClass('validate[required,past[now]]');                    
                        data.form.find('input[name="strtelefono"]').addClass('validate[required,custom[number],maxSize[15]]');
                        data.form.find('select[name="strciudadresidencia"]').addClass('validate[required]');
                        data.form.find('input[name="strmail"]').addClass('validate[required,custom[email]]');
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
                loadConsultaEstudiantes();            

                function loadConsultaEstudiantes(){
                    var filtros = "{a1:\"1\"}";
                    $('#EstudianteTableContainer').jtable('load', {service:"DNEstudiantes",
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
            <div id="EstudianteTableContainer"></div>
        </div>
    <jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>