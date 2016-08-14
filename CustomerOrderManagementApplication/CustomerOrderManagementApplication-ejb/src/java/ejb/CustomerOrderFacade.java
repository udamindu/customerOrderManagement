/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Arunalu Hasakelum
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrderEntity> {
    @PersistenceContext(unitName = "CustomerOrderManagementApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderFacade() {
        super(CustomerOrderEntity.class);
    }
    
    public boolean hasOrders(String customerId){
        boolean status;
        Query query = em.createQuery("Select e " + "from CUSTOMERORDERENTITY e " + "where e.CUSTOMER_ID = :customerId");
        List<CustomerOrderEntity> customerOrderList = query.getResultList();
        if(customerOrderList!=null){
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }
    
}
