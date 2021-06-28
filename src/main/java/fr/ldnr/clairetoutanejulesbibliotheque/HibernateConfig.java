/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldnr.clairetoutanejulesbibliotheque;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    //On recree la configuration en java (a la place du fichier xml : hibernate.cfg.xml)
    @Bean
    public SessionFactory sessionFactory() {

        Properties options = new Properties();
        //<property name="hibernate.connection.driver_class">org.sqlite.JDBC</property>
        options.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        //<property name="hibernate.connection.url">jdbc:sqlite:nom.sqlite</property>
        options.put("hibernate.connection.url", "jdbc:sqlite:bibliothequeData.sqlite");
        //<property name="hibernate.dialect">org.sqlite.hibernate.dialect.SQLiteDialect</property>
        options.put("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
        //<property name="hibernate.show_sql">true</property>
        options.put("hibernate.show_sql", "true");
        //<property name="hibernate.hbm2ddl.auto">create</property>
        options.put("hibernate.hbm2ddl.auto", "update");

        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .addProperties(options)
                //<mapping class="fr.ldnr.julesflores.mavenproject4.Inscription"/> 
                .addAnnotatedClass(Livre.class)
                .addAnnotatedClass(Emprunt.class)
                .buildSessionFactory();
        return factory;
    }
}
