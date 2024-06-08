package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}
