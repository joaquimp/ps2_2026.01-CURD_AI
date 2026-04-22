package br.mackenzie.ps2.veiculos.service;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {

	private final VeiculoRepository veiculoRepository;

	public VeiculoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}

	public List<Veiculo> listarTodos() {
		return veiculoRepository.findAll();
	}

	public Veiculo buscarPorPlaca(String placa) {
		return localizarPorPlaca(placa);
	}

	public Veiculo cadastrar(Veiculo veiculo) {
		validarVeiculo(veiculo);
		if (veiculoRepository.existsByPlacaIgnoreCase(veiculo.getPlaca())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe um veiculo com esta placa.");
		}
		return veiculoRepository.save(veiculo);
	}

	public Veiculo atualizar(String placa, Veiculo veiculoAtualizado) {
		validarVeiculo(veiculoAtualizado);
		Veiculo atual = localizarPorPlaca(placa);
		if (!atual.getPlaca().equalsIgnoreCase(veiculoAtualizado.getPlaca())
				&& veiculoRepository.existsByPlacaIgnoreCase(veiculoAtualizado.getPlaca())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe um veiculo com esta placa.");
		}

		if (!atual.getPlaca().equalsIgnoreCase(veiculoAtualizado.getPlaca())) {
			veiculoRepository.delete(atual);
		}

		atual.setPlaca(veiculoAtualizado.getPlaca());
		atual.setMarca(veiculoAtualizado.getMarca());
		atual.setModelo(veiculoAtualizado.getModelo());
		atual.setAno(veiculoAtualizado.getAno());
		atual.setCor(veiculoAtualizado.getCor());

		return veiculoRepository.save(atual);
	}

	public void remover(String placa) {
		Veiculo veiculo = localizarPorPlaca(placa);
		veiculoRepository.delete(veiculo);
	}

	private Veiculo localizarPorPlaca(String placa) {
		return veiculoRepository.findByPlacaIgnoreCase(placa)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado."));
	}

	private void validarVeiculo(Veiculo veiculo) {
		if (veiculo == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O corpo da requisicao e obrigatorio.");
		}
		if (estaVazio(veiculo.getMarca()) || estaVazio(veiculo.getModelo()) || estaVazio(veiculo.getPlaca()) || estaVazio(veiculo.getCor())
				|| veiculo.getAno() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Os campos marca, modelo, ano, placa e cor sao obrigatorios.");
		}
	}

	private boolean estaVazio(String valor) {
		return valor == null || valor.isBlank();
	}
}