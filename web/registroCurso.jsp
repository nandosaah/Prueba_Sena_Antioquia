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
            $('#CursoTableContainer').jtable({
                title : 'Registro de Cursos',
                animationsEnabled : true,
                paging: true, //Enable paging
                columnResizable: true,
                pageSize: 10, //Set page size (default: 10)
                actions : {
                    listAction: 'FrontController?service=DNCursos&action=list',
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
                                url: 'FrontController?service=DNCursos&action=add&data='+ JSON.stringify(JSON.parse(_string)),
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
                                url: 'FrontController?service=DNCursos&action=update&data='+ JSON.stringify(JSON.parse(_string)),
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
                    numcurso : {
                        title : 'Id',
                        width : '3%',
                        key : true,
                        list : true,
                        create : false,
                        input: function (data){
                            if (data.value){
                                return '<input type="text" readonly class="form-control input-sm" name="numcurso" value="' + data.value + '"/>';
                            }
                        }
                    },
                    strnombre: {
                        title : 'Nombre',
                        width : '30%',
                        list : true,
                        create : true,
                        edit : true,
                        input: function (data) {
                            if (data.record) {
                                return '<input type="text" class="form-control input-sm" name="strnombre" style="width: 100%;" size="10" maxlength="100" value="' + data.value + '" />';
                            } else {
                                return '<input type="text" class="form-control input-sm" name="strnombre" style="width: 100%;" size="10" maxlength="100"/>';
                            }
                        }
                    },
                    strobservaciones : {
                        title : 'Observaciones',
                        width : '60%',
                        list : true,
                        create : true,
                        edit : true,
                        type: 'textarea',
                        input: function (data) {
                            if (data.record) {
                                return '<input type="text" class="form-control input-sm" name="strobservaciones" style="width: 100%;" size="10" maxlength="1000" value="' + data.value + '" />';
                            } else {
                                return '<input type="text" class="form-control input-sm" name="strobservaciones" style="width: 100%;" size="10" maxlength="1000"/>';
                            }
                        }
                    }                                        
                },
                //Initialize validation logic when a form is created
                formCreated: function (event, data) {
                    data.form.find('input[name="strnombre"]').addClass('validate[required,maxSize[35]]');
                    data.form.find('textarea[name="strobservaciones"]').addClass('validate[required,maxSize[1000]]');
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
            loadConsultaCursos();            

            function loadConsultaCursos(){
                var filtros = "{a1:\"1\"}";
                $('#CursoTableContainer').jtable('load', {service:"DNCursos",
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
        <div id="CursoTableContainer"></div>
    </div>
    <jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>