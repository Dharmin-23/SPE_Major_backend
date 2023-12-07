package com.shubham.swapsie.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.Product;
import com.shubham.swapsie.model.SwapRequest;
import com.shubham.swapsie.model.User;
import com.shubham.swapsie.repository.ProductRepository;
import com.shubham.swapsie.repository.SwapRequestRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SwapRequestServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SwapRequestServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private SwapRequestRepository swapRequestRepository;

    @Autowired
    private SwapRequestServiceImpl swapRequestServiceImpl;

    /**
     * Method under test: {@link SwapRequestServiceImpl#createSwapRequest(SwapRequest)}
     */
    @Test
    void testCreateSwapRequest() {
        SwapRequest swapRequest = new SwapRequest();
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(swapRequest);
        assertSame(swapRequest, swapRequestServiceImpl.createSwapRequest(new SwapRequest()));
        verify(swapRequestRepository).save((SwapRequest) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#createSwapRequest(SwapRequest)}
     */
    @Test
    void testCreateSwapRequest2() {
        when(swapRequestRepository.save((SwapRequest) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.createSwapRequest(new SwapRequest()));
        verify(swapRequestRepository).save((SwapRequest) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getAllSwapRequests()}
     */
    @Test
    void testGetAllSwapRequests() {
        ArrayList<SwapRequest> swapRequestList = new ArrayList<>();
        when(swapRequestRepository.findAll()).thenReturn(swapRequestList);
        List<SwapRequest> actualAllSwapRequests = swapRequestServiceImpl.getAllSwapRequests();
        assertSame(swapRequestList, actualAllSwapRequests);
        assertTrue(actualAllSwapRequests.isEmpty());
        verify(swapRequestRepository).findAll();
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getAllSwapRequests()}
     */
    @Test
    void testGetAllSwapRequests2() {
        when(swapRequestRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getAllSwapRequests());
        verify(swapRequestRepository).findAll();
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#deleteSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteSwapRequest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getName()" because the return value of "com.shubham.swapsie.model.SwapRequest.getProduct1()" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.deleteSwapRequest(SwapRequestServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(swapRequestRepository).delete((SwapRequest) any());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(new SwapRequest()));
        swapRequestServiceImpl.deleteSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#deleteSwapRequest(long)}
     */
    @Test
    void testDeleteSwapRequest2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequestRepository)
                .delete((SwapRequest) any());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(new SwapRequest()));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.deleteSwapRequest(1L));
        verify(swapRequestRepository).findById((Long) any());
        verify(swapRequestRepository).delete((SwapRequest) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#deleteSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteSwapRequest3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getName()" because the return value of "com.shubham.swapsie.model.SwapRequest.getProduct2()" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.deleteSwapRequest(SwapRequestServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product());
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        doNothing().when(swapRequestRepository).delete((SwapRequest) any());
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        swapRequestServiceImpl.deleteSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#deleteSwapRequest(long)}
     */
    @Test
    void testDeleteSwapRequest4() {
        Product product = new Product();
        product.setName("Deleted Successfully. SwapRequest of (Product 1:");
        Product product1 = new Product();
        Product product2 = new Product();
        User user1 = new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou");

        SwapRequest swapRequest = new SwapRequest(1L, "Status", product1, product2, user1,
                new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou"));
        swapRequest.setProduct1(product);
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        doNothing().when(swapRequestRepository).delete((SwapRequest) any());
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals(
                "Deleted Successfully. SwapRequest of (Product 1:Deleted Successfully. SwapRequest of (Product 1: and"
                        + " Product 2:null) having id:1 deleted successfully",
                swapRequestServiceImpl.deleteSwapRequest(1L));
        verify(swapRequestRepository).findById((Long) any());
        verify(swapRequestRepository).delete((SwapRequest) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#deleteSwapRequest(long)}
     */
    @Test
    void testDeleteSwapRequest5() {
        doNothing().when(swapRequestRepository).delete((SwapRequest) any());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.deleteSwapRequest(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestById(long)}
     */
    @Test
    void testGetSwapRequestById() {
        SwapRequest swapRequest = new SwapRequest();
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(swapRequest));
        assertSame(swapRequest, swapRequestServiceImpl.getSwapRequestById(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestById(long)}
     */
    @Test
    void testGetSwapRequestById2() {
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestById(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestById(long)}
     */
    @Test
    void testGetSwapRequestById3() {
        when(swapRequestRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestById(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#updateSwapRequest(long, SwapRequest)}
     */
    @Test
    void testUpdateSwapRequest() {
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        SwapRequest swapRequest = new SwapRequest();
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(swapRequest));
        SwapRequest actualUpdateSwapRequestResult = swapRequestServiceImpl.updateSwapRequest(1L, new SwapRequest());
        assertSame(swapRequest, actualUpdateSwapRequestResult);
        assertNull(actualUpdateSwapRequestResult.getUser2());
        assertNull(actualUpdateSwapRequestResult.getUser1());
        assertNull(actualUpdateSwapRequestResult.getStatus());
        assertNull(actualUpdateSwapRequestResult.getProduct2());
        assertNull(actualUpdateSwapRequestResult.getProduct1());
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#updateSwapRequest(long, SwapRequest)}
     */
    @Test
    void testUpdateSwapRequest2() {
        when(swapRequestRepository.save((SwapRequest) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(new SwapRequest()));
        assertThrows(ResourceNotFoundException.class,
                () -> swapRequestServiceImpl.updateSwapRequest(1L, new SwapRequest()));
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#updateSwapRequest(long, SwapRequest)}
     */
    @Test
    void testUpdateSwapRequest3() {
        SwapRequest swapRequest = mock(SwapRequest.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setProduct1((Product) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setProduct2((Product) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setStatus((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setUser1((User) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setUser2((User) any());
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> swapRequestServiceImpl.updateSwapRequest(1L, new SwapRequest()));
        verify(swapRequestRepository).findById((Long) any());
        verify(swapRequest).setStatus((String) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#updateSwapRequest(long, SwapRequest)}
     */
    @Test
    void testUpdateSwapRequest4() {
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        assertThrows(ResourceNotFoundException.class,
                () -> swapRequestServiceImpl.updateSwapRequest(1L, new SwapRequest()));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#updateSwapRequest(long, SwapRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateSwapRequest5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.SwapRequest.getStatus()" because "swapRequest" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.updateSwapRequest(SwapRequestServiceImpl.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        SwapRequest swapRequest = mock(SwapRequest.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setProduct1((Product) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setProduct2((Product) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setStatus((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setUser1((User) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(swapRequest).setUser2((User) any());
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        swapRequestServiceImpl.updateSwapRequest(1L, null);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#acceptSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAcceptSwapRequest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getUser()" because "product1" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.acceptSwapRequest(SwapRequestServiceImpl.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(new SwapRequest()));
        swapRequestServiceImpl.acceptSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#acceptSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAcceptSwapRequest2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getUser()" because "product2" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.acceptSwapRequest(SwapRequestServiceImpl.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product());
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        swapRequestServiceImpl.acceptSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#acceptSwapRequest(long)}
     */
    @Test
    void testAcceptSwapRequest3() {
        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product(1L, "Name", "The characteristics of someone or something", "Img Link",
                new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        SwapRequest swapRequest1 = new SwapRequest();
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(swapRequest1);
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(swapRequest1, swapRequestServiceImpl.acceptSwapRequest(1L));
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#acceptSwapRequest(long)}
     */
    @Test
    void testAcceptSwapRequest4() {
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.acceptSwapRequest(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#acceptSwapRequest(long)}
     */
    @Test
    void testAcceptSwapRequest5() {
        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product(1L, "Name", "The characteristics of someone or something", "Img Link",
                new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.save((SwapRequest) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.acceptSwapRequest(1L));
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#declineSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeclineSwapRequest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getUser()" because "product1" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.declineSwapRequest(SwapRequestServiceImpl.java:106)
        //   See https://diff.blue/R013 to resolve this issue.

        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.of(new SwapRequest()));
        swapRequestServiceImpl.declineSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#declineSwapRequest(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeclineSwapRequest2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getUser()" because "product2" is null
        //       at com.shubham.swapsie.service.impl.SwapRequestServiceImpl.declineSwapRequest(SwapRequestServiceImpl.java:106)
        //   See https://diff.blue/R013 to resolve this issue.

        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product());
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        swapRequestServiceImpl.declineSwapRequest(1L);
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#declineSwapRequest(long)}
     */
    @Test
    void testDeclineSwapRequest3() {
        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product(1L, "Name", "The characteristics of someone or something", "Img Link",
                new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Failed !!. Already swapped !!!. The product has already been swapped with someone else",
                swapRequestServiceImpl.declineSwapRequest(1L));
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#declineSwapRequest(long)}
     */
    @Test
    void testDeclineSwapRequest4() {
        when(swapRequestRepository.save((SwapRequest) any())).thenReturn(new SwapRequest());
        when(swapRequestRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.declineSwapRequest(1L));
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#declineSwapRequest(long)}
     */
    @Test
    void testDeclineSwapRequest5() {
        SwapRequest swapRequest = new SwapRequest();
        swapRequest.setProduct1(new Product(1L, "Name", "The characteristics of someone or something", "Img Link",
                new User(1L, "F Name", "L Name", "jane.doe@example.org", "iloveyou")));
        Optional<SwapRequest> ofResult = Optional.of(swapRequest);
        when(swapRequestRepository.save((SwapRequest) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(swapRequestRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.declineSwapRequest(1L));
        verify(swapRequestRepository).save((SwapRequest) any());
        verify(swapRequestRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByProduct1(long)}
     */
    @Test
    void testGetSwapRequestByProduct1() {
        ArrayList<SwapRequest> swapRequestList = new ArrayList<>();
        when(swapRequestRepository.findSwapRequestsByProduct1Id(anyLong())).thenReturn(swapRequestList);
        List<SwapRequest> actualSwapRequestByProduct1 = swapRequestServiceImpl.getSwapRequestByProduct1(1L);
        assertSame(swapRequestList, actualSwapRequestByProduct1);
        assertTrue(actualSwapRequestByProduct1.isEmpty());
        verify(swapRequestRepository).findSwapRequestsByProduct1Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByProduct1(long)}
     */
    @Test
    void testGetSwapRequestByProduct12() {
        when(swapRequestRepository.findSwapRequestsByProduct1Id(anyLong()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestByProduct1(1L));
        verify(swapRequestRepository).findSwapRequestsByProduct1Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByProduct2(long)}
     */
    @Test
    void testGetSwapRequestByProduct2() {
        ArrayList<SwapRequest> swapRequestList = new ArrayList<>();
        when(swapRequestRepository.findSwapRequestsByProduct2Id(anyLong())).thenReturn(swapRequestList);
        List<SwapRequest> actualSwapRequestByProduct2 = swapRequestServiceImpl.getSwapRequestByProduct2(1L);
        assertSame(swapRequestList, actualSwapRequestByProduct2);
        assertTrue(actualSwapRequestByProduct2.isEmpty());
        verify(swapRequestRepository).findSwapRequestsByProduct2Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByProduct2(long)}
     */
    @Test
    void testGetSwapRequestByProduct22() {
        when(swapRequestRepository.findSwapRequestsByProduct2Id(anyLong()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestByProduct2(1L));
        verify(swapRequestRepository).findSwapRequestsByProduct2Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByUser1(long)}
     */
    @Test
    void testGetSwapRequestByUser1() {
        ArrayList<SwapRequest> swapRequestList = new ArrayList<>();
        when(swapRequestRepository.findAllSwapRequestByUser1Id(anyLong())).thenReturn(swapRequestList);
        List<SwapRequest> actualSwapRequestByUser1 = swapRequestServiceImpl.getSwapRequestByUser1(1L);
        assertSame(swapRequestList, actualSwapRequestByUser1);
        assertTrue(actualSwapRequestByUser1.isEmpty());
        verify(swapRequestRepository).findAllSwapRequestByUser1Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByUser1(long)}
     */
    @Test
    void testGetSwapRequestByUser12() {
        when(swapRequestRepository.findAllSwapRequestByUser1Id(anyLong()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestByUser1(1L));
        verify(swapRequestRepository).findAllSwapRequestByUser1Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByUser2(long)}
     */
    @Test
    void testGetSwapRequestByUser2() {
        ArrayList<SwapRequest> swapRequestList = new ArrayList<>();
        when(swapRequestRepository.findAllSwapRequestByUser2Id(anyLong())).thenReturn(swapRequestList);
        List<SwapRequest> actualSwapRequestByUser2 = swapRequestServiceImpl.getSwapRequestByUser2(1L);
        assertSame(swapRequestList, actualSwapRequestByUser2);
        assertTrue(actualSwapRequestByUser2.isEmpty());
        verify(swapRequestRepository).findAllSwapRequestByUser2Id(anyLong());
    }

    /**
     * Method under test: {@link SwapRequestServiceImpl#getSwapRequestByUser2(long)}
     */
    @Test
    void testGetSwapRequestByUser22() {
        when(swapRequestRepository.findAllSwapRequestByUser2Id(anyLong()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> swapRequestServiceImpl.getSwapRequestByUser2(1L));
        verify(swapRequestRepository).findAllSwapRequestByUser2Id(anyLong());
    }
}

