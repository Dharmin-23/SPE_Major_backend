package com.shubham.swapsie.service;

import com.shubham.swapsie.model.SwapRequest;

import java.util.List;

public interface SwapRequestService {
    SwapRequest createSwapRequest(SwapRequest swapRequest);

    List<SwapRequest> getAllSwapRequests();
    String deleteSwapRequest(long id);

    SwapRequest updateSwapRequest(long id, SwapRequest swapRequest);

    SwapRequest getSwapRequestById(long id);

    SwapRequest acceptSwapRequest(long id);

    String declineSwapRequest(long id);

    List<SwapRequest> getSwapRequestByProduct1(long product1_id);

    List<SwapRequest> getSwapRequestByProduct2(long product2_id);

    List<SwapRequest> getSwapRequestByUser1(long user1_id);

    List<SwapRequest> getSwapRequestByUser2(long user2_id);
}
