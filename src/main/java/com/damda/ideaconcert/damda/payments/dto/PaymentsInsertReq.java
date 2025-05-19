package com.damda.ideaconcert.damda.payments.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class PaymentsInsertReq {

    private int subscribe_goods_id;
    private String merchant_uid;
    private String imp_uid;
    private int charge_seq;
    private String pay_method;
    private String channel;
    private String pg_provider;
    private String pg_tid;
    private String escrow;
    private String apply_num;
    private String bank_code;
    private String bank_name;
    private String card_code;
    private String card_name;
    private String card_number;
    private int card_quota;
    private int card_type;
    private String vbank_code;
    private String vbank_name;
    private String vbank_num;
    private String vbank_holder;
    private BigInteger vbank_date;
    private BigInteger vbank_issued_at;
    private String name;
    private BigDecimal amount;
    private BigDecimal cancel_amount;
    private String currency;
    private String buyer_name;
    private String buyer_email;
    private String buyer_tel;
    private String buyer_addr;
    private String buyer_postcode;
    private String cancel_request_amount;
    private String refund_reason;
    private String refund_holder;
    private String refund_bank;
    private String refund_account;
    private String custom_data;
    private String status;
    private String started_at;
    private String paid_at;
    private String failed_at;
    private String cancelled_at;
    private String fail_reason;
    private String cancel_reason;
    private String receipt_url;
    private String cash_receipt_issued;
    private String customer_uid;
    private String customer_uid_usage;
    private BigDecimal origin_amount;
    private String origin_currency;
    private Double exchange_rate;
    private String bad_yn;
}
