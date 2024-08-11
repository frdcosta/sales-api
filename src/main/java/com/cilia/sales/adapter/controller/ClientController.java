package com.cilia.sales.adapter.controller;

import com.cilia.sales.adapter.mapper.ClientMapper;
import com.cilia.sales.application.dto.request.ClientRequest;
import com.cilia.sales.application.dto.response.ClientResponse;
import com.cilia.sales.application.usecase.client.DeleteClientUseCase;
import com.cilia.sales.application.usecase.client.GetClientUseCase;
import com.cilia.sales.application.usecase.client.SaveClientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final GetClientUseCase getClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final SaveClientUseCase saveClientUseCase;
    private final ClientMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(mapper.toResponseList(getClientUseCase.execute()));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(mapper.toResponse(saveClientUseCase.execute(mapper.fromRequest(request))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return getClientUseCase.execute(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest clientDetails) {
        return getClientUseCase.execute(id)
                .map(client -> ResponseEntity.ok(mapper.toResponse(saveClientUseCase.execute(mapper.fromRequest(clientDetails)))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        deleteClientUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
