package com.example.demo.configuration;

import com.example.demo.client.ArrayPersistanceHttpClient;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableFeignClients(clients = ArrayPersistanceHttpClient.class)
@RibbonClient(name = "array-persistence-service", configuration = RibbonConfig.class)
@Primary
public class RibbonConfig {

   @LocalServerPort
   int port;

   @Bean
   public ServerList<Server> serverList() {
      return new StaticServerList<>(new Server("127.0.0.1", port), new Server("127.0.0.1", port));
   }
}