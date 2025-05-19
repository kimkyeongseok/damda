package com.damda.ideaconcert.damda.payments.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    @Column(nullable = false)
    private int user_id;
    @Column(nullable = false)
    private int subscribe_goods_id;
    @Column(nullable = false)
    private String merchant_uid;
    @Column(nullable = false)
    private String imp_uid;
    @Column(nullable = false)
    private int charge_seq;
    @Column(nullable = false)
    private String pay_method;
    @Column(nullable = false)
    private String channel;
    @Column(nullable = false)
    private String pg_provider;
    @Column(nullable = false)
    private String pg_tid;
    @Column(nullable = false)
    private String escrow;
    @Column(nullable = false)
    private String apply_num;
    @Column(nullable = false)
    private String bank_code;
    @Column(nullable = false)
    private String bank_name;
    @Column(nullable = false)
    private String card_code;
    @Column(nullable = false)
    private String card_name;
    @Column(nullable = false)
    private String card_number;
    @Column(nullable = false)
    private int card_quota;
    @Column(nullable = false)
    private int card_type;
    @Column(nullable = false)
    private String vbank_code;
    @Column(nullable = false)
    private String vbank_name;
    @Column(nullable = false)
    private String vbank_num;
    @Column(nullable = false)
    private String vbank_holder;
    @Column(nullable = false)
    private BigInteger vbank_date;
    @Column(nullable = false)
    private BigInteger vbank_issued_at;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal cancel_amount;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private String buyer_name;
    @Column(nullable = false)
    private String buyer_email;
    @Column(nullable = false)
    private String buyer_tel;
    @Column(nullable = false)
    private String buyer_addr;
    @Column(nullable = false)
    private String buyer_postcode;
    @Column(nullable = false)
    private String cancel_request_amount;
    @Column(nullable = false)
    private String refund_reason;
    @Column(nullable = false)
    private String refund_holder;
    @Column(nullable = false)
    private String refund_bank;
    @Column(nullable = false)
    private String refund_account;
    @Column(nullable = false)
    private String custom_data;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String started_at;
    @Column(nullable = false)
    private String paid_at;
    @Column(nullable = false)
    private String failed_at;
    @Column(nullable = false)
    private String cancelled_at;
    @Column(nullable = false)
    private String fail_reason;
    @Column(nullable = false)
    private String cancel_reason;
    @Column(nullable = false)
    private String receipt_url;
    @Column(nullable = false)
    private String cash_receipt_issued;
    @Column(nullable = false)
    private String customer_uid;
    @Column(nullable = false)
    private String customer_uid_usage;
    @Column(nullable = false)
    private BigDecimal origin_amount;
    @Column(nullable = false)
    private String origin_currency;
    @Column(nullable = false)
    private Double exchange_rate;
    @Column(nullable = false)
    private String bad_yn;

    @Builder
    public Payments(Integer no, int user_id, int subscribe_goods_id, String merchant_uid, String imp_uid, int charge_seq, String pay_method,
                    String channel, String pg_provider, String pg_tid, String escrow, String apply_num, String bank_code, String bank_name,
                    String card_code, String card_name, String card_number, int card_quota, int card_type, String vbank_code, String vbank_name,
                    String vbank_num, String vbank_holder, BigInteger vbank_date, BigInteger vbank_issued_at, String name, BigDecimal amount,
                    BigDecimal cancel_amount, String currency, String buyer_name, String buyer_email, String buyer_tel, String buyer_addr,
                    String buyer_postcode, String cancel_request_amount, String refund_reason, String refund_holder, String refund_bank,
                    String refund_account, String custom_data, String status, String started_at, String paid_at, String failed_at, String cancelled_at,
                    String fail_reason, String cancel_reason, String receipt_url, String cash_receipt_issued, String customer_uid, String customer_uid_usage,
                    BigDecimal origin_amount, String origin_currency, Double exchange_rate, String bad_yn
                    ){
        this.no = no;
        this.user_id = user_id;
        this.subscribe_goods_id = subscribe_goods_id;
        this.merchant_uid = merchant_uid;
        this.imp_uid = imp_uid;
        this.charge_seq = charge_seq;
        this.pay_method = pay_method;
        this.channel = channel;
        this.pg_provider = pg_provider;
        this.pg_tid = pg_tid;
        this.escrow = escrow;
        this.apply_num = apply_num;
        this.bank_code = bank_code;
        this.bank_name = bank_name;
        this.card_code = card_code;
        this.card_name = card_name;
        this.card_number = card_number;
        this.card_quota = card_quota;
        this.card_type = card_type;
        this.vbank_code = vbank_code;
        this.vbank_name = vbank_name;
        this.vbank_num = vbank_num;
        this.vbank_holder = vbank_holder;
        this.vbank_date = vbank_date;
        this.vbank_issued_at = vbank_issued_at;
        this.name = name;
        this.amount = amount;
        this.cancel_amount = cancel_amount;
        this.currency = currency;
        this.buyer_name = buyer_name;
        this.buyer_email = buyer_email;
        this.buyer_tel = buyer_tel;
        this.buyer_addr = buyer_addr;
        this.buyer_postcode = buyer_postcode;
        this.cancel_request_amount = cancel_request_amount;
        this.refund_reason = refund_reason;
        this.refund_holder = refund_holder;
        this.refund_bank = refund_bank;
        this.refund_account = refund_account;
        this.custom_data = custom_data;
        this.status = status;
        this.started_at = started_at;
        this.paid_at = paid_at;
        this.failed_at = failed_at;
        this.cancelled_at = cancelled_at;
        this.fail_reason = fail_reason;
        this.cancel_reason = cancel_reason;
        this.receipt_url = receipt_url;
        this.cash_receipt_issued = cash_receipt_issued;
        this.customer_uid = customer_uid;
        this.customer_uid_usage = customer_uid_usage;
        this.origin_amount = origin_amount;
        this.origin_currency = origin_currency;
        this.exchange_rate = exchange_rate;
        this.bad_yn = bad_yn;
    }
}
