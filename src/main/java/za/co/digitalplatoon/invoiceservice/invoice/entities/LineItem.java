package za.co.digitalplatoon.invoiceservice.invoice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static javax.persistence.FetchType.LAZY;

@Entity
public class LineItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;

    @ManyToOne(fetch = LAZY)
    private Invoice invoice;

    public LineItem(){}

    public LineItem(Long quantity, String description, BigDecimal unitPrice) {
        this.quantity = quantity;
        this.description = description;
        this.unitPrice = unitPrice;
    }
    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    //getLineItemTotal calculations
    public BigDecimal getLineItemTotal(){
       return getUnitPrice()
               .multiply(BigDecimal.valueOf(getQuantity()))
               .setScale(2, RoundingMode.HALF_UP);
    }

}
