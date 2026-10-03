package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_line")
class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_line_id", nullable = false)
    private Integer invoiceLineId;

    // FK -> invoice.invoice_id
    @NotNull
    @Column(name = "invoice_id", nullable = false)
    private Integer invoiceId;

    // FK -> track.track_id
    @NotNull
    @Column(name = "track_id", nullable = false)
    private Integer trackId;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    protected InvoiceLine() {
    }

    Integer getInvoiceLineId() {
        return invoiceLineId;
    }

    Integer getInvoiceId() {
        return invoiceId;
    }

    void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    Integer getTrackId() {
        return trackId;
    }

    void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    BigDecimal getUnitPrice() {
        return unitPrice;
    }

    void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    Integer getQuantity() {
        return quantity;
    }

    void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
