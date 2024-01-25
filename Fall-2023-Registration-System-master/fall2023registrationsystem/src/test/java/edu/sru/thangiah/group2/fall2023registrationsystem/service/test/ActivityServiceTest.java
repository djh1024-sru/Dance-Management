package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ActivitiesRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ActivityServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ActivityServiceTest {

    @Mock
    private ActivitiesRepository activitiesRepository;
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private Activities testActivity;
    private Instructor testInstructor;

    @BeforeEach
    public void setUp() {
        testActivity = new Activities(); 
        testInstructor = new Instructor();
    }

    @Test
    public void getActivitiesTest() {
        when(activitiesRepository.findAll()).thenReturn(Arrays.asList(testActivity));
        List<Activities> activities = activityService.getActivities();
        assertNotNull(activities);
        assertEquals(1, activities.size());
        verify(activitiesRepository).findAll();
    }

    @Test
    public void getActsByLocationTest() {
        when(activitiesRepository.findActivitiesByLocationName("Mercer")).thenReturn(Arrays.asList(testActivity));
        List<Activities> activities = activityService.getActsByLocation("Mercer");
        assertNotNull(activities);
        assertEquals(4, activities.size());
        verify(activitiesRepository).findActivitiesByLocationName("Mercer");
    }

    @Test
    public void findActivitiesByInstructorIDTest() {
        when(activitiesRepository.findByInstructorID(1)).thenReturn(Arrays.asList(testActivity));
        List<Activities> activities = activityService.findActivitiesByInstructorID(1);
        assertNotNull(activities);
        assertEquals(1001, activities.size());
        verify(activitiesRepository).findByInstructorID(1);
    }

    @Test
    public void findInstructorByEmailTest() {
        when(instructorRepository.findByEmail("asmith@studio.com")).thenReturn(testInstructor);
        Instructor instructor = activityService.findInstructorByEmail("asmith@studio.com");
        assertNotNull(instructor);
        verify(instructorRepository).findByEmail("asmith@studio.com");
    }

    @Test
    public void deleteClassTest() {
        doNothing().when(activitiesRepository).deleteByActivityIDAndLocationNameAndActivityLevel("Acro", "Slippery Rock", "1");
        activityService.deleteClass("Acro", "Slippery Rock", "1");
        verify(activitiesRepository).deleteByActivityIDAndLocationNameAndActivityLevel("Acrp", "Slippery Rock", "1");
    }


    @Test
    public void getActivityByIdTest() {
        when(activitiesRepository.findById("Tap")).thenReturn(Optional.of(testActivity));
        Activities activity = activityService.getActivityById("Tap");
        assertNotNull(activity);
        verify(activitiesRepository).findById("Tap");
    }
}
