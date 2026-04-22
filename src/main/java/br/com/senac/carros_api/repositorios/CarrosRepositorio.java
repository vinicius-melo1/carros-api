package br.com.senac.carros_api.repositorios;

import br.com.senac.carros_api.entidades.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrosRepositorio
    extends JpaRepository<Carros, Long>{
}
