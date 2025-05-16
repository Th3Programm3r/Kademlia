package com.pt.Kademlia.controller;

import com.pt.Kademlia.model.Node;
import com.pt.Kademlia.model.RoutingTable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


//s|kadelmia
//Work
//GUARDAR A LISTA DE HASH NUM JSON E BUSCAR QUANDO A APLICAÇÂO INICIA E GUARDAR QUANDO A APLICAÇÂO TERMINA


@CrossOrigin
@RestController()
@RequestMapping("/kademlia")
public class KademliaController {
    private RoutingTable routingTable;
    private final String file = "routing_table.json";

    @PostConstruct
    public void init() {
        Node localNode = new Node("192.150.1.0", 8090);
        routingTable = RoutingTable.loadFromJsonFile(file);
        if (routingTable == null) {
            routingTable = new RoutingTable(localNode);
        }
        System.out.println("Routing table initialized.");
    }

    @PreDestroy
    public void shutdown() {
        routingTable.saveToJsonFile(file);
        System.out.println("Routing table saved to JSON before shutdown.");
    }


    @PostMapping("/addNode")
    public String addNode(@RequestBody Node node) throws Exception {
        try{
            Node newNode=new Node(node.getIp(), node.getPort());
            routingTable.addNode(newNode);
            return "Node Added Sucessfull To Kademlia";
        }
        catch(Exception e){
            throw new Exception(e.getCause());
        }

    }

    @GetMapping("/printRoutingTable")
    public String printRoutingTable(){
        return routingTable.printRoutingTableToString();
    }

    @GetMapping("/ping")
    public void ping(){
        routingTable.ping();
    }

    @GetMapping("/save")
    public void save(){
        routingTable.saveToJsonFile(file);
    }


    @GetMapping("/findNodeByIpAndPort/{ip}/{port}")
    public Node findNodeByIpAndPort(@PathVariable String ip, @PathVariable int port){
        return routingTable.findNodeByIpAndPort(ip,port);
    }

    @GetMapping("/findNodeByHash/{hash}")
    public Node findNodeByHash(@PathVariable String hash){
        return routingTable.findNodeByHash(hash);
    }


    @GetMapping("/load")
    public void load(){
        routingTable.loadFromJsonFile(file);
    }

    @Scheduled(fixedRateString = "120000")
    public void callPing(){
        routingTable.ping();
    }
}
