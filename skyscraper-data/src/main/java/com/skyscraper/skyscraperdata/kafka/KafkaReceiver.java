package com.skyscraper.skyscraperdata.kafka;

import com.alibaba.fastjson.JSON;
import com.skyscraper.skyscraperdata.dto.PaperDTO;
import com.skyscraper.skyscraperdata.elasticsearch.helper.EsOperatorHepler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

/**
 * create by sumerian on 2020/6/19
 * <p>
 * desc: kafka消费者的定义
 **/
@Component
@Slf4j
public class KafkaReceiver {

    @Autowired
    EsOperatorHepler esOperatorHepler;

    //kafka在消费的时候，必须指定消费的topic和groupId，
    @KafkaListener(topics = "hello", groupId = "test-hello-group")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) throws IOException {
        log.info("record===={}", record);
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object msg = kafkaMessage.get();
            PaperDTO paperDTO = JSON.parseObject(JSON.toJSONString(msg), PaperDTO.class);
            esOperatorHepler.intsert(paperDTO);
            log.info("record@===================={}", record);
            log.info("msg@={}", msg);
            //设置ack之后,kafka在处理完消息之后，会通知zookeeper该条消息已经被消费，broker就将该条消息丢弃，
            //该条消息就不会被重复消费。这里也就是值的是手动提交偏移量。
            //但是要在配置文件中将enable-auto-commit设值为false
            ack.acknowledge();

        }
    }
}
