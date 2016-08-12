/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Arunalu Hasakelum
 */
@Stateless
public class CustomerFacade extends AbstractFacade<CustomerEntity> {
    @PersistenceContext(unitName = "CustomerOrderManagementApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(CustomerEntity.class);
    }
    
}
