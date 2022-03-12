package org.zutjmx.hibernate.asociaciones.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.zutjmx.hibernate.asociaciones.app.entity.Cliente;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);
        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("clienteDetalle",JoinType.LEFT);

        query.select(cliente).distinct(true);

        List<Cliente> clientes = entityManager
                .createQuery(query)
                .getResultList();

        clientes.forEach(
                c -> System.out.println(
                        c.getNombre() +
                                ", direcciones :: " + c.getDirecciones() +
                                ", detalle :: " + (c.getClienteDetalle() != null? c.getClienteDetalle() : "")
                )
        );

        entityManager.close();
    }
}
