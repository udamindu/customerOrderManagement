<%-- 
    Document   : listCustomerOrders
    Created on : Aug 11, 2016, 9:22:26 PM
    Author     : Arunalu Hasakelum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Order List</title>
        
        <!--<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">-->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/customerApp.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Customer Order Management Console</a>
                </div>
            </div>
        </nav>
        
        <!-- breadcrumb -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                        <li>
                            <a class="btn btn-default btn-sm" href="/CustomerOrderManagementApplication-war"><span class="glyphicon glyphicon-home"></span> Home</a>
                        </li>
                        <li class="active">
                            <span class="glyphicon glyphicon-user"></span>View Customer Orders
                        </li>
                    </ol>
                </div>
            </div>
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Customer Order</button>
        </div>
        
        <!-- status message div -->
        <div class="container-fluid">
            <c:if test="${not empty messageSuccess}">
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${messageSuccess}
                </div>
                <c:remove var="messageSuccess" scope="session" /> 
            </c:if>
            <c:if test="${not empty messageFailure}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${messageFailure}
                </div>
                <c:remove var="messageFailure" scope="session" /> 
            </c:if>
        </div>
            
        <div class="container-fluid"> 
            <c:choose>
                <c:when test="${empty customerOrderList}">
                    <h4>Sorry, No Customer Order data recorded</h4>
                </c:when>
                <c:otherwise>   
                    <table class="table table-striped table-hover"> 
                        <thead>
                            <tr>
                                <th>Order No</th>
                                <th>Customer ID</th>
                                <th>Customer Name</th>
                                <th>Amount</th>
                                <th>Due Date</th>  
                                <th>Comments</th>  
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${customerOrderList}" var="element"> 
                                <tr>
                                    <td> ${element.orderId} </td>                        
                                    <td> ${element.customer.id} </td>  
                                    <td> ${element.customer.name}  </td>                    
                                    <td> ${element.amount}  </td>
                                    <td> ${element.dueDate}  </td> 
                                    <td> ${element.comment}  </td> 
                                    <td>
                                        <button name="editCustomerOrder" class="btn btn-default btn-sm"
                                                data-oid="${element.orderId}" data-ocid="${element.customer.id}"
                                                data-ocname="${element.customer.name}" data-oamount="${element.amount}"
                                                data-oduedate="${element.dueDate}" data-ocomment="${element.comment}">
                                            <span class="glyphicon glyphicon-edit"></span>View and Update
                                        </button>
                                    </td>         
                                </tr>   
                            </c:forEach>
                        </tbody>
                    </table>                   
                </c:otherwise>
            </c:choose>    
        </div>
        
        <!-- Modal for adding new Customer Orders -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Customer Order</h4>
                    </div>
                    <form id="customer-add-form" action="/CustomerOrderManagementApplication-war/AddCustomerOrder" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="orderId">Order No:</label>
                                <input type="text" class="form-control" name="orderId" autofocus required/>
                            </div>
                            <div class="form-group">
                                <label for="customerId">Customer Id:</label>
                                <select class="form-control" name="customerId" id="customerId" required>
                                    <c:forEach items="${customerList}" var="customerElement">
                                        <option>${customerElement.id}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="amount">Amount:</label>
                                <input type="number" class="form-control" name="amount" required/>
                            </div>
                            <div class="form-group">
                                <label for="dueDate">Due Date:</label>
                                <input type="date" class="form-control" name="dueDate" required/>
                            </div>
                            <div class="form-group">
                                <label for="comment">Comment:</label>
                                <textarea rows="4" cols="" class="form-control" name="comment" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
                    
            </div>
        </div>
        
        <!-- Modal for viewing/editing Customer Orders-->
        <div id="editCusOrderModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">View &amp; Edit Customers</h4>
                    </div>
                    <form id="customer-add-form" action="/CustomerOrderManagementApplication-war/UpdateCustomerOrder" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="orderId">Order No:</label>
                                <input type="text" class="form-control" id="orderId" name="orderId"/>
                            </div>
                            <div class="form-group">
                                <label for="customerIdd">Customer Id:</label>
                                <input type="text" class="form-control" id="customerIdd" name="customerIdd" required/>
                            </div>
                            <div class="form-group">
                                <label for="customerName">Customer Name:</label>
                                <input type="text" class="form-control" name="customerName" id="customerName" required/>
                            </div>
                            <div class="form-group">
                                <label for="orderAmount">Order Amount:</label>
                                <input type="number" class="form-control" id="orderAmount" name="orderAmount" required/>
                            </div>
                            <div class="form-group">
                                <label for="orderDueDate">Due Date:</label>
                                <input type="date" class="form-control" name="orderDueDate" id="orderDueDate" required/>
                            </div>
                            <div class="form-group">
                                <label for="orderComment">Comment:</label>
                                <textarea rows="4" cols="" class="form-control" name="orderComment" id="orderComment" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Edit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
                    
            </div>
        </div>
        <!--<script  type="text/javascript" src="JS/bootstrap.min.js"></script>-->
    </body>
</html>
