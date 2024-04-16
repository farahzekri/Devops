package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    public StockServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Example Stock");

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = stockService.addStock(stock);

        Assertions.assertEquals(stock, result);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void retrieveStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Example Stock");

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock result = stockService.retrieveStock(1L);

        Assertions.assertEquals(stock, result);
        verify(stockRepository, times(1)).findById(1L);
    }

    @Test
    void retrieveAllStock() {
        List<Stock> stocks = new ArrayList<>();
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stock1.setTitle("Stock 1");
        stocks.add(stock1);

        Stock stock2 = new Stock();
        stock2.setIdStock(2L);
        stock2.setTitle("Stock 2");
        stocks.add(stock2);

        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> result = stockService.retrieveAllStock();

        Assertions.assertEquals(stocks, result);
        verify(stockRepository, times(1)).findAll();
    }
}