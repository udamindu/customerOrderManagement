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
        <title>Customer Order Management</title>
          
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <script  type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
        <script  type="text/javascript" src="js/bootstrap.min.js"></script>
        <!--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" style="color: #ff6600">Customer Order Management Console</a>
                </div>
            </div>
        </nav>
        
        <div class="container-fluid"> 
            <div class="row">
                <div class="col-lg-12">
                    <div class="page-header">
                        <h1>Customer Order Management Console</h1>
                    </div>
                </div>
            </div>
            
            <div class="row">
                
                <div class="col-md-6" style="border-color: #ff6600">  
                   <div class="panel panel-warning" style="background-color: white">
                       <div class="panel-heading"><h3>Customers</h3></div>
                        <div class="panel-body" style="margin-left: 30%">
                            <a href="/CustomerOrderManagementApplication-war/ListCustomers" class="btn btn-default btn-lg" style="border-color: #ff6600;color: #ff6600">
                                <span class="glyphicon glyphicon-user"></span>Customers Section
                            </a>
                        </div>    
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="panel panel-warning" style="background-color: white">
                        <div class="panel-heading"><h3>Customer Orders</h3></div>
                        <div class="panel-body" style="margin-left: 25%">
                            <a href="/CustomerOrderManagementApplication-war/ListCustomerOrder" class="btn btn-default btn-lg" style="border-color: #ff6600;color: #ff6600">
                                <span class="glyphicon glyphicon-list-alt"></span>Customer Orders Section
                            </a>
                        </div>
                    </div>
                </div>
                    
            </div>
            <div class="jumbotron" style="background-color: white">
                <p>
                    "Customer Order Management Console is an simple Enterprise Application developed to support, manage
                    Customer and Order relationships"
                </p>
            </div>
        </div>
        
        <nav class="navbar navbar-inverse" style="margin-bottom: 0%">
            <div class="container-fluid">
                <div class="navbar-footer">
                    <h4 style="margin-left: 35%;color: white">Enterprise Application Development 2016</h4>
                </div>
            </div>
        </nav>
    </body>
</html>

