package br.com.techtaste.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal da aplicação API Gateway.
 *
 * Esta classe é o ponto de entrada da aplicação Spring Boot que atua como
 * um gateway de API, responsável por rotear requisições para os microsserviços
 * registrados no service discovery.
 *
 * @author TechTaste
 * @version 1.0
 * 
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	/**
	 * Método principal (entry point) da aplicação.
	 *
	 * Este método é responsável por inicializar e levantar o contexto da aplicação
	 * Spring Boot, configurando automaticamente todos os componentes necessários
	 * para o funcionamento do API Gateway, incluindo:
	 * - Configuração de rotas
	 * - Integração com service discovery (Eureka/Consul)
	 * - Filtros e interceptadores de requisição
	 * - Balanceamento de carga
	 * - Circuit breakers e tolerância a falhas
	 *
	 * @param args Argumentos de linha de comando que podem ser passados
	 *             para configurar propriedades da aplicação
	 */
	public static void main(String[] args) {
		// Inicializa a aplicação Spring Boot, levantando o contexto completo
		// O parâmetro ApiGatewayApplication.class informa ao Spring qual é
		// a classe principal a ser carregada e configurada
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}