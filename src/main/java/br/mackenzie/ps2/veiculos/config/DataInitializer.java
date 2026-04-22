package br.mackenzie.ps2.veiculos.config;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner inicializarVeiculos(VeiculoRepository veiculoRepository) {
		return args -> {
			if (veiculoRepository.count() > 0) {
				return;
			}

			veiculoRepository.saveAll(List.of(
				new Veiculo("BRA2E19", "Toyota", "Corolla", 2022, "Preto"),
				new Veiculo("MCK9F44", "Honda", "Civic", 2021, "Prata"),
				new Veiculo("XYZ1A23", "Chevrolet", "Onix", 2023, "Branco")
			));
		};
	}
}