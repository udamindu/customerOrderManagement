/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$('[name="btn-edit-customer"]').click(function(){
   var id = $(this).data("id");
   var cname = $(this).data("cname");
   var caddress = $(this).data("caddress");
   var contactNo = $(this).data("contact-no");
   
   $('#cid').val(id);
   $('#cname').val(cname);
   $('#caddress').val(caddress);
   $('#contact-no').val(contactNo);
   hideFormAdd();
   $('#cid').prop("readonly", true);
   $('#myModal').modal('show');
});

$('[name="btn-delete-customer"]').click(function(){
    var r = confirm("Do you want to delete this customer? ");
    if (r == true) {
        var id = $(this).data("cid");
        $.post( "RemoveCustomer",{id:id}, function() {
            window.location.href='GetCustomers';
          });
    } else {
        
    }

});

