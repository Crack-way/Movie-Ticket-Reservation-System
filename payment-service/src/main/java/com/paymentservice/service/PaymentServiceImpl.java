package com.paymentservice.service;


import com.paymentservice.dto.PaymentRequest;
import com.paymentservice.dto.PaymentResponse;
import com.paymentservice.entity.Payment;
import com.paymentservice.enums.PaymentStatus;
import com.paymentservice.exception.PaymentDoesNotExistException;
import com.paymentservice.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {


        Payment payment = paymentRepo.save(toPayment(paymentRequest));
        return toPaymentResponse(payment);

    }

    public Payment toPayment(PaymentRequest paymentRequest) {

        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setUser_id(paymentRequest.getUserId());
        return payment;
    }

    public PaymentResponse toPaymentResponse(Payment payment) {

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentStatus(payment.getPaymentStatus().toString());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setPaymentId(payment.getPayment_id());
        paymentResponse.setUserId(payment.getUser_id());

        return paymentResponse;
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest, int paymentId) {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new PaymentDoesNotExistException("Payment Doesnt exist"));
        payment.setAmount(paymentRequest.getAmount());
        payment.setUser_id(paymentRequest.getUserId());
        paymentRepo.save(payment);
        return toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse checkPayment(int paymentId) {


        return toPaymentResponse(paymentRepo.findById(paymentId).orElseThrow(() -> new PaymentDoesNotExistException("Payment with id" + paymentId + "doesnt exist")));
    }

}
