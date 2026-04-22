package br.com.senac.carros_api.services;

import br.com.senac.carros_api.dtos.CarrosRequestDto;
import br.com.senac.carros_api.entidades.Carros;
import br.com.senac.carros_api.repositorios.CarrosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {
    private CarrosRepositorio carrosRepositorio;

    public CarrosService(CarrosRepositorio carrosRepositorio) {
        this.carrosRepositorio = carrosRepositorio;
    }

    public List<Carros> listar () {
        return carrosRepositorio.findAll();
    }

    public Carros criar(CarrosRequestDto carro) {
        Carros carroPersist = this.carrosRequestDtoParaClientes(carro);

        return carrosRepositorio.save(carroPersist);
    }

    public Carros atualizar (Long id, CarrosRequestDto cliente) {
        if(carrosRepositorio.existsById(id)) {
            Carros carroPersist = this.carrosRequestDtoParaClientes(cliente);

            carroPersist.setId(id);

            return carrosRepositorio.save(carroPersist);
        }

        throw new RuntimeException("Carro Não encontrado");
    }

    public void deletar (Long id) {
        if(carrosRepositorio.existsById(id)) {
            carrosRepositorio.deleteById(id);
        }
        throw new RuntimeException("Carro não encontrado");
    }

    private Carros carrosRequestDtoParaClientes(CarrosRequestDto entrada) {
        Carros saida = new Carros();
        saida.setMarca(entrada.getMarca());
        saida.setModelo(entrada.getModelo());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;
    }
}
