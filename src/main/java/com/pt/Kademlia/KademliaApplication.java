package com.pt.Kademlia;

import com.pt.Kademlia.model.Node;
import com.pt.Kademlia.model.RoutingTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigInteger;

@EnableScheduling
@SpringBootApplication
public class KademliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KademliaApplication.class, args);
	}
}
