package za.co.digitalplatoon.invoiceservice.invoice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;
    private  Long vatRate;

    @Temporal(TemporalType.DATE)
    private Date invoiceDate ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    List<LineItem> lineItems;

    public Invoice(){}

    public Invoice(String client, Long vatRate, Date invoiceDate, List<LineItem> lineItems) {
        this.client = client;
        this.vatRate = vatRate;
        this.invoiceDate = invoiceDate;
        this.lineItems = lineItems;

    }
    @PrePersist
    public void prePersist(){
        invoiceDate = new Date();
    }
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }


    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    //getSubTotal Calculations
    public BigDecimal getSubTotal(){

        BigDecimal subtotal = new BigDecimal("0");

        for(LineItem lineItem : getLineItems()){
            subtotal = subtotal.add(lineItem.getLineItemTotal());
        }
        return subtotal.setScale(2,RoundingMode.HALF_UP);
    }
    //getVat Calculations
    public BigDecimal getVat(){
        BigDecimal vatPercentage =new BigDecimal(((double) getVatRate()/100))
                                  .setScale(2,RoundingMode.HALF_UP);

        BigDecimal calcVat = getSubTotal().multiply(vatPercentage);
        return calcVat.setScale(2,RoundingMode.HALF_UP);
    }
    //getTotal calculations
    public BigDecimal getTotal(){
        BigDecimal sub = getSubTotal().add(getVat());
        return sub.setScale(2,RoundingMode.HALF_UP);
    }

}
