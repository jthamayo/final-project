package io.github.jthamayo.backend.dto;

import java.util.List;

public class NetworkChatDto extends ChatDto {

    private Long networkId;
    private List<UserSummary> participants;

    public Long getNetworkId() {
	return networkId;
    }

    public void setNetworkId(Long networkId) {
	this.networkId = networkId;
    }

    public List<UserSummary> getParticipants() {
	return participants;
    }

    public void setParticipantsUsername(List<UserSummary> participants) {
	this.participants = participants;
    }

    public void setParticipants(List<UserSummary> participants) {
	this.participants = participants;
    }

    /////////////////////////////// CONSTRUCTOR///////////////////////////////////////

    
    public NetworkChatDto(Long id, Long networkId, List<UserSummary> participants, List<MessageDto> messages) {
	this.setId(id);
	this.setMessages(messages);
	this.networkId = networkId;
	this.participants = participants;
    }
}
