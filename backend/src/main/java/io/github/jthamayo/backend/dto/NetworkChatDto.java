package io.github.jthamayo.backend.dto;

import java.util.List;


public class NetworkChatDto extends ChatDto{

    private Long networkId;
    private List<String> participantsUsername;

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public List<String> getParticipantUsername() {
        return participantsUsername;
    }
    
    public void setParticipantUsername(List<String> participantsUsername) {
	this.participantsUsername = participantsUsername;
    }

    ///////////////////////////////CONSTRUCTOR///////////////////////////////////////

    public NetworkChatDto(Long networkId, List<String> participantsUsername, List<MessageDto> messages) {
	this.setMessages(messages);
	this.networkId = networkId;
	this.participantsUsername = participantsUsername;
    }    
}
