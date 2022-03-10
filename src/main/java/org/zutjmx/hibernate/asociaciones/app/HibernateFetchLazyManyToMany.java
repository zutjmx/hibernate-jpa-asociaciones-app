package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchLazyManyToMany {
    public static void main(String[] args) {
        String todosLosAlumnos = "select distinct a from Alumno a " +
                "left outer join fetch a.cursos";

        EntityManager entityManager = JpaUtil.getEntityManager();

        List<Alumno> alumnos = entityManager
                .createQuery(todosLosAlumnos,Alumno.class)
                .getResultList();
        alumnos.forEach(
                    alumno -> System.out.println(alumno.getNombre() + " :: " + alumno.getCursos())
                );
        entityManager.close();
    }
}
