package be.vdab.hateoas.services;

import be.vdab.hateoas.domain.Console;
import be.vdab.hateoas.dto.NieuweConsole;
import be.vdab.hateoas.repositories.ConsoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
    public Optional<Console> findById(long id) {
        return consoleRepository.findById(id);
    }
    @Transactional
    public Console create(NieuweConsole nieuweConsole) {
        var console = new Console(nieuweConsole.naam(), nieuweConsole.gewicht());
        consoleRepository.save(console);
        return console;
    }
}
