package za.co.digitalplatoon.invoiceservice.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoiceservice.invoice.entities.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.services.impl.InvoiceServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping(InvoiceController.BASE_URL)
public class InvoiceController {

    public static final String BASE_URL="invoices";

    private InvoiceServiceImpl invoiceService;
    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }
    @GetMapping
    public Iterable<Invoice> viewAllInvoices() {
        return invoiceService.viewAllInvoices();
    }
    @GetMapping("/{invoiceId}")
    public Optional<Invoice> viewInvoice(@PathVariable("invoiceId") Long id) {
        return invoiceService.viewInvoice(id);
    }
}
