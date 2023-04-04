package be.vdab.hateoas.controllers;

import be.vdab.hateoas.domain.Console;
import be.vdab.hateoas.dto.NieuweConsole;
import be.vdab.hateoas.exceptions.ConsoleNietGevondenException;
import be.vdab.hateoas.services.ConsoleService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("consoles")
@ExposesResourceFor(Console.class)
public class ConsoleController {
    private final ConsoleService consoleService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Console> links;
    private static final BigDecimal KILO_IN_EEN_POND = BigDecimal.valueOf(2.2);

    public ConsoleController(ConsoleService consoleService, EntityLinks links) {
        this.consoleService = consoleService;
        this.links = links.forType(Console.class, Console::getId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid NieuweConsole nieuweConsole) {
        var console = consoleService.create(nieuweConsole);
        var headers = new HttpHeaders();
        headers.setLocation(links.linkToItemResource(console).toUri());
        return headers;
    }
    @GetMapping("{id}")
    EntityModel<Console> findById(@PathVariable long id) {
        return consoleService.findById(id)
                .map(console -> EntityModel.of(console)
                        .add(links.linkToItemResource(console))
                        .add(links.linkForItemResource(console)
                                .slash("gewichtInPond").withRel("gewichtInPond")))
                .orElseThrow(ConsoleNietGevondenException::new);
    }
    @GetMapping("{id}/gewichtInPond")
    BigDecimal findGewichtInPond(@PathVariable long id) {
        return consoleService.findById(id)
                .map(console -> console.getGewicht().multiply(KILO_IN_EEN_POND))
                .orElseThrow(ConsoleNietGevondenException::new);
    }
}
