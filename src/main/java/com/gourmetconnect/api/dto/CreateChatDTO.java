package com.gourmetconnect.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateChatDTO {
    private List<String> participants;
    private boolean isGroup;
}
