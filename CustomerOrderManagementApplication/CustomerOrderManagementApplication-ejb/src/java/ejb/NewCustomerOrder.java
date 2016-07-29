/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Arunalu Hasakelum
 */
@MessageDriven(mappedName = "jms/NewCustomerOrder", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewCustomerOrder implements MessageListener {
    
    public NewCustomerOrder() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
}
