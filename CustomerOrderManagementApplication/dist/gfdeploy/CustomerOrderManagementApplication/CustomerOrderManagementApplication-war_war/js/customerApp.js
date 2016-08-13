/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('[name="editCustomer"]').click(function(){
        var id = $(this).data("cid");
        var cname = $(this).data("cname");
        var caddress = $(this).data("caddress");
        var contactNo = $(this).data("ccontact");
   
        $('#mcid').val(id);
        $('#mcname').val(cname);
        $('#mcaddress').val(caddress);
        $('#mcontactno').val(contactNo);
        $('#mcid').prop("readonly", true);
        $('#editModal').modal('show');
    });
    
    $('[name="editCustomerOrder"]').click(function(){
        var orderid = $(this).data("oid");
        var customerid = $(this).data("ocid");
        var customername = $(this).data("ocname");
        var orderamount = $(this).data("oamount");
        var orderamount = $(this).data("oamount");
   
        $('#mcid').val(id);
        $('#mcname').val(cname);
        $('#mcaddress').val(caddress);
        $('#mcontactno').val(contactNo);
        $('#mcid').prop("readonly", true);
        $('#editModal').modal('show');
    });
});

