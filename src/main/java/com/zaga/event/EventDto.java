package com.zaga.event;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.zaga.model.entity.ProjectDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto implements Serializable {
    public String source;
    public String destination;
    public String eventId;
    public LocalDateTime eventDate;
    public Object eventData;

}
