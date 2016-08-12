/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Arunalu Hasakelum
 */
@Entity
public class CustomerOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private String orderId;
    
    @NotNull
    private String dueDate;
    
    @NotNull
    private String comment;
    
    @NotNull
    private double amount;
    
    @ManyToOne
    private CustomerEntity customer;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return orderId;
    }

    public void setId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrderEntity)) {
            return false;
        }
        CustomerOrderEntity other = (CustomerOrderEntity) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CustomerOrder[ id=" + orderId + " ]";
    }
    
}
