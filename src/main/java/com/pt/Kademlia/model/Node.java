package com.pt.Kademlia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Data
public class Node {
    private final String id;
    private final String ip;
    private final int port;
    //Lista Nos vizinhos em hash
    //List dAs transações e quem fex
    //Validar se a hash ta correta de acordo com a lista interna das hashs das transaçoes
    //Lista das transaçoes recebidas
    //propagar para os membros que participam na transação

    //Blockchain
    //Receber uma transação
    //Minar as transacoes
    //Enviar de volta a transacap
    



    @JsonCreator
    public Node(@JsonProperty("ip") String ip, @JsonProperty("port") int port) {
        this.ip = ip;
        this.port = port;
        this.id = generateNodeId(ip, port);
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    private String generateNodeId(String ip, int port) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update((ip + ":" + port).getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}