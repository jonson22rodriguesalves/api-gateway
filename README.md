* TechTaste - API Gateway
* 🌐 Sistema de Gateway de API para Microsserviços
* Sistema desenvolvido para Alura - BBTS 2026 que implementa um gateway de API centralizado
* para roteamento, balanceamento de carga e descoberta de serviços da plataforma TechTaste,
* utilizando arquitetura reativa com Spring Cloud Gateway.
 
* 📋 Funcionalidades Principais
* ✅ Roteamento Dinâmico - Descoberta automática de serviços registrados no Eureka
* ✅ Balanceamento de Carga - Distribuição inteligente de requisições entre instâncias
* ✅ Service Discovery - Integração com Netflix Eureka para registro e descoberta
* ✅ Roteamento Reativo - Baseado em Spring WebFlux para alta performance
* ✅ Resiliência - Tolerância a falhas com fallback automático
* ✅ Configuração Simplificada - Locator automático baseado em service-id
 
* 🛠️ Tecnologias Utilizadas
* Tecnologia	Versão	Finalidade
* Java	21	Linguagem de programação
* Spring Boot	3.5.16	Framework principal
* Spring Cloud Gateway	2025.0.3	Gateway reativo e roteamento
* Spring Cloud Netflix Eureka Client	2025.0.3	Service discovery e registro
* Spring WebFlux	-	Suporte a programação reativa
* Maven	-	Gerenciador de dependências
 
* 📚 Pré-requisitos
* Java JDK 21 ou superior
* Conhecimento básico de microsserviços
* Eureka Server em execução (porta 8081)
* Terminal/Console para execução
  
* 📦 Estrutura do Projeto
*   api-gateway/
*   ├── src/
*   │   ├── main/
*   │   │   ├── java/
*   │   │   │   └── br/com/techtaste/api_gateway/
*   │   │   │       └── ApiGatewayApplication.java
*   │   │   └── resources/
*   │   │       └── application.properties
*   ├── pom.xml
*   └── README.md
  
* 🚀 Como Executar
* Pré-requisitos
* Java JDK 21+
* Maven 3.6+
* Eureka Server rodando em http://localhost:8081
* Postman/Insomnia (para testes)
 
* Passos para Execução
* Inicie o Eureka Server (certifique-se de que está rodando na porta 8081)
 
* Compile e empacote o projeto:
* mvn clean package
 
* Execute a aplicação:
* java -jar target/api-gateway-0.0.1-SNAPSHOT.jar
 
* Ou via Maven:
* mvn spring-boot:run
 
* Acesso ao Gateway:
* http://localhost:8082
 
* 🎯 Endpoints do Gateway
 
* Roteamento Automático
* O gateway automaticamente expõe rotas para todos os microsserviços registrados no Eureka seguindo o padrão:
* Response (201 Created):
 
* http://localhost:8082/{SERVICE-ID}/{endpoint}
 
* Exemplos de Rotas Geradas
* Serviço Registrado	URL do Gateway	Descrição
* ms-pedidos	http://localhost:8082/ms-pedidos/pedidos	Roteia para o microsserviço de pedidos
* ms-usuarios	http://localhost:8082/ms-usuarios/usuarios	Roteia para o microsserviço de usuários
* ms-produtos	http://localhost:8082/ms-produtos/produtos	Roteia para o microsserviço de produtos
 
* Endpoints de Monitoramento (Spring Boot Actuator)
* Health Check: http://localhost:8082/actuator/health
* Métricas: http://localhost:8082/actuator/metrics
* Info: http://localhost:8082/actuator/info
* Gateway Routes: http://localhost:8082/actuator/gateway/routes
 
* 📊 Diagrama de Arquitetura
````mermaid
graph TB
    Client[Cliente Externo] -->|Requisição HTTP| Gateway[API Gateway<br/>:8082]
    
    Gateway -->|Registro e Descoberta| Eureka[Eureka Server<br/>:8081]
    
    Gateway -->|Roteamento| Service1[ms-pedidos<br/>:8083]
    Gateway -->|Roteamento| Service2[ms-usuarios<br/>:8084]
    Gateway -->|Roteamento| Service3[ms-produtos<br/>:8085]
    
    Service1 -->|Registro| Eureka
    Service2 -->|Registro| Eureka
    Service3 -->|Registro| Eureka
    
    classDef gateway fill:#4CAF50,stroke:#333,stroke-width:2px,color:#fff
    classDef service fill:#2196F3,stroke:#333,stroke-width:2px,color:#fff
    classDef eureka fill:#FF9800,stroke:#333,stroke-width:2px,color:#fff
    classDef client fill:#9E9E9E,stroke:#333,stroke-width:2px,color:#fff
    
    class Gateway gateway
    class Service1,Service2,Service3 service
    class Eureka eureka
    class Client client

````



*📊 Diagrama de Classes
  
````mermaid
classDiagram
    direction TB
    
    class ApiGatewayApplication {
        +main(String[] args)
    }
    
    class SpringApplication {
        +run(Class, String[])
    }
    
    class ApplicationProperties {
        <<config>>
        -spring.application.name
        -server.port
        -eureka.client.*
        -spring.cloud.gateway.*
    }
    
    class EurekaClient {
        <<dependency>>
        +registrarServico()
        +descobrirServicos()
        +atualizarRegistry()
    }
    
    class GatewayRoutes {
        <<auto>>
        +rotasDinamicas()
        +locatorEnabled()
        +lowerCaseServiceId()
    }
    
    ApiGatewayApplication --> SpringApplication : utiliza
    ApiGatewayApplication --> ApplicationProperties : carrega
    ApiGatewayApplication --> EurekaClient : integra
    ApiGatewayApplication --> GatewayRoutes : configura
    
    note for ApiGatewayApplication "Ponto de entrada da aplicação\nHabilita descoberta de serviços\nConfigura roteamento automático"
    note for GatewayRoutes "Locator automático gera\nrotas: /{service-id}/**"
````
* 🔧 Configurações Principais
 
* application.properties
* # Nome e Porta
* spring.application.name=api-gateway
* server.port=8082
 
* # Eureka Client
* eureka.client.service-url.defaultZone=http://localhost:8081/eureka
* eureka.client.fetch-registry=true
* eureka.client.register-with-eureka=true
 
* # Gateway Discovery Locator
* spring.cloud.gateway.discovery.locator.enabled=true
* spring.cloud.gateway.discovery.locator.lower-case-service-id=true
 
 
* 📝 Observações Técnicas
* Arquitetura Reativa
* Spring WebFlux: Utiliza programação reativa com Reactor Core
* Non-blocking I/O: Alta performance com baixo consumo de recursos
* Netty Server: Servidor web embarcado otimizado para aplicações reativas
 
* Service Discovery
* Eureka Integration: O gateway se registra e descobre serviços automaticamente
* Cache Local: Mantém cópia do registry para consultas rápidas
* Heartbeat: Envia pulsos periódicos para manter registro ativo
 
* Roteamento Dinâmico
* Locator Automático: Cria rotas baseadas nos serviços registrados
* Balanceamento: Utiliza Ribbon para balanceamento de carga client-side
* Resiliência: Fallback automático em caso de falhas
 
* Boas Práticas Aplicadas
* ✅ Configuração Externalizada: Propriedades em application.properties
* ✅ Descoberta Automática: Reduz configuração manual de rotas
* ✅ Arquitetura Reativa: Suporte a alta concorrência
* ✅ Integração Contínua: Facilita deployment em ambientes cloud
* ✅ Observabilidade: Pronto para monitoramento com Actuator
 
* 🔍 Troubleshooting
* Problemas Comuns
* Problema	Solução
* Gateway não inicia	Verificar se Eureka Server está rodando na porta 8081
* Rotas não são criadas	Confirmar que locator.enabled=true está configurado
* Serviços não descobertos	Verificar se os serviços estão registrados no Eureka
* Porta 8082 em uso	Alterar server.port para outra porta disponível
 
* Logs Importantes
* Service Registration: Registered instance api-gateway with status UP
* Route Creation: Route added for service: ms-pedidos
* Discovery Refresh: Fetching registry from Eureka
 
* 📚 Dependências Principais
* <!-- Spring Cloud Gateway Reativo -->
* <dependency>
*     <groupId>org.springframework.cloud</groupId>
*     <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
* </dependency>
 
* <!-- Eureka Client para Service Discovery -->
* <dependency>
*     <groupId>org.springframework.cloud</groupId>
*     <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
* </dependency>
 
* 🚦 Status do Projeto
* Componente	Status	Observação
* Gateway	✅ Ativo	Disponível na porta 8082
* Eureka Integration	✅ Ativo	Registrado e descobrindo serviços
* Roteamento Dinâmico	✅ Ativo	Rotas geradas automaticamente
* Balanceamento	✅ Ativo	Distribuição entre instâncias