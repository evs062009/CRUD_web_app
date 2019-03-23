package com.shevtsov.servlets;

import com.shevtsov.domain.Client;
import com.shevtsov.dto.ClientDto;
import com.shevtsov.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ClientServlet extends AbstractServlet<Client> {
    private ClientService clientService;

    ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected List<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    protected boolean create(HttpServletRequest req, HttpServletResponse resp) {
        Optional<ClientDto> optional = getParameters(req, resp);
        if(optional.isPresent()) {
            ClientDto clientDto = optional.get();
            return clientService.create(clientDto.getName(), clientDto.getSurname(), clientDto.getAge(),
                    clientDto.getPhone(), clientDto.getEmail());
        } else {
            return false;
        }
    }

    @Override
    protected boolean modify(HttpServletRequest req, HttpServletResponse resp, long id) {
        Optional<ClientDto> optional = getParameters(req, resp);
        if (optional.isPresent()){
            ClientDto clientDto = optional.get();
            return clientService.modify(id, clientDto.getName(), clientDto.getSurname(), clientDto.getAge(),
                    clientDto.getPhone(), clientDto.getEmail());
        } else {
            return false;
        }
    }

    @Override
    protected boolean remove(long id) {
        return clientService.remove(id);
    }

    private Optional<ClientDto> getParameters(HttpServletRequest req, HttpServletResponse resp) {
        int age;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ServletUtilities.printMsg(resp, "Invalid age input!!!");
            return Optional.empty();
        }
        return Optional.of(new ClientDto(req.getParameter("name"), req.getParameter("surname"),
                age, req.getParameter("phone"), req.getParameter("email")));
    }
}