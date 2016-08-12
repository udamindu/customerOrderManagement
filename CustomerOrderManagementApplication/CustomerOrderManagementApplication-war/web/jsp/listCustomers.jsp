<%-- 
    Document   : listCustomers
    Created on : Aug 11, 2016, 9:17:00 PM
    Author     : Arunalu Hasakelum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List</title>
        
        <!--<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css"> -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                        <li>
                            <a class="btn btn-default btn-sm" href="/CustomerOrderManagementApplication-war"><span class="glyphicon glyphicon-home"></span> Home</a>
                        </li>
                        <li class="active">
                            <span class="glyphicon glyphicon-user"></span>View Customers
                        </li>
                    </ol>
                </div>
            </div>
            
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Customer</button>
        </div>
        
        <div class="container-fluid">
            <c:if test="${not empty confirmation}">
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${confirmation}
                </div>
                <c:remove var="confirmation" scope="session" /> 
            </c:if>
        </div>
            
        <div class="container-fluid table-responsive">             
            <c:choose>
                <c:when test="${empty customerList}">
                    <h4>No Customer data recorded</h4>
                </c:when>
                <c:otherwise>   
                        <table class="table table-bordered"> 
                            <thead>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Name</th>
                                    <th>Address</th>
                                    <th>Contact No.</th>  
                                    <th>Actions </th>  
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${customerList}" var="element"> 
                                    <tr>
                                        <td> ${element.id} </td>                        
                                        <td> ${element.name} </td>  
                                        <td> ${element.address}  </td>                    
                                        <td> ${element.contactNumber}  </td>   
                                        <td>
                                            <button name="btn-edit-customer" class="btn btn-primary"
                                                    data-id="${element.id}" data-cname="${element.name}"
                                                    data-caddress="${element.address}" data-contact-no="${element.contactNumber}" >
                                                Update</button>
                                        </td>       
                                        <td>
                                            <button name="btn-delete-customer" data-cid="${element.id}" class="btn btn-info">Delete</button>
                                        </td>   
                                    </tr>   
                                </c:forEach>
                            </tbody>
                        </table>                   
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- Modal for adding new Customers -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
      
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Customers</h4>
                    </div>
                    <form id="customer-add-form" action="/CustomerOrderManagementApplication-war/AddCustomer" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="cname">Customer Name:</label>
                            <input type="text" class="form-control" name="cname" autofocus required/>
                        </div>
                        <div class="form-group">
                            <label for="caddress">Customer Address:</label>
                            <input type="text" class="form-control" name="caddress" required/>
                        </div>
                        <div class="form-group">
                            <label for="contactno">Contact Number:</label>
                            <input type="tel" pattern="^\d{3}-\d{7}$" class="form-control" placeholder="Contact Number (format: xxx-xxxxxxx)" name="contactno" required/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" id="btn-add-customer" class="btn btn-primary">Add</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                    </form>
                </div>
        
            </div>
        </div>
        <!--<script  type="text/javascript" src="js/bootstrap.min.js"></script>
        <script  type="text/javascript" src="js/jquery-1.11.0.min.js"></script>-->
    </body>
</html>
