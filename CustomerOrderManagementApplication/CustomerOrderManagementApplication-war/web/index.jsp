<%-- 
    Document   : index
    Created on : Jul 30, 2016, 1:13:08 AM
    Author     : Arunalu Hasakelum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Orders</title>
        
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Customer Order Management Console</a>
                </div>
            </div>
        </nav>
        
        <div class="container-fluid"> 
            <div class="row">

                <div class="col-md-6">
                    <div>
                        <h3>Customer Orders</h3>
                        <a href="/CustomerOrderManagementApplication-war/ListCustomerOrder" class="btn btn-primary btn-lg" >
                            View Customer Orders </a>
                    </div>
                </div>
                    
                <div class="col-md-6">  
                    <div>
                        <h3>Customers</h3>
                        <a href="/CustomerOrderManagementApplication-war/ListCustomers" class="btn btn-primary btn-lg" >View Customers </a>
                    </div>
                </div>
                    
            </div>
        </div>
        <script  type="text/javascript" src="JS/bootstrap.min.js"></script>
    </body>
</html>

