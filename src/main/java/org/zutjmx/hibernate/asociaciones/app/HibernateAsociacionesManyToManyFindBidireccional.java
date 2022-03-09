package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.entity.Curso;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesManyToManyFindBidireccional {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Alumno alumnoUno = entityManager.find(Alumno.class,12L);
            Alumno alumnoDos = entityManager.find(Alumno.class,14L);

            /*Curso cursoUno = new Curso(faker.book().title(),faker.book().author());
            Curso cursoDos = new Curso(faker.book().title(),faker.book().author());*/
            Curso cursoUno = entityManager.find(Curso.class,15L);
            Curso cursoDos = entityManager.find(Curso.class,16L);

            alumnoUno.addCurso(cursoUno);
            alumnoUno.addCurso(cursoDos);

            alumnoDos.addCurso(cursoUno);

            entityManager.getTransaction().commit();

            System.out.println(alumnoUno);
            System.out.println(alumnoDos);

            entityManager.getTransaction().begin();
            //Curso cursoAEliminar = entityManager.find(Curso.class,22L);
            Curso cursoAEliminar = new Curso(cursoDos.getTitulo(),cursoDos.getProfesor());
            cursoAEliminar.setId(cursoDos.getId());
            alumnoUno.removeCurso(cursoAEliminar);
            entityManager.getTransaction().commit();
            System.out.println(alumnoUno);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
