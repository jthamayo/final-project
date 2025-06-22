package io.github.jthamayo.backend.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_chats")
@DiscriminatorValue("GROUP")
public class GroupChat extends Chat {

    @OneToOne
    @JoinColumn(name = "group_id", unique = true)
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    /////////////////////////////////CONSTRUCTOR//////////////////////////////////////

    public GroupChat(Long id, Group group, List<Message> messages) {
	this.group = group;
	this.setMessages(messages);
    }
    
    public GroupChat() {

    }
    
}
