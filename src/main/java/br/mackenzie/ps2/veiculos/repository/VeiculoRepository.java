package br.mackenzie.ps2.veiculos.repository;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

	Optional<Veiculo> findByPlacaIgnoreCase(String placa);

	boolean existsByPlacaIgnoreCase(String placa);
}