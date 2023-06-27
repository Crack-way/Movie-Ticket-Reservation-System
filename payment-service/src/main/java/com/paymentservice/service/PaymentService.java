package com.paymentservice.service;

import com.paymentservice.dto.PaymentRequest;
import com.paymentservice.dto.PaymentResponse;

public interface PaymentService{


     PaymentResponse makePayment(PaymentRequest paymentRequest);

     PaymentResponse updatePayment(PaymentRequest paymentRequest,int paymentId);


     PaymentResponse checkPayment(int paymentId);

}
