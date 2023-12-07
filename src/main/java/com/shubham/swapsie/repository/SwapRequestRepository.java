package com.shubham.swapsie.repository;

import com.shubham.swapsie.model.SwapRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwapRequestRepository extends JpaRepository<SwapRequest, Long> {

    public List<SwapRequest> findSwapRequestsByProduct1Id(long product1_id);

    public List<SwapRequest> findSwapRequestsByProduct2Id(long product2_id);


    public List<SwapRequest> findAllSwapRequestByUser1Id(long user1_id);

    public List<SwapRequest> findAllSwapRequestByUser2Id(long user2_id);
}
