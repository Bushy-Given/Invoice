package za.co.digitalplatoon.invoiceservice.invoice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoiceservice.invoice.entities.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.repositories.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.services.InvoiceService;

import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Iterable<Invoice> viewAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> viewInvoice(Long id) {
        return invoiceRepository.findById(id);
    }
}
