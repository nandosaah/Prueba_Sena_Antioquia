<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="co.com.academica.utils.Mensajes.*"%>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
    String userRol = (String) session.getAttribute("userRol");
    if (userRol != null && !userRol.equalsIgnoreCase("ADMINISTRADOR")) {
        response.sendRedirect("home.jsp");
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
            
            $('#UserTableContainer').jtable({
                title : 'Lista de Usuarios',
                paging: true, //Enable paging
                pageSize: 10, //Set page size (default: 10)
                actions : {
                    listAction: 'FrontController?service=DNUsuarios&action=list',
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
                                url: 'FrontController?service=DNUsuarios&action=add&data='+ JSON.stringify(JSON.parse(_string)),
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
                                url: 'FrontController?service=DNUsuarios&action=update&data='+ JSON.stringify(JSON.parse(_string)),
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
                    numusuario : {
                        title : 'Id',
                        width : '10%',
                        key : true,
                        list : true,
                        create : false,
                        edit: true,
                        input: function (data){
                            if (data.value){
                                    return '<input type="text" readonly class="jtable-input-readonly" name="numusuario" value="' + data.value + '"/>';
                            }
                        }
                    },
                    strlogin : {
                        title : 'Login',
                        width : '30%',
                        edit : true
                    },
                    strnombrecompleto : {
                        title : 'Nombre',
                        width : '30%',
                        edit : true
                    },
                    strrol : {
                        title : 'Rol',
                        width : '30%',
                        edit : true,
                        options: { 'ADMINISTRADOR': 'Administrador', 'USUARIO': 'Usuario' }
                    },
                    strclave : {
                        title : 'Clave',
                        width : '30%',
                        list: false,
                        create: true,
                        edit: true,
                        type: 'password'
                    },
                    strestado : {
                        title : 'Estado',
                        width : '5%',
                        edit : true,
                        options: { 'A': 'Activo', 'I': 'Inactivo' }
                    },
                    strmail : {
                        title : 'Email',
                        width : '20%',
                        edit : true
                    }
            
                },
                //Initialize validation logic when a form is created
                formCreated: function (event, data) {
                    data.form.find('input[name="strlogin"]').addClass('validate[required]');
                    data.form.find('input[name="strnombrecompleto"]').addClass('validate[required]');
                    data.form.find('input[name="strclave"]').addClass('validate[required]');
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
            //$('#UserTableContainer').jtable('load');
            loadUsuarios();
        
            function loadUsuarios(){
                var filtros = "{a1:\"1\"}";
                $('#UserTableContainer').jtable('load', {service:"DNUsuarios",
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
        <div id="UserTableContainer"></div>
    </div>
    <jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>