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
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.LocationRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.DisplayServicesImpl;

@ExtendWith(MockitoExtension.class)
public class DisplayServicesImplTest {

    @Mock
    private LocationRepository locationRepo;
    @Mock
    private InstructorRepository instructorRepo;
    @Mock
    private ChildRepository childRepo;
    @Mock
    private ParentRepository parentRepo;

    @InjectMocks
    private DisplayServicesImpl displayServices;

    @BeforeEach
    public void setUp() {
       
    }

    @Test
    public void getAllLocsTest() {
        when(locationRepo.findAll()).thenReturn(Arrays.asList(new Location()));
        List<Location> locations = displayServices.getAllLocs();
        assertNotNull(locations);
        verify(locationRepo).findAll();
    }

    @Test
    public void getAllInstructsTest() {
        when(instructorRepo.findAll()).thenReturn(Arrays.asList(new Instructor()));
        List<Instructor> instructors = displayServices.getAllInstructs();
        assertNotNull(instructors);
        verify(instructorRepo).findAll();
    }

    @Test
    public void getAllChildrenTest() {
        when(childRepo.findAll()).thenReturn(Arrays.asList(new Children()));
        List<Children> children = displayServices.getAllChildren();
        assertNotNull(children);
        verify(childRepo).findAll();
    }

    @Test
    public void getParentBalanceTest() {
        when(parentRepo.findAll()).thenReturn(Arrays.asList(new Parent()));
        List<Parent> parents = displayServices.getParentBalance();
        assertNotNull(parents);
        verify(parentRepo).findAll();
    }

}
