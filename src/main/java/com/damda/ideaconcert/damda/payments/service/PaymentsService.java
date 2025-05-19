package com.damda.ideaconcert.damda.payments.service;

import com.damda.ideaconcert.damda.payments.domain.Payments;
import com.damda.ideaconcert.damda.payments.dto.PaymentsInsertReq;
import com.damda.ideaconcert.damda.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;

    public void paymentsInsert(PaymentsInsertReq req,int userId){
        //현재 날짜 가져오는 부분
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Payments payments = Payments.builder()
                .user_id(userId)
                .subscribe_goods_id(req.getSubscribe_goods_id())
                .merchant_uid(req.getMerchant_uid())
                .imp_uid(req.getImp_uid())
                .pay_method(req.getPay_method())
                .channel(req.getChannel())
                .pg_provider(req.getPg_provider())
                .pg_tid(req.getPg_tid())
                .escrow(req.getEscrow())
                .apply_num(req.getApply_num())
                .bank_code(req.getBank_code())
                .bank_name(req.getBank_name())
                .card_code(req.getCard_code())
                .card_number(req.getCard_number())
                .card_quota(req.getCard_quota())
                .card_type(req.getCard_type())
                .vbank_code(req.getVbank_code())
                .vbank_name(req.getVbank_name())
                .vbank_num(req.getVbank_num())
                .vbank_holder(req.getVbank_holder())
                .vbank_date(req.getVbank_date())
                .vbank_issued_at(req.getVbank_issued_at())
                .name(req.getName())
                .amount(req.getAmount())
                .cancel_amount(req.getCancel_amount())
                .currency(req.getCurrency())
                .buyer_name(req.getBuyer_name())
                .buyer_email(req.getBuyer_email())
                .buyer_tel(req.getBuyer_tel())
                .buyer_addr(req.getBuyer_addr())
                .buyer_postcode(req.getBuyer_postcode())
                .cancel_request_amount(req.getCancel_request_amount())
                .refund_reason(req.getRefund_reason())
                .refund_holder(req.getRefund_holder())
                .refund_bank(req.getRefund_bank())
                .refund_account(req.getRefund_account())
                .custom_data(req.getCustom_data())
                .status(req.getStatus())
                .paid_at(req.getPaid_at())
                .failed_at(req.getFailed_at())
                .cancelled_at(req.getCancelled_at())
                .fail_reason(req.getFail_reason())
                .cancel_reason(req.getCancel_reason())
                .receipt_url(req.getReceipt_url())
                .cash_receipt_issued(req.getCash_receipt_issued())
                .customer_uid(req.getCustomer_uid())
                .customer_uid_usage(req.getCustomer_uid_usage())
                .origin_amount(req.getOrigin_amount())
                .origin_currency(req.getOrigin_currency())
                .exchange_rate(req.getExchange_rate())
                .bad_yn(req.getBad_yn())
                .build();
        paymentsRepository.save(payments);
    }

    public List<Payments> paymentsList(){

        List<Payments> payments = paymentsRepository.findAll();

        if(payments != null) {
            return payments;
        }else{
            return null;
        }
    }
}
