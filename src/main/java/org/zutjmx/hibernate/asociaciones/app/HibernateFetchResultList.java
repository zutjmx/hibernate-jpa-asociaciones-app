package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {
        String todosLosClientes = "select distinct c from Cliente c " +
                "left outer join fetch c.direcciones " +
                //"left outer join fetch c.facturas " +
                "left outer join fetch c.clienteDetalle";

        EntityManager entityManager = JpaUtil.getEntityManager();
        List<Cliente> clientes = entityManager
                .createQuery(todosLosClientes, Cliente.class)
                .getResultList();
        clientes.forEach(
                cliente -> System.out.println(cliente.getNombre() + " :: " + cliente.getDirecciones())
        );
        entityManager.close();
    }
}
