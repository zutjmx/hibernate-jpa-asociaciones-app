package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        Cliente cliente = entityManager.find(Cliente.class,21L);
        System.out.println(cliente.getNombre() + " :: " + cliente.getDirecciones());

        entityManager.close();

    }
}
