package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ActivitySectorImplTest {
    @Mock
    ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    ActivitySectorImpl activitySectorService;
    @Test
    void retrieveAllActivitySectors() {
        // Given
        List<ActivitySector> expectedActivitySectors = Arrays.asList(
                new ActivitySector(1L, "Sector 1"),
                new ActivitySector(2L, "Sector 2")
        );
        when(activitySectorRepository.findAll()).thenReturn(expectedActivitySectors);

        // When
        List<ActivitySector> actualActivitySectors = activitySectorService.retrieveAllActivitySectors();

        // Then
        assertEquals(expectedActivitySectors.size(), actualActivitySectors.size());
    }

    @Test
    void addActivitySector() {
        // Given
        ActivitySector activitySector = new ActivitySector(1L, "New Sector");
        when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(activitySector);

        // When
        ActivitySector savedActivitySector = activitySectorService.addActivitySector(activitySector);

        // Then
        assertEquals(activitySector.getName(), savedActivitySector.getName());
    }

    @Test
    void deleteActivitySector() {
        // Given
        Long id = 1L;

        // When
        activitySectorService.deleteActivitySector(id);

        // Then
        verify(activitySectorRepository, times(1)).deleteById(id);
    }

    @Test
    void updateActivitySector() {
        // Given
        ActivitySector activitySector = new ActivitySector(1L, "Updated Sector");
        when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(activitySector);

        // When
        ActivitySector updatedActivitySector = activitySectorService.updateActivitySector(activitySector);

        // Then
        assertEquals(activitySector.getName(), updatedActivitySector.getName());
    }

    @Test
    void retrieveActivitySector() {
        // Given
        Long id = 1L;
        ActivitySector activitySector = new ActivitySector(id, "Sector");
        when(activitySectorRepository.findById(id)).thenReturn(Optional.of(activitySector));

        // When
        ActivitySector retrievedActivitySector = activitySectorService.retrieveActivitySector(id);

        // Then
        assertEquals(activitySector.getName(), retrievedActivitySector.getName());
    }
}