package com.zaga.consumer;

import com.zaga.event.EventDto;
import com.zaga.model.entity.ProjectDetails;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MailDeserializer extends ObjectMapperDeserializer<EventDto> {


    public MailDeserializer() {

        super(EventDto.class);

    }

}

