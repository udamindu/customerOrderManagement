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
        <script  type="text/javascript" src="JS/bootstrap.min.js"></script>
    </body>
</html>
