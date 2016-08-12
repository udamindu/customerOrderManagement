/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$('[name="editCustomer"]').click(function(){
   var id = $(this).data("cid");
   var cname = $(this).data("cname");
   var caddress = $(this).data("caddress");
   var contactNo = $(this).data("contactNo");
   
   $('#cid').val(id);
   $('#cname').val(cname);
   $('#caddress').val(caddress);
   $('#contactNo').val(contactNo);
   $('#cid').prop("readonly", true);
   $('#editModal').modal('show');
});

$('[name="deleteCustomer"]').click(function(){
        var id = $(this).data("cid");
        $.post( "RemoveCustomer",{id:id}, function() {
            window.location.href='ListCustomers';
          });
});

