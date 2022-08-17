package Ticket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTicketTest {
    RepositoryTicket repository = new RepositoryTicket();
    ManagerTicket manager = new ManagerTicket(repository);

    Ticket ticket1 = new Ticket(1, 8_000, "SVO", "AER", 120);
    Ticket ticket2 = new Ticket(2, 12_000, "VKO", "KZN", 300);
    Ticket ticket3 = new Ticket(3, 10_500, "VKO", "KZN", 60);
    Ticket ticket4 = new Ticket(4, 5_000, "LED", "AMV", 90);
    Ticket ticket5 = new Ticket(5, 50_000, "MRV", "VVO", 1800);


    @Test
    public void addTicket() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);


        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchTicket() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.searchBy("DME", "UFA");
    }

    @Test
    public void searchSameName() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket2, ticket3};
        Ticket[] actual = manager.searchBy("VKO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNameProduct() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket5};
        Ticket[] actual = manager.searchBy("MRV", "VVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoTicket() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("ALA", "AAQ");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findId() {
        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);
        repository.add(ticket4);
        repository.add(ticket5);

        repository.findById(2);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAll();
    }

    @Test
    public void findNoId() {
        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);
        repository.add(ticket4);
        repository.add(ticket5);

        repository.findById(10);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll();
    }

    @Test
    public void removeId() {
        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);
        repository.add(ticket4);
        repository.add(ticket5);

        repository.removeById(3);
    }

    @Test
    public void removeNoId() {
        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);
        repository.add(ticket4);
        repository.add(ticket5);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(20);
        });
    }
}