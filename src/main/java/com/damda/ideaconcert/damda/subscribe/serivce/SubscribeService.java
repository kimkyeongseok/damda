package com.damda.ideaconcert.damda.subscribe.serivce;

import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import com.damda.ideaconcert.damda.subscribe.dto.SubscribeRes;
import com.damda.ideaconcert.damda.subscribe.repository.SubscribeRepository;
import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.damda.ideaconcert.damda.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final GoodsRepository goodsRepository;

    public void subscribeInsert(Integer userId,Integer paymentId,Integer subscribeGoodsId){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);
        SubscribeRes endDate = this.subscribeByUserId(userId);
        Goods goods = goodsRepository.subscribeGoodsByIds(subscribeGoodsId);
        String strDate ;
         if(endDate != null) {
              strDate = endDate.getEndDate();
         }else{
              strDate = now_dt;
         }

        try {
            Date date = format.parse(strDate);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date);
            cal1.add(Calendar.MONTH, goods.getMonth());
            Date startDate1 = new Date(cal1.getTimeInMillis());
            String endToDate = format.format(startDate1);

            Subscribe subscribe = Subscribe.builder()
                    .user_id(userId)
                    .payment_id(paymentId)
                    .goods_id(subscribeGoodsId)
                    .start_date(strDate)
                    .end_date(endToDate)
                    .build();
            subscribeRepository.save(subscribe);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @SneakyThrows
    public SubscribeRes subscribeByUserId(Integer userId){
        SubscribeRes subscribe = subscribeRepository.subscribeByUserId(userId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String strDate = subscribe.getEndDate();
        Date date = format.parse(strDate);

        String userYn;
        if(date.before(now) == true){
            userYn = "Y";
        }else{
            userYn = "N";
        }
        subscribe.setUseYn(userYn);

        if(subscribe != null) {
            return subscribe;
        }else{
            return null;
        }
    }
}
