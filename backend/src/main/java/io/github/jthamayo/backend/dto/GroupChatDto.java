package io.github.jthamayo.backend.dto;

import java.util.List;

public class GroupChatDto extends ChatDto {

    private Long groupId;
    private List<String> participantUsernames;

    public Long getGroupId() {
	return groupId;
    }

    public void setGroupId(Long groupId) {
	this.groupId = groupId;
    }

    public List<String> getParticipantUsernames() {
	return participantUsernames;
    }

    public void setParticipantUsernames(List<String> participantUsernames) {
	this.participantUsernames = participantUsernames;
    }

    public GroupChatDto(Long groupId, List<String> participantUsernames, List<MessageDto> messages) {
	this.groupId = groupId;
	this.participantUsernames = participantUsernames;
	this.setMessages(messages);
    }
}
