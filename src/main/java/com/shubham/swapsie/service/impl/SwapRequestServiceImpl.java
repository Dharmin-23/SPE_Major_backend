package com.shubham.swapsie.service.impl;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.Product;
import com.shubham.swapsie.model.SwapRequest;
import com.shubham.swapsie.model.User;
import com.shubham.swapsie.repository.ProductRepository;
import com.shubham.swapsie.repository.SwapRequestRepository;
import com.shubham.swapsie.service.SwapRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwapRequestServiceImpl implements SwapRequestService {

    @Autowired
    SwapRequestRepository swapRequestRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public SwapRequest createSwapRequest(SwapRequest swapRequest) {
        return swapRequestRepository.save(swapRequest);
    }

    @Override
    public List<SwapRequest> getAllSwapRequests() {
        return swapRequestRepository.findAll();
    }

    @Override
    public String deleteSwapRequest(long id) {
        SwapRequest swapRequest = swapRequestRepository.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("Not Found !! SwapRequest with id:"+id+" does not exist"));
        swapRequestRepository.delete(swapRequest);
        return "Deleted Successfully. SwapRequest of (Product 1:"+swapRequest.getProduct1().getName()+" and Product 2:"+swapRequest.getProduct2().getName()+") having id:"+swapRequest.getId()+" deleted successfully";
    }

    @Override
    public SwapRequest getSwapRequestById(long id) {
        SwapRequest swapRequest = swapRequestRepository.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("Not Found !! SwapRequest with id:"+id+" does not exist"));
        return swapRequest;
    }

    @Override
    public SwapRequest updateSwapRequest(long id, SwapRequest swapRequest) {
        SwapRequest updatedSwapRequest = swapRequestRepository.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("Not Found !! SwapRequest with id:"+id+" does not exist"));

        updatedSwapRequest.setStatus(swapRequest.getStatus());
        updatedSwapRequest.setProduct1(swapRequest.getProduct1());
        updatedSwapRequest.setProduct2(swapRequest.getProduct2());
        updatedSwapRequest.setUser1(swapRequest.getUser1());
        updatedSwapRequest.setUser2(swapRequest.getUser2());
        swapRequestRepository.save(updatedSwapRequest);
        return updatedSwapRequest;
    }

    @Override
    public SwapRequest acceptSwapRequest(long id) {
        SwapRequest swapRequest = swapRequestRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Not Found !! SwapRequest with id:"+id+" does not exist"));

        Product product1 = swapRequest.getProduct1();
        Product product2 = swapRequest.getProduct2();
        User user1 = swapRequest.getUser1();
        User user2 = swapRequest.getUser2();

        //logic to check if not already processed.
        if(product1.getUser() != user1 || product2.getUser() != user2) {
            swapRequest.setStatus("Failed !!. Already swapped !!!. The product has already been swapped with someone else");
            SwapRequest failedSwapRequest = swapRequestRepository.save(swapRequest);
            return failedSwapRequest;
        }

        if( !swapRequest.getStatus().equals("Pending") ) {
            swapRequest.setStatus("Failed !!. Already Processed !!!. The Swap Request has already been processed earlier");
            SwapRequest failedSwapRequest = swapRequestRepository.save(swapRequest);
            return failedSwapRequest;
        }
        // logic complete for checking

        swapRequest.setStatus("Completed Successfully. ( "+user1.getId()+" ) : between ( FromUser1Id:"+user1.getId()+", ToUser2Id:"+user2.getId()+" )");
        product1.setUser(user2);
        product2.setUser(user1);
        productRepository.save(product1);
        productRepository.save(product2);
        SwapRequest acceptedSwapRequest = swapRequestRepository.save(swapRequest);

        return acceptedSwapRequest;
    }
    @Override
    public String declineSwapRequest(long id) {
        SwapRequest swapRequest = swapRequestRepository.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("Not Found !! SwapRequest with id:"+id+" does not exist"));

        Product product1 = swapRequest.getProduct1();
        Product product2 = swapRequest.getProduct2();
        User user1 = swapRequest.getUser1();
        User user2 = swapRequest.getUser2();

        //logic to check if not already processed.
        if(product1.getUser() != user1 || product2.getUser() != user2) {
            swapRequest.setStatus("Failed !!. Already swapped !!!. The product has already been swapped with someone else");
            SwapRequest failedSwapRequest = swapRequestRepository.save(swapRequest);
//            return failedSwapRequest;
            return ("Failed !!. Already swapped !!!. The product has already been swapped with someone else");
        }


        if( !swapRequest.getStatus().equals("Pending") ) {
//            return swapRequest;
            return "Failed !! Already processed!!!.  SwapRequest of (Product 1:" + swapRequest.getProduct1().getName() + " and Product 2:" + swapRequest.getProduct2().getName() + ") having id : " + swapRequest.getId() + " was already processed earlier";
        }

        swapRequest.setStatus("Declined.  between (FromUser1Id:"+user1.getId()+", ToUser2Id:"+user2+")");
        SwapRequest  declinedSwapRequest = swapRequestRepository.save(swapRequest);
//        return declinedSwapRequest;
        return "Declined !!! . SwapRequest of (Product 1:"+swapRequest.getProduct1().getName()+" and Product 2:"+swapRequest.getProduct2().getName()+") having id : "+swapRequest.getId()+" was declined";

    }

    @Override
    public List<SwapRequest> getSwapRequestByProduct1(long product1_id) {
        return swapRequestRepository.findSwapRequestsByProduct1Id(product1_id);
    }

    @Override
    public List<SwapRequest> getSwapRequestByProduct2(long product2_id) {
        return swapRequestRepository.findSwapRequestsByProduct2Id(product2_id);
    }

    @Override
    public List<SwapRequest> getSwapRequestByUser1(long user1_id) {
        return swapRequestRepository.findAllSwapRequestByUser1Id(user1_id);
    }

    @Override
    public List<SwapRequest> getSwapRequestByUser2(long user2_id) {
        return swapRequestRepository.findAllSwapRequestByUser2Id(user2_id);
    }
}
