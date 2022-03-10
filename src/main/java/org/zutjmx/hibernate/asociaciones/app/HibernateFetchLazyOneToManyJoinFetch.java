package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        Faker faker = new Faker();
        //String consulta = "select c from Cliente c where c.id = :id";
        String consultaLeftJoin = "select c from Cliente c " +
                "left outer join fetch c.direcciones " +
                "left join fetch c.clienteDetalle " +
                "where c.id = :id";
        EntityManager entityManager = JpaUtil.getEntityManager();

        Cliente cliente = entityManager
                //.createQuery(consulta,Cliente.class)
                .createQuery(consultaLeftJoin,Cliente.class)
                .setParameter("id",21L)
                .getSingleResult();

        System.out.println(cliente.getNombre() + " :: " + cliente.getDirecciones());
        System.out.println(cliente.getNombre() + " :: " + cliente.getClienteDetalle());

        entityManager.close();

    }
}
