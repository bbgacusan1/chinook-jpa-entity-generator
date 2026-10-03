package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Integer invoiceId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Size(max = 70)
    @Column(name = "billing_address", length = 70)
    private String billingAddress;

    @Size(max = 40)
    @Column(name = "billing_city", length = 40)
    private String billingCity;

    @Size(max = 40)
    @Column(name = "billing_state", length = 40)
    private String billingState;

    @Size(max = 40)
    @Column(name = "billing_country", length = 40)
    private String billingCountry;

    @Size(max = 10)
    @Column(name = "billing_postal_code", length = 10)
    private String billingPostalCode;

    @NotNull
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    protected Invoice() {
    }

    Integer getInvoiceId() {
        return invoiceId;
    }

    Customer getCustomer() {
        return customer;
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }

    LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    String getBillingAddress() {
        return billingAddress;
    }

    void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    String getBillingCity() {
        return billingCity;
    }

    void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    String getBillingState() {
        return billingState;
    }

    void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    String getBillingCountry() {
        return billingCountry;
    }

    void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    String getBillingPostalCode() {
        return billingPostalCode;
    }

    void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    BigDecimal getTotal() {
        return total;
    }

    void setTotal(BigDecimal total) {
        this.total = total;
    }
}
