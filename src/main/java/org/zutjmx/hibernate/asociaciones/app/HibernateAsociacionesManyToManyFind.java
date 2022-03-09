package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.entity.Curso;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesManyToManyFind {
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

            alumnoUno.getCursos().add(cursoUno);
            alumnoUno.getCursos().add(cursoDos);

            alumnoDos.getCursos().add(cursoUno);

            entityManager.getTransaction().commit();

            System.out.println(alumnoUno);
            System.out.println(alumnoDos);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
