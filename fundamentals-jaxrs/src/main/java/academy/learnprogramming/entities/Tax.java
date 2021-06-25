package academy.learnprogramming.entities;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "tax_id"))
public class Tax extends AbstractEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Column(name = "TAX_RATE")
    private BigDecimal taxRate;

    public BigDecimal getTaxRate() {
	return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
	this.taxRate = taxRate;
    }
}
