package com.subrutin.lingkar.catalog.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.subrutin.lingkar.catalog.domain.Publisher;
import com.subrutin.lingkar.catalog.repository.PublisherRepository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestTransaction
public class PublisherRepositoryImplTest {

    @Inject
    private PublisherRepository publisherRepository;

    @Test
    void testFindAllPublisher() {
        List<Publisher> publishers = this.publisherRepository.findAllPublishers();
        assertEquals(5, publishers.size());

    }

    @Test
    void testCreatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Penguin Random House");
        publisher.setDescription(
                "Penguin Random House, hasil merger antara Penguin Books dan Random House pada 2013, adalah penerbit buku terbesar di dunia");
        publisherRepository.createPublisher(publisher);

        assertEquals(6, publisherRepository.findAllPublishers().size());
    }

    @Test
    void testDeletePublisher() {
        publisherRepository.deletePublisher(1L);
        Optional<Publisher> publisher = this.publisherRepository.findPublisherById(1L);
        assertEquals(false, publisher.isPresent());

    }

    @Test
    void testFindPublisherById() {
        Optional<Publisher> publisher = this.publisherRepository.findPublisherById(1L);
        assertEquals("Bentang Pustaka", publisher.get().getName());

    }

    @Test
    void testUpdatePublisher() {
        Publisher publisher = publisherRepository.findPublisherById(1L).get();
        publisher.setName("Penerbit Bentang Pustaka");
        publisherRepository.updatePublisher(publisher);
        Publisher publisher2 = publisherRepository.findPublisherById(1L).get();
        assertEquals("Penerbit Bentang Pustaka", publisher2.getName());

    }
}
