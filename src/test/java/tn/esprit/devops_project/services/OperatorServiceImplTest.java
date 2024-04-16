package tn.esprit.devops_project.services;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class OperatorServiceImplTest {
        @InjectMocks
        OperatorServiceImpl operatorService;
        @Mock
        OperatorRepository operatorRepository;
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

    @Test
    void retrieveAllOperators() {
        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);

        Operator operator2 = new Operator();
        operator2.setIdOperateur(2L);
        List<Operator> operators = Arrays.asList(operator1, operator2);

        when(operatorRepository.findAll()).thenReturn(operators);
        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();
        assertEquals(2, retrievedOperators.size());
        verify(operatorRepository, times(1)).findAll();
    }

    @Test
    void addOperator() {

        Operator operator = new Operator();
        operator.setIdOperateur(1L);

        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator addedOperator = operatorService.addOperator(operator);
        assertNotNull(addedOperator);
        verify(operatorRepository, times(1)).save(operator);
    }

    @Test
    void deleteOperator() {
        Long operatorId = 1L;
        operatorService.deleteOperator(operatorId);
        verify(operatorRepository, times(1)).deleteById(operatorId);
    }

    @Test
    void updateOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator updatedOperator = operatorService.updateOperator(operator);
        assertNotNull(updatedOperator);
        verify(operatorRepository, times(1)).save(operator);
    }

    @Test
    void retrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);
        assertNotNull(retrievedOperator);
        assertEquals(operatorId, retrievedOperator.getIdOperateur());
        verify(operatorRepository, times(1)).findById(operatorId);
    }
}