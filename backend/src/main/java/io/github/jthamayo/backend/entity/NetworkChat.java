package io.github.jthamayo.backend.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("NETWORK")
public class NetworkChat extends Chat {

    @OneToOne
    @JoinColumn(name = "connection_id", unique = true)
    private Network network;

    public Network getNetwork() {
	return network;
    }

    public void setNetwork(Network network) {
	this.network = network;
    }

    ///////////////////// CONSTRUCTOR////////////////////////////

    public NetworkChat(Network network, List<Message> messages) {
	this.network = network;
	setMessages(messages);
    }

    public NetworkChat() {
	
    }

}
