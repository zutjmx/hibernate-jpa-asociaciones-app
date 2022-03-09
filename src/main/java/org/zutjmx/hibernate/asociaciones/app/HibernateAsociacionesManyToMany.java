package org.zutjmx.hibernate.asociaciones.app;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.zutjmx.hibernate.asociaciones.app.entity.Alumno;
import org.zutjmx.hibernate.asociaciones.app.entity.Curso;
import org.zutjmx.hibernate.asociaciones.app.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            String nombreAlumnoUno = faker.name().firstName();
            String apellidoAlumnoUno = faker.name().lastName();

            String nombreAlumnoDos = faker.name().firstName();
            String apellidoAlumnoDos = faker.name().lastName();

            Alumno alumnoUno = new Alumno(nombreAlumnoUno,apellidoAlumnoUno);
            Alumno alumnoDos = new Alumno(nombreAlumnoDos,apellidoAlumnoDos);

            String nombreCursoUno = faker.book().title();
            String profesorCursoUno = faker.name().fullName();

            String nombreCursoDos = faker.book().title();
            String profesorCursoDos = faker.name().fullName();

            Curso cursoUno = new Curso(nombreCursoUno,profesorCursoUno);
            Curso cursoDos = new Curso(nombreCursoDos,profesorCursoDos);

            alumnoUno.getCursos().add(cursoUno);
            alumnoUno.getCursos().add(cursoDos);

            alumnoDos.getCursos().add(cursoUno);

            entityManager.persist(alumnoUno);
            entityManager.persist(alumnoDos);

            entityManager.getTransaction().commit();

            System.out.println(alumnoUno);
            System.out.println(alumnoDos);

            entityManager.getTransaction().begin();
            Curso cursoAEliminar = entityManager.find(Curso.class,22L);
            alumnoUno.getCursos().remove(cursoAEliminar);
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
