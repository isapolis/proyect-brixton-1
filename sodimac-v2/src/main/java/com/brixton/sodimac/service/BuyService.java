package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.dto.request.compras.ReqBuyRequestDTO;
import com.brixton.sodimac.dto.response.OrderBuyResponseDTO;
import com.brixton.sodimac.dto.response.compras.ReqBuyResponseDTO;

import java.util.List;

public interface BuyService {
    ReqBuyResponseDTO createRequestBuy(ReqBuyRequestDTO reqBuyRequestDTO);
    List<ReqBuyResponseDTO> checkStatusOfRequestBuys(long idEmployee, String status);

    List<ReqBuyResponseDTO> getConfirmedBuys();

    void rejectRequestBuy(long id, String reason);

    OrderBuyResponseDTO createOrderBuy(long idEmployee, OrderBuyRequestDTO orderBuyRequestDTO);

//    List<ReqBuyResponseDTO> getTemporaryBuys();
//    List<ReqBuyResponseDTO> getConfirmedBuys();
//    List<ReqBuyResponseDTO> getApprovedBuys();
//    Object rejectBuy(long idBuy, ReasonRejectDTO reason);
//    Object createOrderBuy(OrderBuyRequestDTO orderBuy);
//    List<Object> getOrderBuy(long idOrderBuy);
//    OrderBuy getOrderBuyRequested(long idOrderBuy);
//    Buy getBuyApproved(long idBuy);

}
