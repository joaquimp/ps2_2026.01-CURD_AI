package br.mackenzie.ps2.veiculos.controller;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.service.VeiculoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private final VeiculoService veiculoService;

	public VeiculoController(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	@GetMapping
	public List<Veiculo> listarTodos() {
		return veiculoService.listarTodos();
	}

	@GetMapping("/buscar")
	public Veiculo buscarPorPlaca(@RequestParam String placa) {
		return veiculoService.buscarPorPlaca(placa);
	}

	@GetMapping("/{placa}")
	public Veiculo buscarPorId(@PathVariable String placa) {
		return veiculoService.buscarPorPlaca(placa);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo cadastrar(@RequestBody Veiculo veiculo) {
		return veiculoService.cadastrar(veiculo);
	}

	@PutMapping("/{placa}")
	public Veiculo atualizar(@PathVariable String placa, @RequestBody Veiculo veiculo) {
		return veiculoService.atualizar(placa, veiculo);
	}

	@DeleteMapping("/{placa}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String placa) {
		veiculoService.remover(placa);
	}
}