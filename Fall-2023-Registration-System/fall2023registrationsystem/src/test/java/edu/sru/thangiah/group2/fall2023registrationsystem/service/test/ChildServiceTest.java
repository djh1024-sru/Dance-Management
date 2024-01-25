package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ChildService;

@ExtendWith(MockitoExtension.class)
public class ChildServiceTest {

    @Mock
    private ChildRepository childRepository;

    @InjectMocks
    private ChildService childService;

    private Children testChild;

    @BeforeEach
    public void setUp() {
        testChild = new Children(); 
        testChild.setChildID(3010);
    }

    @Test
    public void updateChildAssignmentTest() {
        when(childRepository.findDistinctByChildID(testChild.getChildID())).thenReturn(Arrays.asList(testChild));
        
        childService.updateChildAssignment(testChild);

        verify(childRepository).findDistinctByChildID(testChild.getChildID());
        verify(childRepository).saveAll(anyList());
    }

}
