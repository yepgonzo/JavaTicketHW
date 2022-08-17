package Ticket;

public class ManagerTicket {
    private RepositoryTicket repository;

    public ManagerTicket(RepositoryTicket repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.add(ticket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Ticket[] findAll() {
        return repository.findAll();
    }

    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket: repository.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String from, String to) {
        if (ticket.getFromAirport().contains(from) && ticket.getToAirport().contains(to)) {
            return true;
        } else {
            return false;
        }
    }
}
