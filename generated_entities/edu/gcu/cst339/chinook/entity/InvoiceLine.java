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
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_line")
class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_line_id", nullable = false)
    private Integer invoiceLineId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

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

    Invoice getInvoice() {
        return invoice;
    }

    void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    Track getTrack() {
        return track;
    }

    void setTrack(Track track) {
        this.track = track;
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
