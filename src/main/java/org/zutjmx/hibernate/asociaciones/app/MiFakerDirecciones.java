package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.entity.Curso;
import org.zutjmx.hibernate.asociaciones.app.entity.Direccion;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.Locale;

public class MiFakerDirecciones {
    public static void main(String[] args) {
        //Faker faker = new Faker();
        Faker faker = new Faker(new Locale("es-MX"));

        System.out.println(":: Se van a generar datos por medio de javafaker ::");
        System.out.println(":: https://github.com/DiUS/java-faker ::");

        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            for (int i = 0; i < 20; i++) {
                String calle = faker.address().streetName();
                Integer numero = Integer.parseInt(faker.address().streetAddressNumber());

                System.out.println("Calle: " + calle);
                System.out.println("Número: " + numero);
                System.out.println(":::::::::::::::::::::::::::::::::");

                Direccion direccion = new Direccion(calle,numero);

                entityManager.persist(direccion);

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
