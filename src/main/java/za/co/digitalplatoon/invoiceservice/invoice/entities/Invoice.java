package za.co.digitalplatoon.invoiceservice.invoice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String client;
    private Long vatRate=15L; //default if not entered

    @Temporal(DATE)
    private Date invoiceDate ;

    @OneToMany(cascade = ALL)
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
        this.vatRate = vatRate != null ? vatRate : 15L;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    //getSubTotal Calculations
    public BigDecimal getSubTotal(){
        return getLineItems().stream()
                .map(LineItem::getLineItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2,RoundingMode.HALF_UP);
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
        BigDecimal total = getSubTotal().add(getVat());
        return total.setScale(2,RoundingMode.HALF_UP);
    }
}
