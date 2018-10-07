package za.co.digitalplatoon.invoiceservice.invoice.services;


import za.co.digitalplatoon.invoiceservice.invoice.entities.Invoice;

import java.util.Optional;

public interface InvoiceService {
    //add invoice
    Invoice addInvoice(Invoice invoice);
    //view all invoice
    Iterable<Invoice> viewAllInvoices();
    //view invoice by id
    Optional<Invoice> viewInvoice(Long id);
}
