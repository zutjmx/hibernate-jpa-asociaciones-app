package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.entity.Curso;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.Locale;

public class MiFakerAlumnosCursos {
    public static void main(String[] args) {
        //Faker faker = new Faker();
        Faker faker = new Faker(new Locale("es-MX"));

        System.out.println(":: Se van a generar datos por medio de javafaker ::");
        System.out.println(":: https://github.com/DiUS/java-faker ::");

        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            for (int i = 0; i < 20; i++) {
                String profesor = faker.name().fullName();
                String nombreCurso = faker.book().title();

                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();


                System.out.println("profesor: " + profesor);
                System.out.println("curso: " + nombreCurso);

                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println(":::::::::::::::::::::::::::::::::");

                Alumno alumno = new Alumno(firstName, lastName);
                Curso curso = new Curso(nombreCurso,profesor);

                entityManager.persist(alumno);
                entityManager.persist(curso);

            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        System.out.println(":: Se concluye proceso de inserción de datos Faker ::");

    }
}
