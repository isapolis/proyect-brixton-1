package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.request.compras.ReqBuyRequestDTO;
import com.brixton.sodimac.dto.response.compras.ReqBuyResponseDTO;

public interface BuyService {
    ReqBuyResponseDTO createRequestBuy(ReqBuyRequestDTO reqBuyRequestDTO);
//    Object createRequerimentForBuy(BuyDTO requerimentBuy);
//    Object confirmRequerimentForBuy(long idBuy, BuyDTO requerimentBuy);
//    Object checkStatusOfRequerimentForBuy(long idEmployee, String status);
//    List<Object> getTemporaryBuys();
//    List<Object> getConfirmedBuys();
//    List<Object> getApprovedBuys();
//    Object rejectBuy(long idBuy, ReasonRejectDTO reason);
//    Object createOrderBuy(OrderBuyRequestDTO orderBuy);
//    List<Object> getOrderBuy(long idOrderBuy);
//    OrderBuy getOrderBuyRequested(long idOrderBuy);
//    Buy getBuyApproved(long idBuy);

}
