//package com.example.springproject;
//
//import com.example.springproject.model.Courier;
//import com.example.springproject.model.Region;
//import com.example.springproject.repository.CourierRepo;
//import com.example.springproject.repository.RegionRepo;
//import com.example.springproject.repository.repositoryExceptions.*;
//import com.example.springproject.service.CourierService;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CourierServiceTest {
//
//    private UUID findCourierUUID;
//    private UUID addRegionUUID;
//    private UUID addNewRegionUUID;
//    private UUID getCourierForRegionUUID;
//
//    private Courier courier;
//
//    @Autowired
//    private RegionRepo regionRepo;
//
//    @Autowired
//    private CourierRepo courierRepo;
//
//    @Autowired
//    private CourierService courierService;
//
//    @BeforeAll
//    void setUp() throws CantCreateCourier, CantCreateRegion, RegionNotFound {
//        findCourierUUID = UUID.randomUUID();
//        addRegionUUID = UUID.randomUUID();
//        addNewRegionUUID = UUID.randomUUID();
//        getCourierForRegionUUID = UUID.randomUUID();
//
//        regionRepo.create(new Region(addRegionUUID, "region_1",
//                12.4, 123.4, 12.2, 12.3));
//        regionRepo.create(new Region(addNewRegionUUID, "region_2",
//                124.4, 1213.4, 412.2, 312.3));
//        regionRepo.create(new Region(getCourierForRegionUUID, "region_2", 21.0,
//                21.4, 32.1, 432.4));
//
//        courier = new Courier(findCourierUUID, 40.0,
//                39.0, regionRepo.find(addRegionUUID));
//        courierRepo.create(courier);
//    }
//
//    @Order(1)
//    @Test
//    public void findCourierTest() throws CourierNotFound {
//        assertEquals(courier.getUuid(), courierService.findCourier(findCourierUUID).getUuid());
//        assertThrows(CourierNotFound.class, () -> {
//            courierService.findCourier(null);
//        });
//    }
//
//    @Order(2)
//    @Test
//    public void createCourierTest() throws CantCreateRegion {
//        assertThrows(RegionNotFound.class, () -> {
//            courierService.createCourier(43.1, 43.2, UUID.randomUUID());
//        });
//        assertThrows(CantCreateCourier.class, () -> {
//            courierService.createCourier(43.1, 43.3, addRegionUUID);
//        });
//
//
//        assertDoesNotThrow(() -> {
//            courierService.createCourier(34.2, 30.0, addNewRegionUUID);
//        });
//    }
//
//    @Order(3)
//    @Test
//    public void getAssignCourierForRegionTest() throws RegionNotFound, NoCourierForThisRegion {
//        assertThrows(NoCourierForThisRegion.class, () -> {
//            courierService.getAssignCourierForRegion(getCourierForRegionUUID);
//        });
//        assertEquals(findCourierUUID, courierService.getAssignCourierForRegion(addRegionUUID).getUuid());
//    }
//
//    @Order(4)
//    @Test
//    public void deleteCourierTest() throws CourierNotFound {
//        assertTrue(courierService.deleteCourier(findCourierUUID));
//    }
//}
