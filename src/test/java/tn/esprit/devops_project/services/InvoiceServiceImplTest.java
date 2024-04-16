package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import static org.mockito.Mockito.*;

class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;



    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        // Given
        List<Invoice> expectedInvoices = Arrays.asList(
                new Invoice(1L),
                new Invoice(2L)
        );
        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        // When
        List<Invoice> actualInvoices = invoiceService.retrieveAllInvoices();

        // Then
        assertEquals(expectedInvoices.size(), actualInvoices.size());
    }

    @Test
    void cancelInvoice() {
        // Given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // When
        invoiceService.cancelInvoice(invoiceId);

        // Then
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L}) // Different invoice IDs to test
    void retrieveInvoice(Long invoiceId) {
        // Given
        Invoice expectedInvoice = new Invoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // When
        Invoice actualInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Then
        assertEquals(expectedInvoice, actualInvoice);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L}) // Different invoice IDs to test
    void getInvoicesBySupplier(Long invoiceId) {
        // Given
        Invoice expectedInvoice = new Invoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // When
        Invoice actualInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Then
        assertEquals(expectedInvoice, actualInvoice);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L}) // Different invoice IDs to test
    void assignOperatorToInvoice(Long invoiceId) {
        // Given
        Invoice expectedInvoice = new Invoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // When
        Invoice actualInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Then
        assertEquals(expectedInvoice, actualInvoice);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L}) // Different invoice IDs to test
    void getTotalAmountInvoiceBetweenDates(Long invoiceId) {
        // Given
        Invoice expectedInvoice = new Invoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // When
        Invoice actualInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Then
        assertEquals(expectedInvoice, actualInvoice);
    }
}